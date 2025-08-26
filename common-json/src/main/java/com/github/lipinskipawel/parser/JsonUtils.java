package com.github.lipinskipawel.parser;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.empty;

public final class JsonUtils {

    public static <T> T parseNode(DeserializationContext ctx, TreeNode tree, String nodeName, Class<T> clazz) throws IOException {
        return parseOptionalNode(ctx, tree, nodeName, clazz).orElseThrow();
    }

    public static <T> Optional<T> parseOptionalNode(DeserializationContext ctx, TreeNode tree, String nodeName, Class<T> clazz) throws IOException {
        final var node = tree.get(nodeName);
        if (node == null) {
            return empty();
        }

        final var parser = node.traverse(ctx.getParser().getCodec());
        parser.nextToken();

        return Optional.of(ctx.readValue(parser, clazz));
    }

    public static <T> List<T> parseListNode(DeserializationContext ctx, TreeNode tree, String nodeName, Class<T> clazz) throws IOException {
        final var node = tree.get(nodeName);
        if (node == null || !node.isArray()) {
            return List.of();
        }

        final var result = new ArrayList<T>();
        final var elements = ((JsonNode) node).elements();

        while (elements.hasNext()) {
            final var parser = elements.next().traverse(ctx.getParser().getCodec());
            parser.nextToken();
            result.add(ctx.readValue(parser, clazz));
        }

        return result;
    }

    public static <T> Set<T> parseSetNode(DeserializationContext ctx, TreeNode tree, String nodeName, Class<T> clazz) throws IOException {
        final var node = tree.get(nodeName);
        if (node == null || !node.isArray()) {
            return Set.of();
        }

        final Set<T> result = new HashSet<>();
        final var elements = ((JsonNode) node).elements();

        while (elements.hasNext()) {
            final var parser = elements.next().traverse(ctx.getParser().getCodec());
            parser.nextToken();
            result.add(ctx.readValue(parser, clazz));
        }

        return result;
    }
}
