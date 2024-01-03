package io.github.hiiragi283.material.util;

import com.google.gson.JsonObject;
import io.github.hiiragi283.material.HMReference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class HTUtils {

    private HTUtils() {
    }

    @NotNull
    public static <T extends Event> T postAndReturnEvent(@NotNull T event) {
        MinecraftForge.EVENT_BUS.post(event);
        return event;
    }

    @NotNull
    public static <T, U extends Event> T wrapAndPostEvent(@NotNull T obj, @NotNull Function<T, U> wrapper) {
        MinecraftForge.EVENT_BUS.post(wrapper.apply(obj));
        return obj;
    }

    @NotNull
    public static String joinToString(String delimiter, Stream<String> stream) {
        StringJoiner sj = new StringJoiner(delimiter);
        stream.forEach(sj::add);
        return sj.toString();
    }

    @Nullable
    public static ModContainer getActiveModContainer() {
        return Loader.instance().activeModContainer();
    }

    public static boolean isHTMaterialsActive() {
        ModContainer activeMod = getActiveModContainer();
        return activeMod != null && activeMod.getModId().equals(HMReference.MOD_ID);
    }

    @NotNull
    public static JsonObject buildJson(Consumer<JsonObject> consumer) {
        JsonObject jsonObject = new JsonObject();
        consumer.accept(jsonObject);
        return jsonObject;
    }

}