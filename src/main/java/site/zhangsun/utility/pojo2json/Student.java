package site.zhangsun.utility.pojo2json;

import lombok.Data;

/**
 * Functions: Student
 *
 * @author Murphy Zhang Sun
 * @date 2019-07-18 10:41
 * @since v4.0.1
 */
@Data
public class Student {
    private String name;
    private char gender;
    private int age;
    private boolean boy;
}