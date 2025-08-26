package com.github.lipinskipawel.fluent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class JsonFactoryNode {

    private final ObjectMapper objectMapper;
    private final ObjectNode objectNode;

    JsonFactoryNode(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectNode = objectMapper.createObjectNode();
    }

    public JsonFactoryNode set(String fieldName, short value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, Short value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, int value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, Integer value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, long value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, Long value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, float value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, Float value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, double value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, Double value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, BigDecimal value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, BigInteger value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, String value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, boolean value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, Boolean value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, byte[] value) {
        objectNode.put(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, JsonNode value) {
        objectNode.set(fieldName, value);
        return this;
    }

    public JsonFactoryNode set(String fieldName, JsonFactoryNode jsonFactoryNode) {
        requireNonNull(jsonFactoryNode);

        final var objectNode1 = objectMapper.createObjectNode();
        objectNode.set(fieldName, objectNode1.setAll(jsonFactoryNode.objectNode()));
        return this;
    }

    ObjectNode objectNode() {
        return objectNode;
    }

    @Override
    public String toString() {
        return objectNode.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonFactoryNode that = (JsonFactoryNode) o;
        return Objects.equals(objectNode, that.objectNode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(objectNode);
    }
}
