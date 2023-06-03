package me.earth.plugins.phobosgui;

import me.earth.plugins.phobosgui.util.PhobosColorUtil;
import java.awt.Color;

public class PhobosColorManager
{
    private static final PhobosColorManager INSTANCE;
    private float red;
    private float green;
    private float blue;
    private float alpha;
    private Color color;
    
    private PhobosColorManager() {
        super();
        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;
        this.alpha = 1.0f;
        this.color = new Color(this.red, this.green, this.blue, this.alpha);
    }
    
    public static PhobosColorManager getInstance() {
        return PhobosColorManager.INSTANCE;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public int getColorAsInt() {
        return PhobosColorUtil.toRGBA(this.color);
    }
    
    public int getColorAsIntFullAlpha() {
        return PhobosColorUtil.toRGBA(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 255));
    }
    
    public int getColorWithAlpha(final int n) {
        return PhobosColorUtil.toRGBA(new Color(this.red, this.green, this.blue, n / 255.0f));
    }
    
    public void setColor(final float red, final float green, final float blue, final float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        this.updateColor();
    }
    
    public void updateColor() {
        this.setColor(new Color(this.red, this.green, this.blue, this.alpha));
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setColor(final int n, final int n2, final int n3, final int n4) {
        this.red = n / 255.0f;
        this.green = n2 / 255.0f;
        this.blue = n3 / 255.0f;
        this.alpha = n4 / 255.0f;
        this.updateColor();
    }
    
    public void setRed(final float red) {
        this.red = red;
        this.updateColor();
    }
    
    public void setGreen(final float green) {
        this.green = green;
        this.updateColor();
    }
    
    public void setBlue(final float blue) {
        this.blue = blue;
        this.updateColor();
    }
    
    public void setAlpha(final float alpha) {
        this.alpha = alpha;
        this.updateColor();
    }
    
    static {
        INSTANCE = new PhobosColorManager();
    }
}
