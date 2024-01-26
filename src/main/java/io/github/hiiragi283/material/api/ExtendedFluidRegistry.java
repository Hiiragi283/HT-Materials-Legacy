package io.github.hiiragi283.material.api;

import java.util.HashSet;
import java.util.Set;

import net.minecraftforge.fluids.Fluid;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ExtendedFluidRegistry {

    private static final BiMap<String, Fluid> masterFluidMap = HashBiMap.create();
    private static final BiMap<String, Fluid> defaultFluidMap = HashBiMap.create();
    private static final BiMap<Fluid, Integer> fluidIdMap = HashBiMap.create();

    private static final Set<String> bucketFluids = new HashSet<>();

    private ExtendedFluidRegistry() {}
}
