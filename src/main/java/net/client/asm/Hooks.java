package net.client.asm;

import net.client.module.ModuleManager;
import net.client.ui.ClickGui;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Hooks {
    public static void onTick() {
        Minecraft mc = Minecraft.getMinecraft();

        // Press Right-Shift (KEY_RSHIFT) when no menu is open to open ClickGUI
        if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) && mc.currentScreen == null) {
            mc.displayGuiScreen(new ClickGui());
        }

        ModuleManager.onTick();
    }

    public static void onRender(float partialTicks) {
        ModuleManager.onRender(partialTicks);
    }
}
