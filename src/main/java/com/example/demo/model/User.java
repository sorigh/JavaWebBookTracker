package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    private String password;

    @Singular
    // when read from table of users, we fetch all others (roles, etc)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "USERS_ID",
                            referencedColumnName = "ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLES_ID",
                            referencedColumnName = "ID")})

    private List<Role> roles; //important! roles saved as list


    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private Boolean enabled = true;

    private String firstName;
    private String lastName;

    @NaturalId(mutable = true)
    private String emailAddress;
    private LocalDate birthdate;

}
