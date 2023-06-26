public class Main {
    public static void main(String[] args) {

        System.out.println(Task01("{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}"));
    }

    /*
    1) Дана строка sql-запроса "select * from students where ".
    Сформируйте часть WHERE этого запроса, используя StringBuilder.
    Данные для фильтрации приведены ниже в виде json-строки.
    Если значение null, то параметр не должен попадать в запрос.
    Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
    select * from students where 'name=Ivanov' and 'country=Russia' and...
     */

    public static String Task01(String jsonStr) {
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

}