package com.github.lipinskipawel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.lipinskipawel.deserializer.FlatDeserializer;
import com.github.lipinskipawel.objects.NestedObject.Inner;
import com.github.lipinskipawel.objects.FlatObject;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.empty;

final class JsonUtils_FlatObjectTest implements WithAssertions {

    private final ObjectMapper mapper = mapper();

    private static ObjectMapper mapper() {
        return new ObjectMapper()
                .registerModule(new SimpleModule()
                        .addDeserializer(FlatObject.class, new FlatDeserializer()));
    }

    @Test
    void without_optional_fields() throws IOException {
        final var rootNode = mapper.createObjectNode();

        rootNode.put("string", "some-string");
        rootNode.put("int", 3);
        rootNode.put("string-list", mapper.createArrayNode().add("sdf").add("sddfsdfsf"));
        rootNode.put("string-set", mapper.createArrayNode().add("sdf").add("234"));

        final var result = mapper.readValue(rootNode.toString(), FlatObject.class);

        assertThat(result)
                .usingRecursiveAssertion()
                .isEqualTo(new FlatObject("some-string", 3, empty(), List.of("sdf", "sddfsdfsf"), Set.of("sdf", "234")));
    }

    @Test
    void with_optional_fields() throws IOException {
        final var rootNode = mapper.createObjectNode();

        rootNode.put("string", "some-string");
        rootNode.put("int", 3);
        rootNode.put("optional-float", 3f);
        rootNode.put("string-list", mapper.createArrayNode().add("sdf").add("sddfsdfsf"));
        rootNode.put("string-set", mapper.createArrayNode().add("sdf").add("234"));

        final var result = mapper.readValue(rootNode.toString(), FlatObject.class);

        assertThat(result)
                .usingRecursiveAssertion()
                .isEqualTo(new FlatObject("some-string", 3, Optional.of(3f), List.of("sdf", "sddfsdfsf"), Set.of("sdf", "234")));
    }

    @Test
    void testing_example_object() throws JsonProcessingException {
        final var example = """
                {"innerString":"optional inner value"}""";

        final var result = mapper.readValue(example, Inner.class);

        assertThat(result).isEqualTo(new Inner("optional inner value"));
    }
}
