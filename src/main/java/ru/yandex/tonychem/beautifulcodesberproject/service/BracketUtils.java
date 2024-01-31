package ru.yandex.tonychem.beautifulcodesberproject.service;

import java.util.Stack;

public class BracketUtils {
    public static boolean validateBrackets(String text) {
        return _validateRecursively(text, 0, new Stack<>());
    }

    private static boolean _validateRecursively(String text, int currentPosition, Stack<Character> bracesStack) {
        if (currentPosition == text.length()) {
            return bracesStack.isEmpty();
        } else {
            boolean hasLettersOrDigits = false;

            for (int i = currentPosition; i < text.length(); i++) {
                char c = text.charAt(i);

                if (c == '(') {
                    bracesStack.push(c);
                    return _validateRecursively(text, i + 1, bracesStack);
                } else if (c == ')') {
                    if (bracesStack.isEmpty()) return false;
                    bracesStack.pop();
                } else if (Character.isLetterOrDigit(c)) {
                    hasLettersOrDigits = true;
                }
            }

            return hasLettersOrDigits && bracesStack.isEmpty();
        }
    }
}
