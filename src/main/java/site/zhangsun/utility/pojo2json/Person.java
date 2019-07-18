package site.zhangsun.utility.pojo2json;

import lombok.Data;

/**
 * Functions: Person
 *
 * @author Murphy Zhang Sun
 * @date 2019-07-18 09:41
 * @since v4.0.1
 */
@Data
public class Person {
    private String name;
    private char gender;
    private int age;
    private boolean boy;
    private double salary;
    private Son son;
}