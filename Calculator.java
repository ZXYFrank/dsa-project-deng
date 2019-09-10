import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class Calculator {
    public static final String wait = "WAIT";
    public static final String calRightNow = "CAL_RIGHT_NOW";

    private double result = 0.0;
    private String RPN = "NONE";

    public static final Map<Character, Integer> pri = new HashMap<Character, Integer>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
            put('^', 3);
            put('!', 4);

            // opreate the caculation right next
            put(')', -10);
            // wait for all remaining
            put('(', -10);
            // calculate all that's remained
            put('\0', -100);
        }
    };
    private static String eliminatePatrenthesis = "ELEMINATE_PAREENTHESIS";
    private static String done = "DONE";

    public static String orderBetween(Character pre, Character current) {
        System.out.println("***************COMPARING: [" + pre + "] AND [" + current + "]*****************");
        if (pre == '\0' & current == '\0') {
            return done;
        }

        if (pre == '(' || current == '(') {
            // left branket
            if (pre == '(' && current == ')') {
                // when parenthesis pair
                return eliminatePatrenthesis;
            } else {
                // wait for the right bracket
                return wait;
            }
        }

        // tell the correct order between the operators
        int preop = pri.get(pre);
        int nextop = pri.get(current);

        int r = nextop - preop;

        if (r >= 0) {
            // the last opreator needs to wait.
            // coz current opreator has higher priority
            return wait;
        } else {
            // (r < 0)
            // previous opreator can perform
            return calRightNow;
        }
    }

    public String getRPN() {
        return RPN;
    }

    public void setRPN(String rPN) {
        this.RPN = rPN;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void evaluateAndGenerateRPN(String infix) {

        // perform format check
        if (!formatCheck(infix)) {
            throw new RuntimeException("ERROR: format");
        }

        // input the infix
        double value = 0;
        // cursor for the char array
        int cur = 0;
        // convert to char array
        char[] chars = infix.toCharArray();

        // oprands and operators
        Stack<Double> oprands = new Stack();
        Stack<Character> operators = new Stack();
        operators.push('\0');
        String RPN = "";

        while (!operators.empty()) {
            // operators remain
            if (isDigitOrDot(chars[cur])) {
                // if there is a number to read
                cur = readDouble(cur, chars, oprands);
                // generate RPN
                // append oprand
                RPN += (oprands.peek().toString()) + " ";
                System.out.println("--------------R P N : " + RPN + "----------");

                System.out.println("OPRANDS:     " + oprands);

                continue;
            } else {
                // meet an opreator
                String action = orderBetween(operators.peek(), chars[cur]);
                if (action == wait) {
                    // store the operator
                    operators.push(chars[cur]);

                    System.out.println("OPERATORS:     " + operators.toString());

                    // move to next char
                    cur++;
                    continue;
                } else if (action == calRightNow) {
                    char oprt = operators.pop();

                    System.out.println("OPERATORS:     " + operators.toString());

                    oprands.push(calculate(oprt, oprands));
                    // append the operator
                    RPN += oprt + " ";

                    System.out.println("OPRANDS:     " + oprands.toString());
                    System.out.println("--------------R P N : " + RPN + "----------");

                    continue;
                } else if (action == eliminatePatrenthesis) {
                    // pop '('
                    operators.pop();

                    System.out.println("OPERATORS:     " + operators.toString());
                    // skip ')'
                    cur++;
                    continue;
                } else if (action == done) {
                    // finished
                    break;
                }
            }
        }

        this.setResult(oprands.peek());
        this.setRPN(RPN);
    }

    private static Double calculate(char oprt, Stack<Double> oprands) {
        // perform the calculation
        double result = 0.0;
        double x2 = oprands.pop();
        // single opreatorjav
        if (oprt == '!') {
            int n = (int) x2;
            if (n == 0) {
                return 1.0;
            } else {
                int r = 1;
                for (int i = 1; i <= n; i++) {
                    r *= i;
                }
                return (double) r;
            }

        } else {
            double x1 = oprands.pop();
            System.out.println("******PERFORM CALCULATION(BINARY): " + x1 + oprt + x2 + "******");
            // binary opreator
            switch (oprt) {
            case '+':
                return x1 + x2;
            case '-':
                return x1 - x2;
            case '*':
                return x1 * x2;
            case '/':
                return x1 / x2;
            case '^':
                return Math.pow(x1, x2);
            }
            throw new RuntimeException("ERROR: Invalid operator!");
        }
    }

    private static boolean formatCheck(String infix) {
        boolean result = true;
        return result;
    }

    private static int readDouble(int cur, char[] chars, Stack<Double> oprands) {
        double x = 0;

        // shift of cursor
        int countIntegerPart = 0;
        // decimal point
        boolean afterDot = false;
        int countDecimal = 0;
        Queue<Integer> digits = new LinkedList<Integer>();
        char c = '0';

        while (isDigitOrDot(c = chars[cur])) {
            if (c == '.') {
                afterDot = true;
                cur++;
                continue;
            } else {
                digits.offer((int) c - '0');
            }

            if (afterDot) {
                // afterDot
                countDecimal++;
            } else {
                countIntegerPart++;
            }
            // move the cursor
            cur++;
        }
        double result = 0;

        // begin from power 0
        countIntegerPart--;
        double power = 0;
        while (!digits.isEmpty()) {
            // digits left
            power = Math.pow(10, countIntegerPart);
            result += digits.poll() * power;
            countIntegerPart--;
        }

        System.out.println(result);

        oprands.push(result);
        // return new cursor position
        return countIntegerPart + countDecimal + cur + 1 /* count the dot */;

    }

    private static boolean isDigitOrDot(char c) {
        return ('0' <= c && c <= '9' || c == '.');
    }

    public static void main(String[] args) {
        String notaition = "2.1/0.7+0.52*2^(4!/(3-))\0";
        Calculator calculator = new Calculator();
        calculator.evaluateAndGenerateRPN(notaition);
        System.out.println("\n********************************FINAL RESULT : " + calculator.getResult()
                + " ********************************");
        System.out.println("\n********************************RPN OF " + notaition
                + " : \n********************************" + calculator.getRPN() + " ********************************");

    }
}
