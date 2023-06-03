package me.earth.plugins.phobosgui.gui.components.buttons;

import me.earth.earthhack.api.setting.settings.BindSetting;
import me.earth.earthhack.api.util.bind.Bind;
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

public class BindButton extends Button
{
    private final BindSetting setting;
    public boolean isListening;
    
    public BindButton(final BindSetting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        if (PhobosGuiModule.getInstance().rainbowRolling.getValue()) {
            final int changeAlpha = PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue());
            final int changeAlpha2 = PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue());
            PhobosRenderUtil.drawGradientRect(this.x, this.y, this.width + 7.4f, this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? changeAlpha : PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077), this.getState() ? (this.isHovering(n, n2) ? changeAlpha2 : PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, PhobosTextManager.getInstance().scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        else {
            PhobosRenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().alpha.getValue()) : PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        if (this.isListening) {
            PhobosTextManager.getInstance().drawStringWithShadow("Listening...", this.x + 2.3f, this.y - 1.7f - PhobosGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else {
            PhobosTextManager.getInstance().drawStringWithShadow(this.setting.getName() + " " + "\u00A77" + this.setting.getValue(), this.x + 2.3f, this.y - 1.7f - PhobosGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public void update() {
        this.setHidden(!Visibilities.VISIBILITY_MANAGER.isVisible(this.setting));
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            BindButton.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            Bind value = Bind.fromKey(n);
            if (value.toString().equalsIgnoreCase("Escape")) {
                return;
            }
            if (value.toString().equalsIgnoreCase("Delete")) {
                value = Bind.none();
            }
            this.setting.setValue(value);
            super.onMouseClick();
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    @Override
    public boolean getState() {
        return !this.isListening;
    }
}
