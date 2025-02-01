package hiiragi283.materials.api.mateial.part;

import org.jetbrains.annotations.NotNull;

public final class DefaultParts {
    private DefaultParts() {
    }

    public static final @NotNull HTPart DUST = new HTSimplePart("dust");

    public static final @NotNull HTPart INGOT = new HTSimplePart("ingot");

    public static final @NotNull HTPart PLATE = new HTSimplePart("plate");
}
