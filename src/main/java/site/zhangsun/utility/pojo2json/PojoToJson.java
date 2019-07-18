package site.zhangsun.utility.pojo2json;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Functions: Pojo To JSON Tools
 *
 * @author Murphy Zhang Sun
 * @date 2019-07-18 09:06
 * @since v4.0.1
 */
public class PojoToJson {

    private static final String TAIL = ",\r\n";
    public static String pojoToJson(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        builder.append("{\r\n");
        Deque<String> tabs = new ArrayDeque<>(1);
        tabs.push("\t");
        for (Field field : fields) {
            field2Json(field, builder, tabs);
        }
        builder.append("}");
        int tail = builder.lastIndexOf(",");
        builder.deleteCharAt(tail);
        return builder.toString();
    }

    private static StringBuilder field2Json(Field field, StringBuilder builder, Deque<String> tabStack) {
        String tabs = tabStack.toString().replace("[", "").replace(",", "").replace("]", "");
        builder.append(tabs).append("\"").append(field.getName()).append("\"");
        Class<?> type = field.getType();
        if (type == java.lang.String.class) {
            return builder.append(": \"\"").append(TAIL);
        }

        if (type == java.lang.Integer.class || type == int.class) {
            return builder.append(": ").append(0).append(TAIL);
        }

        if (type == java.lang.Long.class || type == long.class) {
            return builder.append(": ").append(0L).append(TAIL);
        }

        if (type == java.lang.Double.class || type == double.class) {
            return builder.append(": ").append(0D).append(TAIL);
        }

        if (type == java.lang.Float.class || type == float.class) {
            return builder.append(": ").append(0F).append(TAIL);
        }

        if (type == java.lang.Short.class || type == short.class) {
            return builder.append(": ").append(0).append(TAIL);
        }

        if (type == java.lang.Byte.class || type == byte.class) {
            return builder.append(": ").append(0).append(TAIL);
        }

        if (type == java.lang.Character.class || type == char.class) {
            return builder.append(": \'\'").append(TAIL);
        }

        if (type == java.lang.Boolean.class || type == boolean.class) {
            return builder.append(": ").append(false).append(TAIL);
        }

        if (type == java.util.Date.class) {
            Calendar instance = Calendar.getInstance();
            Date time = instance.getTime();
            return builder.append(": \"").append(time).append("\"").append(TAIL);
        }

        Field[] declaredFields = field.getType().getDeclaredFields();
        int head = builder.lastIndexOf(",");
        builder.deleteCharAt(head);
        builder.append(": {\r\n");

        tabStack.push("\t");
        for (Field declaredField : declaredFields) {
            field2Json(declaredField, builder, tabStack);
        }
        tabStack.pop();
        tabs = tabStack.toString().replace("[", "").replace("]", "").replace(",", "");
        builder.append(tabs);
        builder.append("}\r\n");
        return builder;
    }
    public static void main(String[] args) {
        String json = pojoToJson(Person.class);
        System.out.println(json);
    }
}