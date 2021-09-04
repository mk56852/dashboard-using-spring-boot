package com.dash.dash.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    @ManyToMany(mappedBy = "specialities")
    private Set<User> users ;

    public Speciality(String name) {
        this.name = name;
    }

    public void addUser(User user)
    {
        users.add(user) ;
     }
}
