package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.utils.Utils;

import java.util.*;

public class Program {
    private final Scanner scanner = new Scanner(System.in);
    private final Queue<String> sign = new ArrayDeque<>();
    private final Deque<Integer> numbers = new LinkedList<>();

    public void start() {
        String input;
        String[] allInput;
        while (true) {
            printMenu();
            input = scanner.nextLine();
            if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                scanner.close();
                break;
            } else {
                var operationNumber = Integer.parseInt(input);
                switch (operationNumber) {
                    case 1:
                        System.out.println(Utils.ANSI_BLUE + "Введите вашу формулу в формате: 2 + 2." +
                                Utils.ANSI_RESET);
                        allInput = scanner.nextLine().split(" ");
                        for (String expression : allInput) {
                            if (expression.equals("+") || expression.equals("-") || expression.equals("/") ||
                                    expression.equals("*")) {
                                sign.offer(expression);
                            } else {
                                numbers.offer(Integer.parseInt(expression));
                            }
                        }
                        polishNotationExpression(numbers, sign);
                        break;
                    default:
                        System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" + Utils.ANSI_RESET);
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Добро пожаловать! Эта программа позволяет преобразовать " +
                "стандарную формулу вида \"2+2\" в обратную польскую нотацию вида \"2 2 +\"" + Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: выход из программы.");
        System.out.println("1: конвертировать выражение в обратную польскую нотацию.");

    }

    private static void polishNotationExpression(Deque<Integer> numbers, Queue<String> sign) {
        var stringBuilder = new StringBuilder();
        System.out.println(Utils.ANSI_CYAN + "Ваше выражение в формате обратной польской нотации:" +
                Utils.ANSI_RESET);
        while (!numbers.isEmpty()) {
            stringBuilder.append(numbers.pop()).append(" ");
        }
        while (!sign.isEmpty()) {
            stringBuilder.append(sign.poll()).append(" ");
        }
        System.out.println(stringBuilder);
    }
}
