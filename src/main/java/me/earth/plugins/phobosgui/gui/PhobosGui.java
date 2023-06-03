// Class Version: 8
package me.earth.plugins.phobosgui.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.impl.managers.Managers;
import me.earth.plugins.phobosgui.PhobosGuiModule;
import me.earth.plugins.phobosgui.gui.Component;
import me.earth.plugins.phobosgui.gui.components.Item;
import me.earth.plugins.phobosgui.gui.components.buttons.ModuleButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

public class PhobosGui
        extends GuiScreen {
    private static PhobosGui instance;
    private final List<Component> components = new ArrayList<Component>();

    public PhobosGui() {
        instance = this;
        this.load();
    }

    public static PhobosGui getInstance() {
        if (instance == null) {
            instance = new PhobosGui();
        }
        return instance;
    }

    public void load() {
        int n = -84;
        for (final Category category : Category.values()) {
            this.components.add(new Component(category.name(), n += 90, 4, true){

                @Override
                public void setupItems() {
                    Managers.MODULES.getRegistered().forEach(module -> {
                        if (module.getCategory() == category) {
                            this.addButton(new ModuleButton((Module)module));
                        }
                    });
                }
            });
        }
        this.components.forEach(component -> component.getItems().sort(Comparator.comparing(Item::getName)));
    }

    public void drawScreen(int n, int n2, float f) {
        PhobosGuiModule.getInstance().onRender2D();
        this.checkMouseWheel();
        this.drawDefaultBackground();
        this.components.forEach(component -> component.drawScreen(n, n2, f));
    }

    public void mouseClicked(int n, int n2, int n3) {
        this.components.forEach(component -> component.mouseClicked(n, n2, n3));
    }

    public void mouseReleased(int n, int n2, int n3) {
        this.components.forEach(component -> component.mouseReleased(n, n2, n3));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void keyTyped(char c, int n) throws IOException {
        super.keyTyped(c, n);
        this.components.forEach(component -> component.onKeyTyped(c, n));
    }

    private void checkMouseWheel() {
        int n = Mouse.getDWheel();
        if (n < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        } else if (n > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public int getTextOffset() {
        return -6;
    }
}
