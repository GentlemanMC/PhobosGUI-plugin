package me.earth.plugins.phobosgui.gui.components;

import me.earth.earthhack.impl.util.math.MathUtil;
import me.earth.plugins.phobosgui.PhobosColorManager;
import me.earth.plugins.phobosgui.PhobosGuiModule;
import me.earth.plugins.phobosgui.PhobosTextManager;
import me.earth.plugins.phobosgui.gui.Component;
import me.earth.plugins.phobosgui.gui.PhobosGui;
import me.earth.plugins.phobosgui.util.PhobosColorUtil;
import me.earth.plugins.phobosgui.util.PhobosRenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class Button extends Item
{
    private boolean state;
    
    public Button(final String s) {
        super(s);
        this.height = 15;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        if (PhobosGuiModule.getInstance().rainbowRolling.getValue()) {
            final int changeAlpha = PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue());
            final int changeAlpha2 = PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue());
            PhobosRenderUtil.drawGradientRect(this.x, this.y, (float)this.width, this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? changeAlpha : PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077), this.getState() ? (this.isHovering(n, n2) ? changeAlpha2 : PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, PhobosTextManager.getInstance().scaledHeight))) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        else {
            PhobosRenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().alpha.getValue()) : PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        PhobosTextManager.getInstance().drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - PhobosGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.onMouseClick();
        }
    }
    
    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        Button.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
    
    public void toggle() {
    }
    
    public boolean getState() {
        return this.state;
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    public boolean isHovering(final int n, final int n2) {
        for (Component component : PhobosGui.getInstance().getComponents()) {
            if (component.drag) {
                return false;
            }
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
}
