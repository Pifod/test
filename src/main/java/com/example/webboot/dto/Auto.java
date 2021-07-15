package com.example.webboot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "auto", schema = "study_database", catalog = "")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String model;
    private String colod;

    public Auto(String model, String colod, User user) {
        this.model = model;
        this.colod = colod;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto that = (Auto) o;
        return id == that.id && Objects.equals(model, that.model) && Objects.equals(colod, that.colod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, colod);
    }

    @Override
    public String toString() {
        String u = user != null ? String.valueOf(user.getId()) : "none";
        return "Auto{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", colod='" + colod + '\'' +
                ", user=" + u +
                '}';
    }
}
