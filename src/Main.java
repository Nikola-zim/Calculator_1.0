import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean roman = false;

    public static void main(String[] args) {
        String str_expression = getString();
        String result = calc(str_expression);
        System.out.println("Результат операции: "+result);
    }
    public static String calc(String input){
        String result; // Строка с результатом
        String[] expression;
        // Разделение аргументов выражения
        expression = input.split(" ");
        // Массив из 2 чисел выражения
        int[] numbers;
        numbers = format_number(expression);
        // Получение операции
        char operation = getOperation(expression[1]);
        // Вычисление операции
        int result_num = 0;
        switch (operation) {
            case '+' -> result_num = numbers[0] + numbers[1];
            case '-' -> result_num = numbers[0] - numbers[1];
            case '*' -> result_num = numbers[0] * numbers[1];
            case '/' -> result_num = numbers[0] / numbers[1];
            default -> {
                System.err.println("Операция не распознана!");
                System.exit(1);
            }
        }
        if (roman) {
            result = convertNumToRoman(result_num);
        } else {
            result = Integer.toString(result_num);
        }
        return result;
    }
    static String getString(){
        String str_expression = null;
        if(scanner.hasNextLine()){
            // Считывание строки выражения
            str_expression = scanner.nextLine();
        } else {
            System.err.println("Вы допустили ошибку при вводе Выражения. Попробуйте еще раз.");
            System.exit(1);
            scanner.next();
        }
        return str_expression;
    }

    // Извлекаем числа из строки и проверяем их значения
    static int[] format_number(String [] input){
        int roman_check_1 = roman_check (input[0]);
        int roman_check_2 = roman_check (input[2]);
        // Проверка того, что оба числа или арабские, или римские
        if ((roman_check_1 == 0 || roman_check_2 == 0) && (roman_check_1 + roman_check_2) > 0){
            System.err.println("Оба числа должны быть или арабскими, или римскими!");
            System.exit(1);
        }
        // Если оба числа римские
        if (roman_check_1 != 0 && roman_check_2 != 0){

            int _num1 = roman_check (input[0]);
            int _num2 = roman_check (input[2]);
            roman = true;
            return new int[]{_num1, _num2};
        }
        int _num1 = Integer.parseInt(input[0]);
        int _num2 = Integer.parseInt(input[2]);
        if((_num1 >= 1) && (_num1 <= 10) && (_num2 >= 1) && (_num2 <= 10)) {
            roman = false;
        } else {
            System.err.println("Превышен диапазон допустимых значений!");
            System.exit(1);
        }
        return new int[]{_num1, _num2};
    }

    static char getOperation(String str_operator) {
        return str_operator.charAt(0);
    }
    // Проверка на римские числа
    static int roman_check(String roman){
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }
    // Конвертирование в римские чисела
    static String convertNumToRoman (Integer numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C" };
        return roman[numArabian];
    }
}