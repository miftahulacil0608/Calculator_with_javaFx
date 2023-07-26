package sample.Evaluation;

import java.math.BigDecimal;
import java.util.Stack;

public class EvaluateString {
    public static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Double> values = new
                Stack<Double>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new
                Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            // Current token is a
            // whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number,
            // push it to stack for numbers
            //sekarang token dibuat ngisi karakter doang, si angka dilempar ke variabel values
            if (tokens[i] >= '0' &&
                    tokens[i] <= '9') {
                StringBuffer sbuf = new
                        StringBuffer();


                //variabel tokens sekarang akan diisi oleh angka dan dilemparkan ke stringBuffer sbuf dan kemudian
                //didorong kedalam variabel stack values
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.'))
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));


                i--;

            }


            /*disini digunakan jika kurung buka "(" digunakan, dan ketika terdapat perhitungan
             lebih dari 2 angka maka perhitungan yg pertama dilemparkan pada else if dibawah yg isine
             tokens[i] == '+' dan seterusnya*/
            else if (tokens[i] == '(') {
                ops.push(tokens[i]);


            }//ini akan dieksekusi jiga ( ) terdapat didalamnya, jika hanya ada salah satu maka error
            //jika lengkap maka akan dilakukan perhitungan
            else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    System.out.println("values yang dieksekusi == " + values);
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }

                ops.pop();

            }

            // Current token is an operator.
            else if (tokens[i] == '!' || tokens[i] == '+' || tokens[i] == '\u2013' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == 'M' || tokens[i] == '^') {
                //ini bakalan dieksekusi bila ops/operator lebih dari 1

                while (!ops.empty() &&
                        hasPrecedence(tokens[i], ops.peek())) {
                    if (ops.contains('!')) {
                        values.push(applyFactorial(ops.pop(), values.pop()));
                    } else {
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    }
                }
                // jika whilenya berjalan maka ops sekarang mengisi token bagian kanan
                ops.push(tokens[i]);
            }
        }

        //ini digunakan untuk perhitungan 2 bilangan terakhir
        //hanya 2 BILANGAN INGAT

        while (!ops.empty()) {
            if (ops.contains('!')) {

                values.push(applyFactorial(ops.pop(), values.pop()));

            } else {

                values.push(applyOp(ops.pop(),
                        values.pop(),
                        values.pop()));
            }

        }

        return values.pop();
    }


    public static boolean hasPrecedence(
            char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '!' || op1 == '^') && (op2 == '*' || op2 == '/' || op1 == 'M'))
            return false;
        if ((op1 == '!' || op1 == '^' || op1 == 'M') && (op2 == '+' || op2 == '\u2013'))
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '\u2013'))
            return false;
        else
            return true;
    }


    public static double applyFactorial(char op, double a) {
        switch (op) {
            case '!':
                return factorial(a);
        }
        return 0;
    }

    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '\u2013':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
            case 'M':
                return a % b;
            case '^':
                return Math.pow(a, b);

        }
        return 0;
    }

    public static double factorial(double a) {
        if (a <= 2) {
            return a;
        }
        return a * factorial(a - 1);
    }

    // Driver method to test above methods
    public static void main(String[] args) {
        char simbol = '\u2015';
        //System.out.println(EvaluateString.evaluate("-3_1"));
        System.out.println(EvaluateString.evaluate("-4 '\u2013' 3.22 "));
        System.out.println(6 - 2.5);
        System.out.println(simbol);
    }
}
