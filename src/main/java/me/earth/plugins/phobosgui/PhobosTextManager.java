// Class Version: 8
package me.earth.plugins.phobosgui;

import me.earth.earthhack.api.event.bus.EventListener;
import me.earth.earthhack.api.event.bus.SubscriberImpl;
import me.earth.earthhack.api.util.interfaces.Globals;
import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.util.math.StopWatch;
import net.minecraft.util.math.MathHelper;

public class PhobosTextManager
        extends SubscriberImpl
        implements Globals {
    private static final PhobosTextManager INSTANCE = new PhobosTextManager();
    public int scaledWidth;
    public int scaledHeight;
    public int scaleFactor;
    private final StopWatch idleTimer = new StopWatch();
    private boolean idling;

    private PhobosTextManager() {
        this.updateResolution();
        this.listeners.add(new EventListener<TickEvent>(TickEvent.class){

            public void invoke(TickEvent tickEvent) {
                PhobosTextManager.this.updateResolution();
            }
        });
    }

    public static PhobosTextManager getInstance() {
        return INSTANCE;
    }

    public void drawStringWithShadow(String string, float f, float f2, int n) {
        this.drawString(string, f, f2, n, true);
    }

    public void drawString(String string, float f, float f2, int n, boolean bl) {
        PhobosTextManager.mc.fontRenderer.drawString(string, f, f2, n, bl);
    }

    public int getStringWidth(String string) {
        return PhobosTextManager.mc.fontRenderer.getStringWidth(string);
    }

    public int getFontHeight() {
        return PhobosTextManager.mc.fontRenderer.FONT_HEIGHT;
    }

    public void updateResolution() {
        this.scaledWidth = PhobosTextManager.mc.displayWidth;
        this.scaledHeight = PhobosTextManager.mc.displayHeight;
        this.scaleFactor = 1;
        boolean bl = mc.isUnicode();
        int n = PhobosTextManager.mc.gameSettings.guiScale;
        if (n == 0) {
            n = 1000;
        }
        while (this.scaleFactor < n && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (bl && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        double d = (double)this.scaledWidth / (double)this.scaleFactor;
        double d2 = (double)this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(d);
        this.scaledHeight = MathHelper.ceil(d2);
    }

    public String getIdleSign() {
        if (this.idleTimer.passed(500L)) {
            this.idling = !this.idling;
            this.idleTimer.reset();
        }
        return this.idling ? "_" : "";
    }
}
