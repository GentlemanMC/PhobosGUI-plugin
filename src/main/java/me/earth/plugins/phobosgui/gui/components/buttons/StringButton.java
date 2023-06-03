package me.earth.plugins.phobosgui.gui.components.buttons;

import me.earth.earthhack.api.setting.settings.StringSetting;
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
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

public class StringButton extends Button
{
    private final StringSetting setting;
    public boolean isListening;
    private CurrentString currentString;
    
    public StringButton(final StringSetting setting) {
        super(setting.getName());
        this.currentString = new CurrentString("");
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
            PhobosTextManager.getInstance().drawStringWithShadow(this.currentString.getString() + PhobosTextManager.getInstance().getIdleSign(), this.x + 2.3f, this.y - 1.7f - PhobosGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else {
            PhobosTextManager.getInstance().drawStringWithShadow(this.setting.getValue(), this.x + 2.3f, this.y - 1.7f - PhobosGui.getInstance().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            StringButton.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            if (n == 1) {
                return;
            }
            if (n == 28) {
                this.enterString();
            }
            else if (n == 14) {
                this.setString(removeLastChar(this.currentString.getString()));
            }
            else {
                Label_0122: {
                    if (n == 47) {
                        if (!Keyboard.isKeyDown(157)) {
                            if (!Keyboard.isKeyDown(29)) {
                                break Label_0122;
                            }
                        }
                        try {
                            this.setString(this.currentString.getString() + Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
                        }
                        catch (final Exception ex) {
                            ex.printStackTrace();
                        }
                        return;
                    }
                }
                if (ChatAllowedCharacters.isAllowedCharacter(c)) {
                    this.setString(this.currentString.getString() + c);
                }
            }
        }
    }
    
    @Override
    public void update() {
        this.setHidden(!Visibilities.VISIBILITY_MANAGER.isVisible(this.setting));
    }
    
    private void enterString() {
        if (this.currentString.getString().isEmpty()) {
            this.setting.setValue(this.setting.getInitial());
        }
        else {
            this.setting.setValue(this.currentString.getString());
        }
        this.setString("");
        super.onMouseClick();
    }

    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    @Override
    public boolean getState() {
        return !this.isListening;
    }
    
    public void setString(final String s) {
        this.currentString = new CurrentString(s);
    }
    
    public static String removeLastChar(final String s) {
        String substring = "";
        if (s != null && s.length() > 0) {
            substring = s.substring(0, s.length() - 1);
        }
        return substring;
    }
    
    public static class CurrentString
    {
        private final String string;
        
        public CurrentString(final String string) {
            super();
            this.string = string;
        }
        
        public String getString() {
            return this.string;
        }
    }
}
