package me.earth.plugins.phobosgui.gui.components;

import me.earth.earthhack.api.util.interfaces.Globals;

public class Item implements Globals
{
    private final String name;
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    private boolean hidden;
    
    public Item(final String name) {
        super();
        this.name = name;
    }
    
    public void setLocation(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
    }
    
    public void update() {
    }
    
    public void onKeyTyped(final char c, final int n) {
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public String getName() {
        return this.name;
    }
}
