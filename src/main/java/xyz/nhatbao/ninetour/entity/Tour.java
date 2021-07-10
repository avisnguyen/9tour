package xyz.nhatbao.ninetour.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

/*******************************************************************************
 <pre>

 Copyright (c) 2021 Nguyen Nhat Bao
 This project is licensed under the terms of the MIT license.

 Author: Nguyen Nhat Bao (Kian Nguyen)
 Website: https://kiandev.xyz
 Contact for work: kiannguyen.work@gmail.com
 Feedback to me: kiannguyen.dev@gmail.com
 Github: https://github.com/kian-nguyen

 Please do not remove.

 </pre>
 ******************************************************************************/

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql =
        "UPDATE tour " +
                "SET is_deleted = true " +
                "WHERE id = ?")
public class Tour extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    private String shortDescription;
    private String thumbnailUrl;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @ManyToOne
    @ToString.Exclude
    private Place departure;
    private String duringTime;
    @ManyToOne
    @ToString.Exclude
    private Place destination;
    private String transport;
    @Column(columnDefinition = "LONGTEXT")
    private String itinerary;
    @Column(columnDefinition = "LONGTEXT")
    private String termConditions;
    @Column(columnDefinition = "LONGTEXT")
    private String faq;

    @ManyToMany
    @ToString.Exclude
    private Set<Place> places = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    private Set<ExtraService> extraServices = new HashSet<>();

    @OneToMany(mappedBy = "tour")
    @ToString.Exclude
    private List<Trip> trips = new ArrayList<>();

    @OneToMany(mappedBy = "tour")
    @ToString.Exclude
    private List<Rating> ratings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tour tour = (Tour) o;

        return Objects.equals(getId(), tour.getId());
    }

    @Override
    public int hashCode() {
        return 15902139;
    }
}
