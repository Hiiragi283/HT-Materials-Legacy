package hiiragi283.materials.api.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class HTPropertyHolderBuilder {

    @NotNull
    private final Map<HTPropertyKey<?>, Object> delegatedMap;

    public HTPropertyHolderBuilder() {
        this(new HashMap<>());
    }

    public HTPropertyHolderBuilder(@NotNull Map<HTPropertyKey<?>, Object> delegatedMap) {
        this.delegatedMap = delegatedMap;
    }

    public <T> @NotNull HTPropertyHolderBuilder put(@NotNull final HTPropertyKey<T> key, @NotNull final T value) {
        delegatedMap.put(key, value);
        return this;
    }

    public @NotNull HTPropertyHolderBuilder remove(@NotNull final HTPropertyKey<?> key) {
        delegatedMap.remove(key);
        return this;
    }

    public @NotNull HTPropertyHolder build() {
        return new HTPropertyHolder() {
            @Override
            public <T> @Nullable T getProperty(@NotNull final HTPropertyKey<T> key) {
                return key.castOrNull(delegatedMap.get(key));
            }

            @Override
            public boolean hasProperty(@NotNull final HTPropertyKey<?> key) {
                return delegatedMap.containsKey(key);
            }
        };
    }
}
