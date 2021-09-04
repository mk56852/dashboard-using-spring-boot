package com.dash.dash.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String fullName ;
    private Date dateAdmit ;
    private String Status ;
    private String disease ;
    private String phoneNumber ;
    @ManyToMany(mappedBy = "clients")
    private Set<User> Doctors ;

    public Client(String fullName, Date dateAdmit, String status, String disease, String phoneNumber) {
        this.fullName = fullName;
        this.dateAdmit = dateAdmit;
        Status = status;
        this.disease = disease;
        this.phoneNumber = phoneNumber;
    }
}

