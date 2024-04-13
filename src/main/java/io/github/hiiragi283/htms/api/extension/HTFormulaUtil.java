package io.github.hiiragi283.htms.api.extension;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

public final class HTFormulaUtil {

    private HTFormulaUtil() {}

    public static <T> @NotNull String formatFormula(@NotNull Map<T, Integer> map,
                                                    @NotNull Function<T, String> formulaFunction) {
        return formatFormula(HTMapUtil.mapKeys(map, formulaFunction));
    }

    public static @NotNull String formatFormula(@NotNull Map<String, Integer> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            builder.append(entry.getKey());
            Integer weight = entry.getValue();
            // 値が1の場合はパス
            if (weight == 1) continue;
            // 化学式の下付き数字の桁数調整
            char subscript0 = (char) 2080;
            char subscript1 = (char) (2080 + (weight % 10));
            char subscript10 = (char) (2080 + (weight / 10));
            // 2桁目が0でない場合，下付き数字を2桁にする
            StringBuilder subBuilder = new StringBuilder();
            if (!Objects.equals(subscript10, subscript0)) subBuilder.append(subscript10);
            subBuilder.append(subscript1);
            builder.append(subBuilder);
        }
        return builder.toString();
    }
}
