package com.poplar.optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created BY poplar ON 2019/12/27
 */
public class OptionalTest1 {
    public static void main(String[] args) {
        Employee employee = new Employee("leo");
        Employee employee2 = new Employee("bob");
        Company company = new Company("alibaba");
//        company.setEmployeeList(Arrays.asList(employee, employee2));
        int a = 1, b = 2, c = 3;
        a = b = c - 2;
        System.out.println(a);
        System.out.println(b);
        Optional<Company> optionalCompany = Optional.of(company);
        List<Employee> list = optionalCompany.map(Company::getEmployeeList).orElse(new ArrayList<>());
        System.out.println(list.size());

    }
}


class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Company {

    private String name;

    private List<Employee> employeeList;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}