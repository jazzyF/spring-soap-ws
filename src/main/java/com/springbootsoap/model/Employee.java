package com.springbootsoap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = -3285409534835834046L;

    @Id
    @Column(name = "employee_id")
    private long employeeId;

    @Column(name = "name")
    private String name;

    @Column(name="department")
    private String department;

    @Column(name = "phone")
    private String phone;

    @Column(name="address")
    private String address;
}
