package com.github.lipinskipawel.objects;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public record FlatObject(
        String string,
        int integer,
        Optional<Float> optionalFloat,
        List<String> stringList,
        Set<String> stringSet
) {
}
