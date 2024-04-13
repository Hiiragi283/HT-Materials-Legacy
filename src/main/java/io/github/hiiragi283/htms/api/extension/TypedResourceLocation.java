package io.github.hiiragi283.htms.api.extension;

import java.util.Optional;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record TypedResourceLocation<T> (@NotNull ResourceLocation location, @NotNull Class<T> clazz) {

    public TypedResourceLocation(@NotNull String namespace, @NotNull String path, @NotNull Class<T> clazz) {
        this(new ResourceLocation(namespace, path), clazz);
    }

    @NotNull
    public Optional<T> castOptional(@Nullable Object object) {
        return Optional.ofNullable(cast(object));
    }

    @Nullable
    public T cast(@Nullable Object object) {
        return object != null ? clazz.isInstance(object) ? clazz.cast(object) : null : null;
    }
}
