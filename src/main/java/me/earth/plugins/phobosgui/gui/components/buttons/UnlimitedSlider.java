package me.earth.plugins.phobosgui.gui.components.buttons;

import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.impl.gui.visibility.Visibilities;
import me.earth.earthhack.impl.util.math.MathUtil;
import me.earth.plugins.phobosgui.PhobosColorManager;
import me.earth.plugins.phobosgui.PhobosGuiModule;
import me.earth.plugins.phobosgui.PhobosTextManager;
import me.earth.plugins.phobosgui.gui.PhobosGui;
import me.earth.plugins.phobosgui.gui.components.Button;
import me.earth.plugins.phobosgui.util.PhobosColorUtil;
import me.earth.plugins.phobosgui.util.PhobosRenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class UnlimitedSlider<N extends Number> extends Button
{
    public NumberSetting<N> setting;
    
    public UnlimitedSlider(final NumberSetting<N> setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        if (PhobosGuiModule.getInstance().rainbowRolling.getValue()) {
            PhobosRenderUtil.drawGradientRect((float)(int)this.x, (float)(int)this.y, this.width + 7.4f, (float)this.height, PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue()), PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue()));
        }
        else {
            PhobosRenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().alpha.getValue()) : PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().hoverAlpha.getValue()));
        }
        PhobosTextManager.getInstance().drawStringWithShadow(" - " + this.setting.getName() + " " + "\u00A77" + this.setting.getValue() + "\u00A7r" + " +", this.x + 2.3f, this.y - 1.7f - PhobosGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            UnlimitedSlider.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            if (this.isRight(n)) {
                if (this.setting.getValue() instanceof Double || this.setting.getValue() instanceof Float) {
                    this.setting.setValue(this.setting.numberToValue(this.setting.getValue().doubleValue() + 0.1));
                }
                else {
                    this.setting.setValue(this.setting.numberToValue(this.setting.getValue().longValue() + 1L));
                }
            }
            else if (this.setting.getValue() instanceof Double || this.setting.getValue() instanceof Float) {
                this.setting.setValue(this.setting.numberToValue(this.setting.getValue().doubleValue() - 0.1));
            }
            else {
                this.setting.setValue(this.setting.numberToValue(this.setting.getValue().longValue() - 1L));
            }
        }
    }
    
    @Override
    public void update() {
        this.setHidden(!Visibilities.VISIBILITY_MANAGER.isVisible(this.setting));
    }

    @Override
    public boolean getState() {
        return true;
    }
    
    public boolean isRight(final int n) {
        return n > this.x + (this.width + 7.4f) / 2.0f;
    }
}
