# **Задача № 2 Польская нотация**

## **Цель**:
1. Создать программу перевода инфиксной записи (например 2 + 3) в постфиксную запись (2 3 +), что и будет являться так называемой "Обратной польской записью". Обра́тная по́льская запись (англ. Reverse Polish notation, RPN) — форма записи математических и логических выражений, в которой операнды расположены перед знаками операций.
2. Необходимо реализовать алгоритм на основе очереди, который прочитает математическое выражение, найдет все числа и сохранит их отдельно от знаков (в отдельную коллекцию).

### *Пример*:
``` Пример 1
1
Введите вашу формулу в формате: 2 + 2.
2 * 3 + 9 / 1 - 6
Ваше выражение в формате обратной польской нотации:
2 3 9 1 6 * + / - 
```

### **Моя реализация**:
1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - класс, отвечающий за запуск программы, путем инициирования метода *start()* с инициированием внутри себя
  вспомогательных ```void``` методов: 
  * *printMenu()* - выводит меню команд программы на экран;
  * *polishNotationExpression()* - переводит выражение в польскую нотацию.  

#### Класс **Program**:
``` java
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
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) {
        var program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Добро пожаловать! Эта программа позволяет преобразовать стандарную формулу вида "2+2" в обратную польскую нотацию вида "2 2 +"
Возможные команды программы:
0 или выход: выход из программы.
1: конвертировать выражение в обратную польскую нотацию.
```
