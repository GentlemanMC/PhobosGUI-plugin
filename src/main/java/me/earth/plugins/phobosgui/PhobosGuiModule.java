// Class Version: 8
package me.earth.plugins.phobosgui;

import me.earth.earthhack.api.event.bus.EventListener;
import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.api.setting.settings.StringSetting;
import me.earth.earthhack.api.util.interfaces.Globals;
import me.earth.earthhack.impl.event.events.render.GuiScreenEvent;
import me.earth.earthhack.impl.event.events.render.Render2DEvent;
import me.earth.plugins.phobosgui.gui.PhobosGui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PhobosGuiModule
        extends Module {
    private static final PhobosGuiModule INSTANCE = new PhobosGuiModule();
    public Setting<Boolean> colorSync = this.register(new BooleanSetting("Sync", true));
    public Setting<Boolean> outline = this.register(new BooleanSetting("Outline", false));
    public Setting<Boolean> rainbowRolling = this.register(new BooleanSetting("RollingRainbow", true));
    public Setting<Integer> hoverAlpha = this.register(new NumberSetting("Alpha", 180, 0, 255));
    public Setting<Integer> alpha = this.register(new NumberSetting("HoverAlpha", 240, 0, 255));
    public Setting<Boolean> openCloseChange = this.register(new BooleanSetting("Open/Close", true));
    public Setting<String> open = this.register(new StringSetting("Open:", "-"));
    public Setting<String> close = this.register(new StringSetting("Close:", "+"));
    public Setting<String> moduleButton = this.register(new StringSetting("Buttons:", ""));
    public Setting<Boolean> devSettings = this.register(new BooleanSetting("DevSettings", true));
    public Setting<Integer> topRed = this.register(new NumberSetting("TopRed", 255, 0, 255));
    public Setting<Integer> topGreen = this.register(new NumberSetting("TopGreen", 0, 0, 255));
    public Setting<Integer> topBlue = this.register(new NumberSetting("TopBlue", 0, 0, 255));
    public Setting<Integer> topAlpha = this.register(new NumberSetting("TopAlpha", 255, 0, 255));
    public Setting<Boolean> rainbow = this.register(new BooleanSetting("Rainbow", false));
    public Setting<Integer> factor = this.register(new NumberSetting("Factor", 1, 0, 20));
    public Setting<Integer> rainbowSpeed = this.register(new NumberSetting("RSpeed", 20, 0, 100));
    public Setting<Integer> rainbowSaturation = this.register(new NumberSetting("Saturation", 255, 0, 255));
    public Setting<Integer> rainbowBrightness = this.register(new NumberSetting("Brightness", 255, 0, 255));
    public final Map<Integer, Integer> colorMap = new HashMap<Integer, Integer>();

    private PhobosGuiModule() {
        super("PhobosGui", Category.Client);
        this.listeners.add(new EventListener<GuiScreenEvent<?>>(GuiScreenEvent.class){

            public void invoke(GuiScreenEvent<?> guiScreenEvent) {
                if (Globals.mc.currentScreen instanceof PhobosGui) {
                    PhobosGuiModule.this.disable();
                }
            }
        });
        this.listeners.add(new EventListener<Render2DEvent>(Render2DEvent.class){

            public void invoke(Render2DEvent render2DEvent) {
                PhobosGuiModule.this.onRender2D();
            }
        });
    }

    public static PhobosGuiModule getInstance() {
        return INSTANCE;
    }

    protected void onEnable() {
        PhobosGui phobosGui = new PhobosGui();
        System.out.println(mc + "");
        mc.displayGuiScreen(phobosGui);
    }

    protected void onDisable() {
        if (PhobosGuiModule.mc.currentScreen instanceof PhobosGui) {
            mc.displayGuiScreen(null);
        }
    }

    public void onRender2D() {
        int n = 101 - (Integer)this.rainbowSpeed.getValue();
        float f = (Boolean)this.colorSync.getValue() != false ? PhobosColorModule.getInstance().hue : (float)(System.currentTimeMillis() % (long)(360 * n)) / (360.0f * (float)n);
        int n2 = PhobosTextManager.getInstance().scaledHeight;
        float f2 = f;
        for (int i = 0; i <= n2; ++i) {
            if (((Boolean)this.colorSync.getValue()).booleanValue()) {
                this.colorMap.put(i, Color.HSBtoRGB(f2, (float)((Integer)PhobosColorModule.getInstance().rainbowSaturation.getValue()).intValue() / 255.0f, (float)((Integer)PhobosColorModule.getInstance().rainbowBrightness.getValue()).intValue() / 255.0f));
            } else {
                this.colorMap.put(i, Color.HSBtoRGB(f2, (float)((Integer)this.rainbowSaturation.getValue()).intValue() / 255.0f, (float)((Integer)this.rainbowBrightness.getValue()).intValue() / 255.0f));
            }
            f2 += 1.0f / (float)n2 * (float)((Integer)this.factor.getValue()).intValue();
        }
    }
}
