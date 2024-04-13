package io.github.hiiragi283.htms.api.extension;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class HTMapUtil {

    private HTMapUtil() {}

    public static <K1, K2, V> Map<K2, V> mapKeys(Map<K1, V> map, Function<K1, K2> keyMapper) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(entry -> keyMapper.apply(entry.getKey()), Map.Entry::getValue));
    }

    public static <K, V1, V2> Map<K, V2> mapValues(Map<K, V1> map, Function<V1, V2> keyMapper) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> keyMapper.apply(entry.getValue())));
    }

    public static <K1, K2, V1, V2> Map<K2, V2> map(Map<K1, V1> map, Function<K1, K2> keyMapper,
                                                   Function<V1, V2> valueMapper) {
        return map.entrySet().stream().collect(Collectors.toMap(entry -> keyMapper.apply(entry.getKey()),
                entry -> valueMapper.apply(entry.getValue())));
    }
}
