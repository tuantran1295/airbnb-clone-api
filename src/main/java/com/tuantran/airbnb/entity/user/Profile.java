package com.tuantran.airbnb.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "profile")
@RequiredArgsConstructor
@Getter
@Setter
public class Profile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "work")
    private String work;

    @Column(name = "about")
    private String about;

    @Column(name = "interest", columnDefinition = "text[]")
    @Type(ListArrayType.class)
    private List<String> interests;

    @Column(name = "status")
    private Integer status;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
