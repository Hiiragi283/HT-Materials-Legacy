package hiiragi283.materials.api.util;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class HTItemUtils {
    private HTItemUtils() {
    }

    public static boolean isNotEmpty(@NotNull final ItemStack stack) {
        return !stack.isEmpty();
    }
}
