package dev.atslega.cpmb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "companies")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String companyName;

    @Column(name = "address", nullable = false)
    private String companyAddress;

    public Company(String name, String address) {
        this.companyName = name;
        this.companyAddress = address;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
