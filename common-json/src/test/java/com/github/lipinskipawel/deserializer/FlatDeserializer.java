package com.github.lipinskipawel.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.lipinskipawel.objects.FlatObject;

import java.io.IOException;

import static com.github.lipinskipawel.JsonUtils.parseListNode;
import static com.github.lipinskipawel.JsonUtils.parseNode;
import static com.github.lipinskipawel.JsonUtils.parseOptionalNode;
import static com.github.lipinskipawel.JsonUtils.parseSetNode;

public final class FlatDeserializer extends JsonDeserializer<FlatObject> {

    @Override
    public FlatObject deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        final JsonNode tree = parser.getCodec().readTree(parser);

        final var string = parseNode(ctx, tree, "string", String.class);
        final var integer = parseNode(ctx, tree, "int", Integer.class);
        final var optionalFloat = parseOptionalNode(ctx, tree, "optional-float", Float.class);
        final var stringList = parseListNode(ctx, tree, "string-list", String.class);
        final var stringSet = parseSetNode(ctx, tree, "string-set", String.class);

        return new FlatObject(
                string,
                integer,
                optionalFloat,
                stringList,
                stringSet
        );
    }
}
