package ai;
import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {

        String a = String.valueOf(s.charAt(0));

        if (a.equals("]") || a.equals(")") || a.equals("}")) {
            return false;
        }

        Stack<Character> stck = new Stack<>();


        for (Character c : s.toCharArray()) {
            if (c == '[' || c == '(' || c == '{') {
                stck.push(c);
            } else if (!stck.empty()) {
                switch (c) {
                    case '}':
                        if (stck.peek().equals('{')) {
                            stck.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ']':
                        if (stck.peek().equals('[')) {
                            stck.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ')':
                        if (stck.peek().equals('(')) {
                            stck.pop();
                        } else {
                            return false;
                        }
                        break;
                }

            }

        }
        if (stck.empty()) {
            return true;
        } else {
            return false;
        }


    }
}

