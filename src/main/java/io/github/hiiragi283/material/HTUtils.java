package io.github.hiiragi283.material;

import java.util.StringJoiner;
import java.util.stream.Stream;

public class HTUtils {

    public static String joinToString(String delimiter, Stream<String> stream) {
        StringJoiner sj = new StringJoiner(delimiter);
        stream.forEach(sj::add);
        return sj.toString();
    }

}