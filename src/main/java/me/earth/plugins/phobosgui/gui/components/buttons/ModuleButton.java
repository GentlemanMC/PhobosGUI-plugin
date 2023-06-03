// Class Version: 8
package me.earth.plugins.phobosgui.gui.components.buttons;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.*;
import me.earth.plugins.phobosgui.PhobosGuiModule;
import me.earth.plugins.phobosgui.PhobosTextManager;
import me.earth.plugins.phobosgui.gui.PhobosGui;
import me.earth.plugins.phobosgui.gui.components.Button;
import me.earth.plugins.phobosgui.gui.components.Item;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

import java.util.ArrayList;
import java.util.List;

public class ModuleButton extends Button
{
    private final Module module;
    private final List<Item> settings;
    private boolean subOpen;

    public ModuleButton(final Module module) {
        super(module.getName());
        this.settings = new ArrayList<>();
        this.module = module;
        this.initSettings();
    }

    public void initSettings() {
        final ArrayList list = new ArrayList();
        if (!this.module.getSettings().isEmpty()) {
            for (final Setting setting : this.module.getSettings()) {
                if (setting instanceof BooleanSetting && !setting.getName().equalsIgnoreCase("enabled")) {
                    list.add(new BooleanButton((Setting<Boolean>)setting));
                }
                else if (setting instanceof BindSetting) {
                    list.add(new BindButton((BindSetting)setting));
                }
                else if (setting instanceof EnumSetting) {
                    list.add(new EnumButton<>((EnumSetting<?>) setting));
                }
                else if (setting instanceof NumberSetting) {
                    if (((NumberSetting)setting).hasRestriction()) {
                        list.add(new Slider<>((NumberSetting<Number>)setting));
                    }
                    else {
                        list.add(new UnlimitedSlider((NumberSetting<Number>)setting));
                    }
                }
                else {
                    if (!(setting instanceof StringSetting)) {
                        continue;
                    }
                    list.add(new StringButton((StringSetting)setting));
                }
            }
        }
        this.settings.clear();
        this.settings.addAll(list);
    }

    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (!this.settings.isEmpty()) {
            final PhobosGuiModule instance = PhobosGuiModule.getInstance();
            PhobosTextManager.getInstance().drawStringWithShadow(instance.openCloseChange.getValue() ? (this.subOpen ? instance.close.getValue() : instance.open.getValue()) : instance.moduleButton.getValue(), this.x - 1.5f + this.width - 7.4f, this.y - 2.0f - PhobosGui.getInstance().getTextOffset(), -1);
            if (this.subOpen) {
                float n4 = 1.0f;
                for (final Item item : this.settings) {
                    if (!item.isHidden()) {
                        n4 += 15.0f;
                        item.setLocation(this.x + 1.0f, this.y + n4);
                        item.setHeight(15);
                        item.setWidth(this.width - 9);
                        item.drawScreen(n, n2, n3);
                    }
                    item.update();
                }
            }
        }
    }

    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (!this.settings.isEmpty()) {
            if (n3 == 1 && this.isHovering(n, n2)) {
                this.subOpen = !this.subOpen;
                ModuleButton.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            if (this.subOpen) {
                for (final Item item : this.settings) {
                    if (!item.isHidden()) {
                        item.mouseClicked(n, n2, n3);
                    }
                }
            }
        }
    }

    @Override
    public void onKeyTyped(final char c, final int n) {
        super.onKeyTyped(c, n);
        if (!this.settings.isEmpty() && this.subOpen) {
            for (final Item item : this.settings) {
                if (!item.isHidden()) {
                    item.onKeyTyped(c, n);
                }
            }
        }
    }

    @Override
    public int getHeight() {
        if (this.subOpen) {
            int n = 14;
            for (final Item item : this.settings) {
                if (!item.isHidden()) {
                    n += item.getHeight() + 1;
                }
            }
            return n + 2;
        }
        return 14;
    }

    public Module getModule() {
        return this.module;
    }

    @Override
    public void toggle() {
        this.module.toggle();
    }

    @Override
    public boolean getState() {
        return this.module.isEnabled();
    }
}
