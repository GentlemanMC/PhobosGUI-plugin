package me.earth.plugins.phobosgui.gui.components.buttons;

import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.impl.gui.visibility.Visibilities;
import me.earth.earthhack.impl.util.math.MathUtil;
import me.earth.plugins.phobosgui.PhobosColorManager;
import me.earth.plugins.phobosgui.PhobosGuiModule;
import me.earth.plugins.phobosgui.PhobosTextManager;
import me.earth.plugins.phobosgui.gui.Component;
import me.earth.plugins.phobosgui.gui.PhobosGui;
import me.earth.plugins.phobosgui.gui.components.Button;
import me.earth.plugins.phobosgui.util.PhobosColorUtil;
import me.earth.plugins.phobosgui.util.PhobosRenderUtil;
import org.lwjgl.input.Mouse;

public class Slider<N extends Number> extends Button
{
    public NumberSetting<N> setting;
    private final Number min;
    private final Number max;
    private final int difference;
    
    public Slider(final NumberSetting<N> setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = setting.getMin();
        this.max = setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        this.dragSetting(n, n2);
        PhobosRenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? -2007673515 : 290805077);
        if (PhobosGuiModule.getInstance().rainbowRolling.getValue()) {
            final int changeAlpha = PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue());
            final int changeAlpha2 = PhobosColorUtil.changeAlpha(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y + this.height, 0, PhobosTextManager.getInstance().scaledHeight)), PhobosGuiModule.getInstance().hoverAlpha.getValue());
            PhobosRenderUtil.drawGradientRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? 0.0f : ((this.width + 7.4f) * this.partialMultiplier()), this.height - 0.5f, this.isHovering(n, n2) ? changeAlpha : PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight)), this.isHovering(n, n2) ? changeAlpha2 : PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)this.y, 0, PhobosTextManager.getInstance().scaledHeight)));
        }
        else {
            PhobosRenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4f) * this.partialMultiplier()), this.y + this.height - 0.5f, this.isHovering(n, n2) ? PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().alpha.getValue()) : PhobosColorManager.getInstance().getColorWithAlpha(PhobosGuiModule.getInstance().hoverAlpha.getValue()));
        }
        PhobosTextManager.getInstance().drawStringWithShadow(this.getName() + " " + "\u00A77" + ((this.setting.getValue() instanceof Float) ? this.setting.getValue() : Double.valueOf(this.setting.getValue().doubleValue())), this.x + 2.3f, this.y - 1.7f - PhobosGui.getInstance().getTextOffset(), -1);
    }
    
    @Override
    public void mouseClicked(final int settingFromX, final int n, final int n2) {
        super.mouseClicked(settingFromX, n, n2);
        if (this.isHovering(settingFromX, n)) {
            this.setSettingFromX(settingFromX);
        }
    }
    
    @Override
    public boolean isHovering(final int n, final int n2) {
        for (Component component : PhobosGui.getInstance().getComponents()) {
            if (component.drag) {
                return false;
            }
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() + 8.0f && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
    
    @Override
    public void update() {
        this.setHidden(!Visibilities.VISIBILITY_MANAGER.isVisible(this.setting));
    }
    
    private void dragSetting(final int settingFromX, final int n) {
        if (this.isHovering(settingFromX, n) && Mouse.isButtonDown(0)) {
            this.setSettingFromX(settingFromX);
        }
    }

    private void setSettingFromX(final int n) {
        double n2 = this.setting.getMin().doubleValue() + this.difference * (double)((n - this.x) / (this.width + 7.4f));
        if (this.setting.isFloating()) {
            if (this.difference <= 1.0) {
                n2 = MathUtil.round(n2, 2);
            }
            else {
                n2 = MathUtil.round(n2, 1);
            }
        }
        this.setting.setValue(this.setting.numberToValue(n2));
    }
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    private float part() {
        return this.setting.getValue().floatValue() - this.min.floatValue();
    }
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
}
