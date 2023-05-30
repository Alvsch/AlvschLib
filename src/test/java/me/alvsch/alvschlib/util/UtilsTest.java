package me.alvsch.alvschlib.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void matches() {
        boolean match1 = Utils.matches("hell", "hello", 1);
        boolean match2 = Utils.matches("ello", "hello", 1);
        boolean match3 = Utils.matches("helo", "hello", 1);
        boolean match4 = Utils.matches("hallo", "hello", 1);
        boolean match5 = Utils.matches("hello", "hello", 1);

        boolean match6 = Utils.matches("ell", "hello", 1);
        boolean match7 = Utils.matches("hel", "hello", 1);
        boolean match8 = Utils.matches("elo", "hello", 1);
        boolean match9 = Utils.matches("hlo", "hello", 1);
        boolean match10 = Utils.matches("heo", "hello", 1);

        assertTrue(match1);
        assertTrue(match2);
        assertTrue(match3);
        assertTrue(match4);
        assertTrue(match5);

        assertFalse(match6);
        assertFalse(match7);
        assertFalse(match8);
        assertFalse(match9);
        assertFalse(match10);
    }
}