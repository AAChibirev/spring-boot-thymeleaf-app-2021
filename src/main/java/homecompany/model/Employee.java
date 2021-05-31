package homecompany.model;

import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="lastname", nullable = false)
    @Size(min=2, max = 20)
    @Pattern(regexp = "^[A-Z][a-z]*$",
            message = "only letters, start with upper case letter")
    private String surname;

    @Size(min=2, max = 20)
    @Column(name="firstname", nullable = false)
    @Pattern(regexp = "^[A-Z][a-z]*$",
            message = "only letters, start with upper case letter")
    private String name;

    @Size(min=2, max = 20)
    @Column(name="middlename", nullable = false)
    @Pattern(regexp = "^[A-Z][a-z]*$",
            message = "only letters, start with upper case letter")
    private String middleName;


    @Column(name="age", nullable = false)
    @Range(min=18, max = 80)
    private int age;

    @Column(name = "salary", nullable = false)
    @Digits(integer = 7, fraction = 2)
    private double salary;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "company", nullable = false)
    private String companyName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
