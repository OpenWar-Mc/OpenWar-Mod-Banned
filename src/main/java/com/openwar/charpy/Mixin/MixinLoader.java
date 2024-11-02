package com.openwar.charpy.Mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinLoader implements IFMLLoadingPlugin, IMixinConnector {

    static {
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
        System.out.println("MixinLoader static block: Initializing");
    }

    public MixinLoader() {
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        System.out.println("MixinLoader constructor: Loading Mixins");
        Mixins.addConfiguration("mixins.openwarbanned.json");
    }

    @Override
    public void connect() {
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(java.util.Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}