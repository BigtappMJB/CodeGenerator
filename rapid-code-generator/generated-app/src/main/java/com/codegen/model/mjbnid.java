
package com.codegen;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mjbnid")
public class mjbnid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    public mjbnid() {
    }

    public mjbnid(
        Long id, 
        String name, 
        String email
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "mjbnid{" +
        "id=" + id + ", "  +
        "name=" + name + ", "  +
        "email=" + email + ""  +
        '}';
    }
}
