
package com.codegen.model;


import jakarta.persistence.*;
import java.io.Serializable;


import java.time.LocalDate;

@Entity
@Table(name = "juned")
public class Juned implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuunNo")
    private Long fuunNo;
    @Column(name = "name")
    private String name;
    @Column(name = "dob")
    private LocalDate dob;


    public Juned() {
    }

    public Juned(
        Long fuunNo, 
        String name, 
        LocalDate dob
    ) {
        this.fuunNo = fuunNo;
        this.name = name;
        this.dob = dob;
    }

    public Long getFuunNo() {
        return fuunNo;
    }

    public void setFuunNo(Long fuunNo) {
        this.fuunNo = fuunNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Juned{" +
        "fuunNo=" + fuunNo + ", "  +
        "name=" + name + ", "  +
        "dob=" + dob + ""  +
        '}';
    }
}
