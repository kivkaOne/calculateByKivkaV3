import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner myScan = new Scanner(System.in);
        System.out.println("Введите выражение, состоящее из двух римских или арабских чисел и одного знака операции");
        String expression = myScan.nextLine();
        System.out.println(transform(expression));
    }

    public static String transform(String expression) {
        int num1;
        int num2;
        String sign;
        String result;
        boolean isRomanValue;
        String[] example = expression.split("[+\\-*/]");

        if (example.length != 2) {
            throw new RuntimeException("Недопустимая длина строки");
        }

        sign = whichSign(expression);
        if (sign == null) {
            throw new RuntimeException("Некорректный арифметический знак");
        }

        if (Roman.isRomanValue(example[0]) && Roman.isRomanValue(example[1])) {
            num1 = Roman.transformInArab(example[0]);
            num2 = Roman.transformInArab((example[1]));
            isRomanValue = true;
        } else if (!Roman.isRomanValue(example[0]) && !Roman.isRomanValue(example[1])) {
            num1 = Integer.parseInt(example[0]);
            num2 = Integer.parseInt(example[1]);
            isRomanValue = false;
        } else {
            throw new RuntimeException("Числа должны быть в одном формате");
        }

        if (num1 > 10 || num1 < 1) {
            throw new RuntimeException("Первое число является недопустимым");
        }
        if (num2 > 10 || num2 < 1) {
            throw new RuntimeException("Второе число является недопустимым");
        }
        int arabValue = operation(num1, num2, sign);
        if (isRomanValue) {
            if (arabValue <= 0) {
                throw new RuntimeException("Римское число должно быть больше 0");
            }
            result = Roman.transformInRoman(arabValue);
        } else {
            result = String.valueOf(arabValue);
        }
        return result;
    }

    static String whichSign(String expression) {

        if (expression.contains("+")) {
            return "+";
        } else if (expression.contains("-")) {
            return "-";
        } else if (expression.contains("*")) {
            return "*";
        } else if (expression.contains("/")) {
            return "/";
        } else {
            return null;
        }
    }

    static int operation(int a, int b, String sign) {

        if (sign.equals("+")) {
            return a + b;
        } else if (sign.equals("-")) {
            return a - b;
        } else if (sign.equals("*")) {
            return a * b;
        } else return a / b;
    }

    class Roman {
        static String[] romanValues = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                                                   "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
                                                   "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                                                   "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV",
                                                   "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI",
                                                   "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                                                   "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI","LVII",
                                                   "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
                                                   "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII",
                                                   "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
                                                   "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
                                                   "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII",
                                                   "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        public static boolean isRomanValue(String value) {
            for (int i = 0; i < romanValues.length; i++) {
                if (value.equals(romanValues[i])) {
                    return true;
                }
            }
            return false;
        }

        public static int transformInArab(String romValue) {
            for (int i = 0; i < romanValues.length; i++) {
                if (romValue.equals(romanValues[i])) {
                    return i + 1;
                }
            }
            return -1;
        }

        public static String transformInRoman(int arabValue) {
            String romanValue = "";
            try {
                romanValue = romanValues[arabValue - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new RuntimeException("arabValue вышел за рамки массива римских чисел");
            }
            return romanValue;
        }
    }
}


