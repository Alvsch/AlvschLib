package me.alvsch.alvschlib;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class AlvschLibTest {

    @Test
    public void javaTest() {
        String test = "hello /join/join/join/join/join/join/join/join/join/join/join/join/join/join/join/join/join";
        System.out.println(removeRepeatedElements(test));
    }


    public static String removeRepeatedElements(String input) {
        StringBuilder result = new StringBuilder();

        Set<String> uniqueElements = new HashSet<>();

        String[] words = input.split(" ");

        for (String word : words) {
            StringBuilder cleanedWord = new StringBuilder();
            char[] characters = word.toCharArray();

            for (int i = 0; i < characters.length; i++) {
                char currentChar = characters[i];
                if (isAllowedOccurrences(characters, i) || !isRepeatedCharacter(characters, i) && !isRepeatedPhrase(word, i)) {
                    cleanedWord.append(currentChar);
                    uniqueElements.add(Character.toString(currentChar));
                }
            }

            if (result.length() > 0) {
                result.append(" ");
            }

            result.append(cleanedWord);
        }

        return result.toString();
    }

    public static boolean isRepeatedCharacter(char[] characters, int index) {
        char currentChar = characters[index];
        for (int i = 0; i < index; i++) {
            if (characters[i] == currentChar) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRepeatedPhrase(String word, int index) {
        String currentPhrase = word.substring(index);
        return word.indexOf(currentPhrase) != index;
    }

    public static boolean isAllowedOccurrences(char[] characters, int index) {
        if (index > 0 && characters[index] == characters[index - 1]) {
            return true;
        }
        return false;
    }

}
