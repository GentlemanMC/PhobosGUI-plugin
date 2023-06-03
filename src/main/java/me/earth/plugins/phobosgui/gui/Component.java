// Class Version: 8
package me.earth.plugins.phobosgui.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.earth.earthhack.api.util.interfaces.Globals;
import me.earth.earthhack.impl.util.math.MathUtil;
import me.earth.plugins.phobosgui.PhobosColorManager;
import me.earth.plugins.phobosgui.PhobosColorModule;
import me.earth.plugins.phobosgui.PhobosGuiModule;
import me.earth.plugins.phobosgui.PhobosTextManager;
import me.earth.plugins.phobosgui.gui.PhobosGui;
import me.earth.plugins.phobosgui.gui.components.Button;
import me.earth.plugins.phobosgui.gui.components.Item;
import me.earth.plugins.phobosgui.util.PhobosColorUtil;
import me.earth.plugins.phobosgui.util.PhobosRenderUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import org.lwjgl.opengl.GL11;

public class Component
        implements Globals {
    private final String name;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private int width;
    private int height;
    private boolean open;
    public boolean drag;
    private final List<Item> items = new ArrayList<Item>();
    private boolean hidden = false;

    public Component(String string, int n, int n2, boolean bl) {
        this.name = string;
        this.x = n;
        this.y = n2;
        this.width = 88;
        this.height = 18;
        this.open = bl;
        this.setupItems();
    }

    public void setupItems() {
    }

    private void drag(int n, int n2) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + n;
        this.y = this.y2 + n2;
    }

    public void drawScreen(int n, int n2, float f) {
        this.drag(n, n2);
        float f2 = this.open ? this.getTotalItemHeight() - 2.0f : 0.0f;
        int n3 = -7829368;
        if (((Boolean)PhobosGuiModule.getInstance().devSettings.getValue()).booleanValue()) {
            int n4 = n3 = (Boolean)PhobosGuiModule.getInstance().colorSync.getValue() != false ? PhobosColorModule.getInstance().getCurrentColorHex() : PhobosColorUtil.toARGB((Integer)PhobosGuiModule.getInstance().topRed.getValue(), (Integer)PhobosGuiModule.getInstance().topGreen.getValue(), (Integer)PhobosGuiModule.getInstance().topBlue.getValue(), (Integer)PhobosGuiModule.getInstance().topAlpha.getValue());
        }
        if (((Boolean)PhobosGuiModule.getInstance().rainbowRolling.getValue()).booleanValue() && ((Boolean)PhobosGuiModule.getInstance().colorSync.getValue()).booleanValue() && ((Boolean)PhobosColorModule.getInstance().rainbow.getValue()).booleanValue()) {
            PhobosRenderUtil.drawGradientRect(this.x, (int)((float)this.y - 1.5f), this.width, this.height - 4, (int)PhobosGuiModule.getInstance().colorMap.getOrDefault(MathUtil.clamp(this.y, 0, PhobosTextManager.getInstance().scaledHeight), -1), (int)PhobosGuiModule.getInstance().colorMap.getOrDefault(MathUtil.clamp(this.y + this.height - 4, 0, PhobosTextManager.getInstance().scaledHeight), -1));
        } else {
            PhobosRenderUtil.drawRect(this.x, (float)this.y - 1.5f, this.x + this.width, this.y + this.height - 6, n3);
        }
        if (this.open) {
            PhobosRenderUtil.drawRect(this.x, (float)this.y + 12.5f, this.x + this.width, (float)(this.y + this.height) + f2, 0x77000000);
            if (((Boolean)PhobosGuiModule.getInstance().outline.getValue()).booleanValue()) {
                Color color;
                if (((Boolean)PhobosGuiModule.getInstance().rainbowRolling.getValue()).booleanValue()) {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel((int)7425);
                    GL11.glBegin(1);
                    color = new Color(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, PhobosTextManager.getInstance().scaledHeight)));
                    GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
                    GL11.glVertex3f(this.x + this.width, (float)this.y - 1.5f, 0.0f);
                    GL11.glVertex3f(this.x, (float)this.y - 1.5f, 0.0f);
                    GL11.glVertex3f(this.x, (float)this.y - 1.5f, 0.0f);
                    float f3 = (float)this.getHeight() - 1.5f;
                    for (Item item : this.getItems()) {
                        color = new Color(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)((float)this.y + (f3 += (float)item.getHeight() + 1.5f)), 0, PhobosTextManager.getInstance().scaledHeight)));
                        GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
                        GL11.glVertex3f(this.x, (float)this.y + f3, 0.0f);
                        GL11.glVertex3f(this.x, (float)this.y + f3, 0.0f);
                    }
                    color = new Color(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)((float)(this.y + this.height) + f2), 0, PhobosTextManager.getInstance().scaledHeight)));
                    GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
                    GL11.glVertex3f(this.x + this.width, (float)(this.y + this.height) + f2, 0.0f);
                    GL11.glVertex3f(this.x + this.width, (float)(this.y + this.height) + f2, 0.0f);
                    for (Item item : this.getItems()) {
                        color = new Color(PhobosGuiModule.getInstance().colorMap.get(MathUtil.clamp((int)((float)this.y + (f3 -= (float)item.getHeight() + 1.5f)), 0, PhobosTextManager.getInstance().scaledHeight)));
                        GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
                        GL11.glVertex3f(this.x + this.width, (float)this.y + f3, 0.0f);
                        GL11.glVertex3f(this.x + this.width, (float)this.y + f3, 0.0f);
                    }
                    GL11.glVertex3f(this.x + this.width, this.y, 0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel((int)7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                } else {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel((int)7425);
                    GL11.glBegin(2);
                    color = (Boolean)PhobosGuiModule.getInstance().colorSync.getValue() != false ? new Color(PhobosColorModule.getInstance().getCurrentColorHex()) : new Color(PhobosColorManager.getInstance().getColorAsIntFullAlpha());
                    GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                    GL11.glVertex3f(this.x, (float)this.y - 1.5f, 0.0f);
                    GL11.glVertex3f(this.x + this.width, (float)this.y - 1.5f, 0.0f);
                    GL11.glVertex3f(this.x + this.width, (float)(this.y + this.height) + f2, 0.0f);
                    GL11.glVertex3f(this.x, (float)(this.y + this.height) + f2, 0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel((int)7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                }
            }
        }
        PhobosTextManager.getInstance().drawStringWithShadow(this.name, (float)this.x + 3.0f, (float)this.y - 4.0f - (float)PhobosGui.getInstance().getTextOffset(), -1);
        if (this.open) {
            float f4 = (float)(this.getY() + this.getHeight()) - 3.0f;
            for (Item item : this.getItems()) {
                if (item.isHidden()) continue;
                item.setLocation((float)this.x + 2.0f, f4);
                item.setWidth(this.getWidth() - 4);
                item.drawScreen(n, n2, f);
                f4 += (float)item.getHeight() + 1.5f;
            }
        }
    }

    public void mouseClicked(int n, int n2, int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.x2 = this.x - n;
            this.y2 = this.y - n2;
            PhobosGui.getInstance().getComponents().forEach(component -> {
                if (component.drag) {
                    component.drag = false;
                }
            });
            this.drag = true;
            return;
        }
        if (n3 == 1 && this.isHovering(n, n2)) {
            this.open = !this.open;
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseClicked(n, n2, n3));
    }

    public void mouseReleased(int n, int n2, int n3) {
        if (n3 == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseReleased(n, n2, n3));
    }

    public void onKeyTyped(char c, int n) {
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.onKeyTyped(c, n));
    }

    public void addButton(Button button) {
        this.items.add(button);
    }

    public void setX(int n) {
        this.x = n;
    }

    public void setY(int n) {
        this.y = n;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int n) {
        this.height = n;
    }

    public void setWidth(int n) {
        this.width = n;
    }

    public void setHidden(boolean bl) {
        this.hidden = bl;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public boolean isOpen() {
        return this.open;
    }

    public final List<Item> getItems() {
        return this.items;
    }

    private boolean isHovering(int n, int n2) {
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
    }

    private float getTotalItemHeight() {
        float f = 0.0f;
        for (Item item : this.getItems()) {
            f += (float)item.getHeight() + 1.5f;
        }
        return f;
    }

    public String getName() {
        return this.name;
    }
}
