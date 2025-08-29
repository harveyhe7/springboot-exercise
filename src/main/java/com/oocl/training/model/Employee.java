package com.oocl.training.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int age;
    String gender;
    double salary;
    boolean active;
//    int companyId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

//    public Employee(String name, int age, String gender, double salary, boolean active, int companyId) {
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//        this.salary = salary;
//        this.active = active;
//        this.companyId = companyId;
//        this.company = null;
//    }

    public Employee(String name, int age, String gender, double salary, boolean active) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = active;
        this.company = null;
    }

//    public Employee(Integer id,String name, int age, String gender, double salary, boolean active, int companyId) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.gender = gender;
//        this.salary = salary;
//        this.active = active;
//        this.companyId = companyId;
//        this.company = null;
//    }
    public Employee(Integer id,String name, int age, String gender, double salary, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = active;
        this.company = null;
    }
}
