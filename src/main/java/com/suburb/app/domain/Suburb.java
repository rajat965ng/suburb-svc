package com.suburb.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "SUBURB")
public class Suburb {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String name;

    @Column
    Long postalCode;

    public Suburb() {
    }

    public Suburb(String name, Long postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Suburb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
