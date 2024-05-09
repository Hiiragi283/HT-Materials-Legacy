package io.github.hiiragi283.htms.api.item;

import java.util.Optional;

import net.minecraft.item.Item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface IItemProvider {

    @NotNull
    Item asItem();

    @NotNull
    static Optional<IItemProvider> castOptional(Object object) {
        return Optional.ofNullable(cast(object));
    }

    @Nullable
    static IItemProvider cast(Object object) {
        return object instanceof IItemProvider ? (IItemProvider) object : null;
    }
}
