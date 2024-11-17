package com.tuantran.airbnb.entity.homestay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "amenity")
@RequiredArgsConstructor
@Getter
@Setter
public class Amenity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "amenities",
            fetch = FetchType.LAZY
    )
    private Set<Homestay> homestays;
}
