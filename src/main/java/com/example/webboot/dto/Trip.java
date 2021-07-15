package com.example.webboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "trip", schema = "stydy_database")
@Getter
@Setter
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String road;
    private String date;

    public Trip(String road, String date) {
        this.road = road;
        this.date = date;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "auto_trip",
            joinColumns = {@JoinColumn(name = "trip_id")},
            inverseJoinColumns = {@JoinColumn(name = "auto_id")})
    private Set<Auto> autos = new HashSet<>();

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", road='" + road + '\'' +
                ", date='" + date + '\'' +
                ", autos=" + autos.stream().map(Auto::toString).collect(Collectors.joining("\n")) +
                '}';
    }

    public void addAuto(Auto auto) {
        this.autos.add(auto);
    }
}
