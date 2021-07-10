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
        "UPDATE place " +
                "SET is_deleted = true " +
                "WHERE id = ?")
public class Place extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    private String shortDescription;
    private String thumbnailUrl;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String lat;
    private String lng;
    private String pt;
    private String hd;

    @ManyToOne
    @ToString.Exclude
    private Region region;

    @ManyToOne
    @ToString.Exclude
    private Place parentPlace;

    @OneToMany(mappedBy = "parentPlace")
    @ToString.Exclude
    private List<Place> childPlaces = new ArrayList<>();

    @ManyToMany(mappedBy = "places")
    @ToString.Exclude
    private Set<Tour> tours = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Place place = (Place) o;

        return Objects.equals(getId(), place.getId());
    }

    @Override
    public int hashCode() {
        return 1945780767;
    }
}
