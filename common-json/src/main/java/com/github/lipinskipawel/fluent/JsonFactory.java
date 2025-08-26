package com.github.lipinskipawel.fluent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonFactory {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonFactoryNode object() {
        return new JsonFactoryNode(objectMapper);
    }

    public static JsonNode array(short... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(int... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(long... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(float... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(double... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(BigDecimal... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(BigInteger... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(String... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(boolean... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(byte[]... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(JsonNode... values) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var value : values) {
            arrayNode.add(value);
        }

        return arrayNode;
    }

    public static JsonNode array(JsonFactoryNode... factoryNodes) {
        final var arrayNode = objectMapper.createArrayNode();

        for (var factoryNode : factoryNodes) {
            arrayNode.add(factoryNode.objectNode());
        }

        return arrayNode;
    }
}
