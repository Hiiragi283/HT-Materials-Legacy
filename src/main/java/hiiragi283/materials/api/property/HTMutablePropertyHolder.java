package hiiragi283.materials.api.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public interface HTMutablePropertyHolder extends HTPropertyHolder {
    <T> @Nullable T setProperty(@NotNull final HTPropertyKey<T> key, @NotNull final T value);

    default <T> @Nullable T setPropertyIfNonNull(@NotNull final HTPropertyKey<T> key, @Nullable final T value) {
        if (value == null) return null;
        return setProperty(key, value);
    }

    <T> @Nullable T remove(@NotNull final HTPropertyKey<T> key);

    default <T> @Nullable T removeIf(@NotNull final HTPropertyKey<T> key, @NotNull final Predicate<? super T> predicate) {
        var valueExist = getProperty(key);
        if (valueExist == null) return null;
        if (predicate.test(valueExist)) {
            return remove(key);
        }
        return null;
    }
}
