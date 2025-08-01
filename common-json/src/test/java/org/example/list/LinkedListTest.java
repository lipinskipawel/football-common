package org.example.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class 3LinkedListTest {
    @Test
    void testConstructor() {
        LinkedList list = new LinkedList();
        assertEquals(0, list.size());
    }

    @Test
    void testAdd() {
        LinkedList list = new LinkedList();

        list.add("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));

        list.add("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
    }

    @Test
    void testRemove() {
        LinkedList list = new LinkedList();

        list.add("one");
        list.add("two");
        assertTrue(list.remove("one"));

        assertEquals(1, list.size());
        assertEquals("two", list.get(0));

        assertTrue(list.remove("two"));
        assertEquals(0, list.size());
    }

    @Test
    public void testRemoveMissing() {
        LinkedList list = new LinkedList();

        list.add("one");
        list.add("two");
        assertFalse(list.remove("three"));
        assertEquals(2, list.size());
    }
}
