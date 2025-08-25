package com.github.lipinskipawel.objects;

import java.util.Optional;

public record NestedObject(
        Inner inner,
        Optional<Inner> optionalInner,
        String something,
        int interesting
) {

    public record Inner(
            String innerString
    ) {
    }
}
