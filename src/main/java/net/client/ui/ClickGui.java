package net.client.ui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class ClickGui extends GuiScreen {

    // Simple placeholder array for categories if not defined globally
    private String[] categoryNames = new String[] { "Combat", "Movement", "Render", "Player" };
    private int panelWidth = 100;
    private int elementHeight = 18;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // 1. Draw dark background overlay across the full screen
        Gui.drawRect(0, 0, this.width, this.height, 0x60000000);

        int x = 20;
        int y = 20;

        // 2. Draw category header panel
        for (int cat = 0; cat < categoryNames.length; cat++) {
            Gui.drawRect(x, y, x + panelWidth, y + elementHeight, 0xFF1E1E24);
            
            if (this.fontRendererObj != null) {
                this.fontRendererObj.drawStringWithShadow(categoryNames[cat], x + 4, y + 3, 0xFFFFFFFF);
            }
            
            x += panelWidth + 10;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}

