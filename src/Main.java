import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
//        System.out.println(task01("{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}"));
        task02(new int[] {5,8,1,2,7,10,8,1,22,5,12,3,1});
    }

    /*
    1) Дана строка sql-запроса "select * from students where ".
    Сформируйте часть WHERE этого запроса, используя StringBuilder.
    Данные для фильтрации приведены ниже в виде json-строки.
    Если значение null, то параметр не должен попадать в запрос.
    Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
    select * from students where 'name=Ivanov' and 'country=Russia' and...
     */

    public static String task01(String jsonStr) {
        StringBuilder stringBuilder = new StringBuilder("select * from students where ");
        jsonStr = jsonStr.replaceAll("\\{|\\}|\"","");
        String[] pairs = jsonStr.split(",");
        for (String s : pairs) {
            String[] pair = s.split(":");
            if (!pair[1].equals("null")) {
                if (stringBuilder.indexOf("=") > 0) stringBuilder.append(" and ");
                stringBuilder.append("'").append(pair[0].trim()).append("=").append(pair[1].trim()).append("'");
            }
        }
        return stringBuilder.toString();
    }

    /*
    2)* Реализуйте алгоритм сортировки пузырьком числового массива,
    результат после каждой итерации запишите в лог-файл.
     */

    public static void task02(int[] arr) {
        String logpath = "log.txt";
        Logger logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(logpath);
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sorting array: ").append(printArr(arr));
        logger.info(stringBuilder.toString());
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    stringBuilder = new StringBuilder(String.format("Replacing %d and %d. Now: ", arr[j],arr[j + 1]));
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    stringBuilder.append(printArr(arr));
                    logger.info(stringBuilder.toString());
                }
            }
        }
        stringBuilder = new StringBuilder("Result: ");
        stringBuilder.append(printArr(arr));
        logger.info(stringBuilder.toString());
        fileHandler.close();
        logger.getHandlers()[0].close();
    }

    public static String printArr(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : arr) {
            if (stringBuilder.length() > 0)
                stringBuilder.append(", ");
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }
}