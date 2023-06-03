// Class Version: 8
package me.earth.plugins.phobosgui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import me.earth.earthhack.api.event.bus.EventListener;
import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.plugins.phobosgui.PhobosColorManager;
import me.earth.plugins.phobosgui.PhobosGuiModule;
import me.earth.plugins.phobosgui.util.PhobosColorUtil;

public class PhobosColorModule
        extends Module {
    private static final PhobosColorModule INSTANCE = new PhobosColorModule();
    public Setting<Boolean> rainbow = this.register(new BooleanSetting("Rainbow", true));
    public Setting<Integer> rainbowSpeed = this.register(new NumberSetting("Speed", 80, 0, 100));
    public Setting<Integer> rainbowSaturation = this.register(new NumberSetting("Saturation", 255, 0, 255));
    public Setting<Integer> rainbowBrightness = this.register(new NumberSetting("Brightness", 255, 0, 255));
    public Setting<Integer> red = this.register(new NumberSetting("Red", 255, 0, 255));
    public Setting<Integer> green = this.register(new NumberSetting("Green", 255, 0, 255));
    public Setting<Integer> blue = this.register(new NumberSetting("Blue", 255, 0, 255));
    public Setting<Integer> alpha = this.register(new NumberSetting("Alpha", 255, 0, 255));
    public final Map<Integer, Integer> colorHeightMap = new HashMap<Integer, Integer>();
    public float hue;

    private PhobosColorModule() {
        super("PhobosColors", Category.Client);
        this.listeners.add(new EventListener<TickEvent>(TickEvent.class){

            public void invoke(TickEvent tickEvent) {
                PhobosColorModule.this.onTick();
            }
        });
        this.enable();
    }

    public static PhobosColorModule getInstance() {
        return INSTANCE;
    }

    private void onTick() {
        int n = 101 - (Integer)this.rainbowSpeed.getValue();
        float f = this.hue = (float)(System.currentTimeMillis() % (long)(360 * n)) / (360.0f * (float)n);
        for (int i = 0; i <= 510; ++i) {
            this.colorHeightMap.put(i, Color.HSBtoRGB(f, (float)((Integer)this.rainbowSaturation.getValue()).intValue() / 255.0f, (float)((Integer)this.rainbowBrightness.getValue()).intValue() / 255.0f));
            f += 0.0013071896f;
        }
        if (((Boolean)PhobosGuiModule.getInstance().colorSync.getValue()).booleanValue()) {
            PhobosColorManager.getInstance().setColor(PhobosColorModule.getInstance().getCurrentColor().getRed(), PhobosColorModule.getInstance().getCurrentColor().getGreen(), PhobosColorModule.getInstance().getCurrentColor().getBlue(), (Integer)PhobosGuiModule.getInstance().hoverAlpha.getValue());
        }
    }

    public int getCurrentColorHex() {
        if (((Boolean)this.rainbow.getValue()).booleanValue()) {
            return Color.HSBtoRGB(this.hue, (float)((Integer)this.rainbowSaturation.getValue()).intValue() / 255.0f, (float)((Integer)this.rainbowBrightness.getValue()).intValue() / 255.0f);
        }
        return PhobosColorUtil.toARGB((Integer)this.red.getValue(), (Integer)this.green.getValue(), (Integer)this.blue.getValue(), (Integer)this.alpha.getValue());
    }

    public Color getCurrentColor() {
        if (((Boolean)this.rainbow.getValue()).booleanValue()) {
            return Color.getHSBColor(this.hue, (float)((Integer)this.rainbowSaturation.getValue()).intValue() / 255.0f, (float)((Integer)this.rainbowBrightness.getValue()).intValue() / 255.0f);
        }
        return new Color((Integer)this.red.getValue(), (Integer)this.green.getValue(), (Integer)this.blue.getValue(), (Integer)this.alpha.getValue());
    }
}
