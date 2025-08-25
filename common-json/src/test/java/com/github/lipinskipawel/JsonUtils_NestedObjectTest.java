package com.github.lipinskipawel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.lipinskipawel.deserializer.NestedDeserializer;
import com.github.lipinskipawel.objects.NestedObject;
import com.github.lipinskipawel.objects.NestedObject.Inner;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.empty;

final class JsonUtils_NestedObjectTest implements WithAssertions {

    private final ObjectMapper mapper = mapper();

    private static ObjectMapper mapper() {
        return new ObjectMapper()
                .registerModule(new SimpleModule()
                        .addDeserializer(NestedObject.class, new NestedDeserializer()));
    }

    @Test
    void nested_objects_without_optional() throws IOException {
        final var rootNode = mapper.createObjectNode();

        rootNode.put("something", "some-string");
        rootNode.put("interesting", 897);

        final var inner = mapper.createObjectNode();
        inner.put("innerString", "inner value of object");
        rootNode.put("inner", inner);

        final var result = mapper.readValue(rootNode.toString(), NestedObject.class);

        assertThat(result)
                .usingRecursiveAssertion()
                .isEqualTo(new NestedObject(new Inner("inner value of object"), empty(), "some-string", 897));
    }

    @Test
    void nested_objects_with_optional() throws IOException {
        final var rootNode = mapper.createObjectNode();

        final var inner = mapper.createObjectNode();
        inner.put("innerString", "inner value of object");
        rootNode.put("inner", inner);

        final var optionalInner = mapper.createObjectNode();
        optionalInner.put("innerString", "optional inner value");
        rootNode.put("optionalInner", optionalInner);

        rootNode.put("something", "some-string");
        rootNode.put("interesting", 897);

        final var result = mapper.readValue(rootNode.toString(), NestedObject.class);

        assertThat(result)
                .usingRecursiveAssertion()
                .isEqualTo(new NestedObject(
                        new Inner("inner value of object"),
                        Optional.of(new Inner("optional inner value")),
                        "some-string",
                        897
                ));
    }
}
