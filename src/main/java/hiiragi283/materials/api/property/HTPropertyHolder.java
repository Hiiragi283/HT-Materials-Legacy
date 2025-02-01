package hiiragi283.materials.api.property;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;

public interface HTPropertyHolder {
    static @NotNull HTPropertyHolder empty() {
        return Empty.INSTANCE;
    }

    <T> @Nullable T getProperty(@NotNull final HTPropertyKey<T> key);

    default <T> @NotNull Optional<T> getOptional(@NotNull final HTPropertyKey<T> key) {
        return Optional.ofNullable(getProperty(key));
    }

    default <T> @NotNull T getPropertyOrDefault(@NotNull final HTPropertyKey<T> key, @NotNull final T defaultValue) {
        return getOptional(key).orElse(defaultValue);
    }

    default <T> @NotNull T getPropertyOrThrow(@NotNull final HTPropertyKey<T> key) {
        return getOptional(key).orElseThrow(IllegalStateException::new);
    }

    boolean hasProperty(@NotNull final HTPropertyKey<?> key);

    enum Empty implements HTPropertyHolder {
        INSTANCE;

        @Override
        public <T> @Nullable T getProperty(@NotNull HTPropertyKey<T> key) {
            return null;
        }

        @Override
        public boolean hasProperty(@NotNull HTPropertyKey<?> key) {
            return false;
        }
    }
}
