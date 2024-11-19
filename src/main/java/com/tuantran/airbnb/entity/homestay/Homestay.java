package com.tuantran.airbnb.entity.homestay;

import com.tuantran.airbnb.dto.homestay.response.HomestayDetail;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@SqlResultSetMapping(
        name = "HomestayDetailMapping",
        classes = @ConstructorResult(
                targetClass = HomestayDetail.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "type", type = Integer.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "phone_number", type = String.class),
                        @ColumnResult(name = "address", type = String.class),
                        @ColumnResult(name = "guests", type = Integer.class),
                        @ColumnResult(name = "bedrooms", type = Integer.class),
                        @ColumnResult(name = "bathrooms", type = Integer.class),
                        @ColumnResult(name = "images", type = List.class),
                        @ColumnResult(name = "avg_price", type = Double.class)
                }
        )
)

@Entity
@Table(name = "homestay")
@RequiredArgsConstructor
@Getter
@Setter
public class Homestay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "images", columnDefinition = "text[]")
    @Type(ListArrayType.class)
    private List<String> images;

    @Column(name = "guests")
    private Integer guests;

    @Column(name = "bedrooms")
    private Integer bedrooms;

    @Column(name = "bathrooms")
    private Integer bathrooms;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "homestay_amenity",
            joinColumns = @JoinColumn(name = "homestay_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;
}