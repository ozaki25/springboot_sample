package sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    public User() { };

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "{id: " + this.getId() + ", name: " + this.getName() + ", age: " + this.getAge() + "}";
    }

    public static String toString(List<User> users) {
        String result = "[";
        for(User u : users) {
            result += "{id: " + u.getId() + ", name: " + u.getName() + ", age: " + u.getAge() + "}, ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }
}
