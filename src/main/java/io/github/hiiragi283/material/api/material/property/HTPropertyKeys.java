package io.github.hiiragi283.material.api.material.property;

public abstract class HTPropertyKeys {

    private HTPropertyKeys() {
    }

    static void init() {
    }

    public static final HTPropertyKey<HTCompoundProperty> COMPOUND = new HTPropertyKey<>("compound", HTCompoundProperty.class);

    public static final HTPropertyKey<HTGemProperty> GEM = new HTPropertyKey<>("gem", HTGemProperty.class);

    public static final HTPropertyKey<HTMetalProperty> METAL = new HTPropertyKey<>("metal", HTMetalProperty.class);

}