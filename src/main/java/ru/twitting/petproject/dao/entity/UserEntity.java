package ru.twitting.petproject.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.twitting.petproject.model.base.UserAuthorityType;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user")
@Where(clause = "active = true")
@SQLDelete(sql = "UPDATE user SET active = false, modified = current_timestamp WHERE id = ?", check = ResultCheckStyle.COUNT)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGenerator")
    @SequenceGenerator(name = "userSeqGenerator", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "shownName", nullable = false)
    private String shownName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone_shown")
    private Boolean phoneShown;

    @Column(name = "email")
    private String email;

    @Column(name = "email_shown")
    private Boolean emailShown;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private UserAuthorityType authorityType = UserAuthorityType.USER;

}
