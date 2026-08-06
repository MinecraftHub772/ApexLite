package net.client.ui;

import net.client.module.ModuleManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class ClickGui extends GuiScreen {
    private final String[] categoryNames = {"Combat", "Movement", "Render", "Player", "World"};
    private final int panelWidth = 80;
    private final int elementHeight = 14;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(0, 0, this.width, this.height, 0x60000000);

        for (byte cat = 0; cat < categoryNames.length; cat++) {
            int x = 10 + (cat * (panelWidth + 5));
            int y = 10;

            drawRect(x, y, x + panelWidth, y + elementHeight, 0xFF1E1E24);
            fontRendererObj.drawStringWithShadow(categoryNames[cat], x + 4, y + 3, 0xFFFFFFFF);

            int moduleY = y + elementHeight;

            for (int i = 0; i < ModuleManager.COUNT; i++) {
                if (ModuleManager.categories[i] != cat || ModuleManager.names[i] == null) continue;

                boolean isHovered = mouseX >= x && mouseX <= x + panelWidth 
                                 && mouseY >= moduleY && mouseY <= moduleY + elementHeight;
                
                boolean active = ModuleManager.enabled[i];

                int bgColor = active ? (isHovered ? 0xFF2A72D0 : 0xFF1F5AAC) 
                                     : (isHovered ? 0xFF383838 : 0xFF252525);

                drawRect(x, moduleY, x + panelWidth, moduleY + elementHeight, bgColor);
                fontRendererObj.drawStringWithShadow(ModuleManager.names[i], x + 4, moduleY + 3, 
                        active ? 0xFFFFFFFF : 0xFFA0A0A0);

                moduleY += elementHeight;
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            for (byte cat = 0; cat < categoryNames.length; cat++) {
                int x = 10 + (cat * (panelWidth + 5));
                int moduleY = 10 + elementHeight;

                for (int i = 0; i < ModuleManager.COUNT; i++) {
                    if (ModuleManager.categories[i] != cat || ModuleManager.names[i] == null) continue;

                    if (mouseX >= x && mouseX <= x + panelWidth 
                     && mouseY >= moduleY && mouseY <= moduleY + elementHeight) {
                        ModuleManager.toggle(i);
                        return;
                    }
                    moduleY += elementHeight;
                }
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
