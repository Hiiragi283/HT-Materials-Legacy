package io.github.hiiragi283.mixin;

import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = OreDictionary.class, remap = false)
public interface OreDictionaryAccessor {

    @Accessor
    static boolean getHasInit() {
        throw new AssertionError();
    }

    @Accessor("hasInit")
    static void setHasInit(boolean hasInit) {
        throw new AssertionError();
    }

    @Invoker("initVanillaEntries")
    static void invokeInitVanillaEntries() {
        throw new AssertionError();
    }

}