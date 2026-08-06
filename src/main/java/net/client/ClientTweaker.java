package net.client.launch;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClientTweaker implements ITweaker {
    private final List<String> args = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        if (args != null) {
            this.args.addAll(args);
        }
        
        if (gameDir != null && !this.args.contains("--gameDir")) {
            this.args.add("--gameDir");
            this.args.add(gameDir.getAbsolutePath());
        }
        if (assetsDir != null && !this.args.contains("--assetsDir")) {
            this.args.add("--assetsDir");
            this.args.add(assetsDir.getAbsolutePath());
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        // Exclude launch package from classloader transformation loops
        classLoader.addClassLoaderExclusion("net.client.launch.");
        
        // Register client ASM transformer
        classLoader.registerTransformer("net.client.asm.ClientTransformer");
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return args.toArray(new String[0]);
    }
}
