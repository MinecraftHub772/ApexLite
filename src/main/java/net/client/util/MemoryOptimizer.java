package net.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class MemoryOptimizer {

    public static void purgeMemoryOnWorldSwitch() {
        System.runFinalization();
        System.gc();
    }

    public static void applyLowRAMOptiFineSettings() {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings gs = mc.gameSettings;

        gs.renderDistanceChunks = 4;
        gs.enableVsync = false;
        gs.fancyGraphics = false;
        gs.ambientOcclusion = 0;
        gs.clouds = 0;
        gs.particleSetting = 2;

        try {
            setOptiFineField(gs, "ofTrees", 1);
            setOptiFineField(gs, "ofDroppedItems", 1);
            setOptiFineField(gs, "ofSmartAnimations", true);
            setOptiFineField(gs, "ofSmoothFps", false);
            setOptiFineField(gs, "ofFastMath", true);
            setOptiFineField(gs, "ofFastRender", true);
            setOptiFineField(gs, "ofChunkUpdates", 1);
        } catch (Exception ignored) {
            // Vanilla fallback handling
        }
    }

    private static void setOptiFineField(GameSettings gs, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = gs.getClass().getField(fieldName);
            field.set(gs, value);
        } catch (Exception ignored) {}
    }
}
