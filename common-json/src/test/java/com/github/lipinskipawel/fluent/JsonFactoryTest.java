package com.github.lipinskipawel.fluent;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.github.lipinskipawel.fluent.JsonFactory.array;
import static com.github.lipinskipawel.fluent.JsonFactory.object;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;

class JsonFactoryTest {

    @Test
    void one_dimension_with_only_primitives() {
        final var jsonFactoryNode = object()
                .set("short", (short) 1)
                .set("Short", Short.valueOf((short) 1))
                .set("int", 2)
                .set("Integer", Integer.valueOf(2))
                .set("long", 3L)
                .set("Long", Long.valueOf(3))
                .set("float", 4.4f)
                .set("Float", Float.valueOf(4.4f))
                .set("double", 5.55d)
                .set("Double", Double.valueOf(5.55d))
                .set("BigDecimal", new BigDecimal("6.00006"))
                .set("BigInteger", new BigInteger("700007"))
                .set("String", "some-string")
                .set("boolean", true)
                .set("Boolean", Boolean.valueOf(false))
                .set("byte[]", new byte[]{1, 3, 4});

        assertThatJson(jsonFactoryNode.toString())
                .when(IGNORING_ARRAY_ORDER)
                .isEqualTo(json("""
                        {
                            "short": 1,
                            "Short": 1,
                            "int": 2,
                            "Integer": 2,
                            "long": 3,
                            "Long": 3,
                            "float": 4.4,
                            "Float": 4.4,
                            "double": 5.55,
                            "Double": 5.55,
                            "BigDecimal": 6.00006,
                            "BigInteger": 700007,
                            "String": "some-string",
                            "boolean": true,
                            "Boolean": false,
                            "byte[]": "AQME"
                        }"""));
    }

    @Test
    void one_dimension_with_array() {
        final var jsonFactoryNode = object()
                .set("shorts", array((short) 1, (short) 2, (short) 3))
                .set("ints", array(1, 2, 3))
                .set("longs", array(1L, 2L, 3L))
                .set("floats", array(3.0003f, 4.555f))
                .set("double", array(3d, 5d))
                .set("BigDecimals", array(new BigDecimal("3243.444"), BigDecimal.valueOf(23L)))
                .set("BigIntegers", array(new BigInteger("34324879"), BigInteger.valueOf(89987978L)))
                .set("Strings", array("some", "strings"))
                .set("bytes", array(new byte[]{3, 4}, new byte[]{8, 9}))
                .set("boolean", array(true, false))
                .set("arrays", array(array(1, 3), array("mixed", "arrays")))
                .set("object-arrays", array(
                        object().set("json-key", "json-value"),
                        object().set("another-json-key", "another-json-value")
                ));

        assertThatJson(jsonFactoryNode.toString())
                .when(IGNORING_ARRAY_ORDER)
                .isEqualTo(json("""
                        {
                            "shorts": [1, 2, 3],
                            "ints": [1, 2, 3],
                            "longs": [1, 2, 3],
                            "floats": [3.0003, 4.555],
                            "double": [3.0, 5.0],
                            "BigDecimals": [3243.444, 23],
                            "BigIntegers": [34324879, 89987978],
                            "Strings": ["some", "strings"],
                            "bytes": ["AwQ=", "CAk="],
                            "boolean": [true, false],
                            "arrays": [
                              [1, 3],
                              ["mixed", "arrays"]
                            ],
                            "object-arrays": [
                              { "json-key": "json-value" },
                              { "another-json-key": "another-json-value" }
                            ]
                        }"""));
    }

    @Test
    void two_dimensions() {
        final var jsonFactoryNode = object()
                .set("inner-object", object()
                        .set("some-key", "some-value")
                        .set("another-object", object()
                                .set("inner-key", "inner-value")));

        assertThatJson(jsonFactoryNode.toString())
                .when(IGNORING_ARRAY_ORDER)
                .isEqualTo(json("""
                        {
                            "inner-object": {
                              "some-key": "some-value",
                              "another-object": {
                                "inner-key": "inner-value"
                              }
                            }
                        }"""));
    }

    @Test
    void handling_nulls() {
        Short sh = null;
        Integer integer = null;
        Long lo = null;
        Float fl = null;
        Double dou = null;
        BigDecimal bigDecimal = null;
        BigInteger bigInteger = null;
        String string = null;
        Boolean bool = null;
        byte[] bytes = null;

        JsonNode nullArrayJsonNode = null;
        final var jsonFactoryNode = object()
                .set("Short", sh)
                .set("Integer", integer)
                .set("Long", lo)
                .set("Float", fl)
                .set("Double", dou)
                .set("BigDecimal", bigDecimal)
                .set("BigInteger", bigInteger)
                .set("String", string)
                .set("Boolean", bool)
                .set("bytes", bytes)
                .set("array", nullArrayJsonNode);

        assertThatJson(jsonFactoryNode.toString())
                .when(IGNORING_ARRAY_ORDER)
                .isEqualTo(json("""
                        {
                          "Short": null,
                          "Integer": null,
                          "Long": null,
                          "Float": null,
                          "Double": null,
                          "BigDecimal": null,
                          "BigInteger": null,
                          "String": null,
                          "Boolean": null,
                          "bytes": null,
                          "array": null
                        }"""));
    }
}
