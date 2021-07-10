package xyz.nhatbao.ninetour.entity;//package xyz.nhatbao.ninetour.entity;
//
//import lombok.Data;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.Where;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@Where(clause = "is_deleted = false")
//@SQLDelete(sql =
//        "UPDATE anonymous_user " +
//                "SET is_deleted = true " +
//                "WHERE id = ?")
//public class AnonymousUser extends BaseEntity {
//
//    private static final long serialVersionUID = 1L;
//
//    private String sex;
//    private String nationality;
//
////    @ManyToOne
////    private Region region;
////
////    @OneToMany(mappedBy = "anonymousUser")
////    private List<Ticket> tickets = new ArrayList<>();
//}
