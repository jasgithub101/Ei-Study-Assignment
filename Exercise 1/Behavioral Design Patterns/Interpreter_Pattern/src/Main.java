import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static Expression parseExpression(String token) {
        String[] tokens = token.split(" ");
        Stack<Expression> stack = new Stack<>();

        for (String t : tokens) {
            switch (t) {
                case "+":
                    Expression rightAdd = stack.pop();
                    Expression leftAdd = stack.pop();
                    stack.push(new Add(leftAdd, rightAdd));
                    break;
                case "-":
                    Expression rightSubtract = stack.pop();
                    Expression leftSubtract = stack.pop();
                    stack.push(new Subtract(leftSubtract, rightSubtract));
                    break;
                case "*":
                    Expression rightMultiply = stack.pop();
                    Expression leftMultiply = stack.pop();
                    stack.push(new Multiply(leftMultiply, rightMultiply));
                    break;
                case "/":
                    Expression rightDivide = stack.pop();
                    Expression leftDivide = stack.pop();
                    stack.push(new Division(leftDivide, rightDivide));
                    break;
                default:
                    stack.push(new Number(Integer.parseInt(t)));
                    break;
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine(); // "5 3 + 8 2 - 3 5 * 12 3 / -";
        Expression parsedExpression = parseExpression(expression);
        int result = parsedExpression.interpret();
        System.out.println("Result: " + result);
        in.close();
    }
}
