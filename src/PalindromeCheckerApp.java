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

        // Step 1: Convert string to linked list
        Node head = new Node(input.charAt(0));
        Node current = head;

        for (int i = 1; i < input.length(); i++) {
            current.next = new Node(input.charAt(i));
            current = current.next;
        }

        // Step 2: Find middle using fast & slow pointer
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 3: Reverse second half
        Node prev = null;
        Node next;

        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // Step 4: Compare halves
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
    // UC9 - Recursive Palindrome Checker
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
        // Base condition
        if (start >= end) {
            return true;
        }

        // If characters don't match
        if (input.charAt(start) != input.charAt(end)) {
            return false;
        }

        // Recursive call
        return isPalindromeRecursive(input, start + 1, end - 1);
    }
    // UC10 - Case-Insensitive & Space-Ignored Palindrome Check
    public static void uc10NormalizedCheck(String input) {
        System.out.println("UC10 - Case Insensitive & Space Ignored Check");

        // Step 1: Normalize string
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Step 2: Check palindrome using two-pointer
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
}