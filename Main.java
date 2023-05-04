import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение:");
        String input = scanner.nextLine();

        // Проверяем, содержится ли в выражении арабские числа
        boolean isArabic = input.matches(".*\\d+.*");

        // Проверяем, содержится ли в выражении римские числа
        boolean isRoman = input.matches(".*[IVXLCMD]+.*");

        // Выбрасываем исключение, если в выражении содержатся как арабские, так и римские числа
        if (isArabic && isRoman) {
            throw new IllegalArgumentException("Неподходящее выражение");
        }

        String[] tokens = input.split(" ");

        // Получаем числа из ввода
        int a;
        int b;
        if (isArabic) {
            a = Integer.parseInt(tokens[0]);
            b = Integer.parseInt(tokens[2]);
        } else {
            a = romanToArabic(tokens[0]);
            b = romanToArabic(tokens[2]);
        }

        // Получаем знак операции
        String operation = tokens[1];

        // Выполняем операцию
        int result;
        switch (operation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неподходящее выражение");
        }

        // Выводим результат
        if (isArabic) {
            System.out.println(result);
        } else {
            System.out.println(arabicToRoman(result));
        }
    }

    // Конвертирует римское число в арабское
    private static int romanToArabic(String input) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i > 0 && map.get(input.charAt(i)) > map.get(input.charAt(i - 1))) {
                result += map.get(input.charAt(i)) - 2 * map.get(input.charAt(i - 1));
            } else {
                result += map.get(input.charAt(i));
            }
        }
        return result;
    }

    // Конвертирует арабское число в римское
    private static String arabicToRoman(int input) {
        if (input < 1 || input > 3999) {
            throw new IllegalArgumentException("Неподходящее число");
        }

        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < symbols.length; i++) {
            while (input >= values[i]) {
                input -= values[i];
                result.append(symbols[i]);
            }
        }
        return result.toString();
    }
}

