package com.github.lipinskipawel.parser.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.lipinskipawel.parser.objects.NestedObject;
import com.github.lipinskipawel.parser.objects.NestedObject.Inner;

import java.io.IOException;

import static com.github.lipinskipawel.parser.JsonUtils.parseNode;
import static com.github.lipinskipawel.parser.JsonUtils.parseOptionalNode;

public final class NestedDeserializer extends JsonDeserializer<NestedObject> {

    @Override
    public NestedObject deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        final JsonNode tree = parser.getCodec().readTree(parser);

        final var inner = parseNode(ctx, tree, "inner", Inner.class);
        final var optionalInner = parseOptionalNode(ctx, tree, "optionalInner", Inner.class);
        final var something = parseNode(ctx, tree, "something", String.class);
        final var interesting = parseNode(ctx, tree, "interesting", Integer.class);

        return new NestedObject(
                inner,
                optionalInner,
                something,
                interesting
        );
    }
}
