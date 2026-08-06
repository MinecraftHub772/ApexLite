# ApexLite Client (1.8.9)

An ultra-lightweight Vanilla + OptiFine LaunchWrapper tweak client designed for PojavLauncher and low-RAM devices (2GB RAM target).

## Features
- Zero-GC primitive array-based module management (30 modules).
- Built directly on LaunchWrapper without Forge overhead.
- Memory purge triggers for world switches.
- Lightweight custom ClickGUI mapped to `Right Shift` (`KEY_RSHIFT`).

## Launch Setup (PojavLauncher)
1. Install Minecraft 1.8.9 + OptiFine HDF5.
2. Place the compiled `ApexLite-1.0.0.jar` in your PojavLauncher game/library directory.
3. Pass the JVM launch argument:
   `--tweakClass net.client.launch.ClientTweaker`
