package xyz.nhatbao.ninetour.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        "UPDATE region " +
                "SET is_deleted = true " +
                "WHERE id = ?")
public class Region extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    private String shortDescription;
    private String thumbnailUrl;
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @OneToMany(mappedBy = "region")
    @ToString.Exclude
    private List<Place> places = new ArrayList<>();

//    @OneToMany(mappedBy = "region")
//    private List<AnonymousUser> anonymousUsers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Region region = (Region) o;

        return Objects.equals(getId(), region.getId());
    }

    @Override
    public int hashCode() {
        return 271369428;
    }
}
