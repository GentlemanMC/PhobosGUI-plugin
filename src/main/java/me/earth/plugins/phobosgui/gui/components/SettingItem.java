package me.earth.plugins.phobosgui.gui.components;

import me.earth.earthhack.api.setting.Setting;

public class SettingItem extends Item
{
    private final Setting<?> theSetting;
    
    public SettingItem(final String s, final Setting<?> theSetting) {
        super(s);
        this.theSetting = theSetting;
    }
    
    public Setting<?> getTheSetting() {
        return this.theSetting;
    }
}
