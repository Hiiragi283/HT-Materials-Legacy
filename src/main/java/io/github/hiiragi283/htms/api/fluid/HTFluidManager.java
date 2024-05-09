package io.github.hiiragi283.htms.api.fluid;

import net.minecraftforge.fluids.Fluid;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import io.github.hiiragi283.htms.api.material.HTMaterialKey;

public final class HTFluidManager {

    public HTFluidManager(Builder builder) {}

    public static final class Builder {

        Table<HTMaterialKey, HTFluidType, Fluid> fluidTable = HashBasedTable.create();

        public Builder add(HTMaterialKey key, HTFluidType type, Fluid fluid) {
            fluidTable.put(key, type, fluid);
            return this;
        }

        public Builder remove(HTMaterialKey key, HTFluidType type) {
            fluidTable.remove(key, type);
            return this;
        }
    }
}
