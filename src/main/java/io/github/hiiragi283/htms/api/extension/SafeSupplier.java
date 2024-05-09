package io.github.hiiragi283.htms.api.extension;

import java.util.Optional;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SafeSupplier<T> extends Supplier<T> {

    @NotNull
    T getOrDefault();

    @Nullable
    default T getOrNull() {
        try {
            return get();
        } catch (Exception e) {
            return null;
        }
    }

    @NotNull
    default Optional<T> getOrEmpty() {
        return Optional.ofNullable(getOrNull());
    }
}
