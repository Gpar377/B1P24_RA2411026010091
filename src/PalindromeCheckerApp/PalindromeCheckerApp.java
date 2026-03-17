package PalindromeCheckerApp;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;

public class PalindromeCheckerApp {
    // UC1 - Application Entry
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("      Palindrome Checker App     ");
        System.out.println("      Version 1.0                ");
        System.out.println("=================================\n");

        uc2HardcodedCheck();

        uc3ReverseString("racecar");

        uc4CharArrayCheck("level");

        uc5StackCheck("madam");

        uc6QueueStackCheck("radar");

        uc7DequeCheck("civic");

        uc8LinkedListCheck("noon");

        uc9RecursiveCheck("refer");

        uc10NormalizedCheck("A man a plan a canal Panama");

        uc11OOPCheck("rotor");

        uc12StrategyCheck("level", new StackStrategy(), "Stack Strategy");
        uc12StrategyCheck("radar", new DequeStrategy(), "Deque Strategy");

        uc13PerformanceCheck("racecar");
    }

    // UC2 - Hardcoded String Check
    public static void uc2HardcodedCheck() {
        System.out.println("UC2 - Hardcoded Palindrome Check");

        String word = "madam";

        if (word.equals("madam")) {
            System.out.println(word + " is a Palindrome\n");
        } else {
            System.out.println(word + " is NOT a Palindrome\n");
        }
    }

    // UC3 - Reverse String Using Loop
    public static void uc3ReverseString(String input) {
        System.out.println("UC3 - Reverse String Method");

        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--) {
            reversed = reversed + input.charAt(i);
        }

        if (input.equals(reversed)) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }

    // UC4 - Character Array (Two Pointer)
    public static void uc4CharArrayCheck(String input) {
        System.out.println("UC4 - Character Array Check");

        char[] arr = input.toCharArray();
        int start = 0;
        int end = arr.length - 1;
        boolean isPalindrome = true;

        while (start < end) {
            if (arr[start] != arr[end]) {
                isPalindrome = false;
                break;
            }
            start++;
            end--;
        }

        if (isPalindrome) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }

    // UC5 - Stack Based Check
    public static void uc5StackCheck(String input) {
        System.out.println("UC5 - Stack Based Check");

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        String reversed = "";

        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }

        if (input.equals(reversed)) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }

    // UC6 - Queue + Stack Check
    public static void uc6QueueStackCheck(String input) {
        System.out.println("UC6 - Queue + Stack Check");

        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            queue.add(input.charAt(i));
            stack.push(input.charAt(i));
        }

        boolean isPalindrome = true;

        while (!queue.isEmpty()) {
            if (queue.remove() != stack.pop()) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }

    // UC7 - Deque Based Optimized Check
    public static void uc7DequeCheck(String input) {
        System.out.println("UC7 - Deque Based Optimized Check");

        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            deque.add(input.charAt(i));
        }

        boolean isPalindrome = true;

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }
    // UC8 - Linked List Based Palindrome Checker
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void uc8LinkedListCheck(String input) {
        System.out.println("UC8 - Linked List Based Palindrome Check");

        if (input == null || input.length() == 0) {
            System.out.println("Empty string\n");
            return;
        }

        Node head = new Node(input.charAt(0));
        Node current = head;

        for (int i = 1; i < input.length(); i++) {
            current.next = new Node(input.charAt(i));
            current = current.next;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node prev = null;
        Node next;

        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        Node firstHalf = head;
        Node secondHalf = prev;

        boolean isPalindrome = true;

        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        if (isPalindrome) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }
    public static void uc9RecursiveCheck(String input) {
        System.out.println("UC9 - Recursive Palindrome Check");

        boolean result = isPalindromeRecursive(input, 0, input.length() - 1);

        if (result) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }

    private static boolean isPalindromeRecursive(String input, int start, int end) {
        if (start >= end) {
            return true;
        }

        if (input.charAt(start) != input.charAt(end)) {
            return false;
        }

        return isPalindromeRecursive(input, start + 1, end - 1);
    }
    public static void uc10NormalizedCheck(String input) {
        System.out.println("UC10 - Case Insensitive & Space Ignored Check");

        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int start = 0;
        int end = normalized.length() - 1;
        boolean isPalindrome = true;

        while (start < end) {
            if (normalized.charAt(start) != normalized.charAt(end)) {
                isPalindrome = false;
                break;
            }
            start++;
            end--;
        }

        if (isPalindrome) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }
    static class PalindromeService {

        public boolean checkPalindrome(String input) {
            if (input == null) {
                return false;
            }

            int start = 0;
            int end = input.length() - 1;

            while (start < end) {
                if (input.charAt(start) != input.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }

            return true;
        }
    }
    public static void uc11OOPCheck(String input) {
        System.out.println("UC11 - Object Oriented Palindrome Service");

        PalindromeService service = new PalindromeService();
        boolean result = service.checkPalindrome(input);

        if (result) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }
    interface PalindromeStrategy {
        boolean isPalindrome(String input);
    }
    static class StackStrategy implements PalindromeStrategy {
        public boolean isPalindrome(String input) {
            Stack<Character> stack = new Stack<>();
            for (char c : input.toCharArray()) {
                stack.push(c);
            }

            for (char c : input.toCharArray()) {
                if (c != stack.pop()) return false;
            }
            return true;
        }
    }

    static class DequeStrategy implements PalindromeStrategy {
        public boolean isPalindrome(String input) {
            Deque<Character> deque = new ArrayDeque<>();
            for (char c : input.toCharArray()) deque.add(c);

            while (deque.size() > 1) {
                if (deque.removeFirst() != deque.removeLast()) return false;
            }
            return true;
        }
    }
    public static void uc12StrategyCheck(String input, PalindromeStrategy strategy, String strategyName) {
        System.out.println("UC12 - Strategy Pattern Check using " + strategyName);
        boolean result = strategy.isPalindrome(input);
        if (result) {
            System.out.println(input + " is a Palindrome\n");
        } else {
            System.out.println(input + " is NOT a Palindrome\n");
        }
    }
    public static void uc13PerformanceCheck(String input) {
        System.out.println("UC13 - Performance Comparison");

        long start, end;

        start = System.nanoTime();
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) stack.push(c);
        for (char c : input.toCharArray()) stack.pop();
        end = System.nanoTime();
        System.out.println("Stack Strategy time: " + (end - start) + " ns");

        start = System.nanoTime();
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : input.toCharArray()) deque.add(c);
        while (deque.size() > 0) deque.removeFirst();
        end = System.nanoTime();
        System.out.println("Deque Strategy time: " + (end - start) + " ns");

        start = System.nanoTime();
        char[] arr = input.toCharArray();
        int s = 0, e = arr.length - 1;
        while (s < e) { if (arr[s++] != arr[e--]) break; }
        end = System.nanoTime();
        System.out.println("Char Array Two Pointer time: " + (end - start) + " ns\n");
    }
}