package io.github.hiiragi283.api.extension;

import java.util.function.Consumer;

import net.minecraft.util.NonNullList;

import org.jetbrains.annotations.NotNull;

import com.google.gson.JsonObject;

public final class ObjectBuilderUtil {

    private ObjectBuilderUtil() {}

    @NotNull
    private static <T> T buildObject(@NotNull T instance, @NotNull Consumer<T> consumer) {
        consumer.accept(instance);
        return instance;
    }

    @NotNull
    public static JsonObject buildJson(@NotNull Consumer<JsonObject> consumer) {
        return buildObject(new JsonObject(), consumer);
    }

    @NotNull
    public static <T> NonNullList<T> buildNonNullList(@NotNull Consumer<NonNullList<T>> consumer) {
        return buildObject(NonNullList.create(), consumer);
    }
}
