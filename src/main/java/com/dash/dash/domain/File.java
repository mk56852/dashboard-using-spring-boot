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
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long clientId ;
    @ManyToMany
    @JoinTable(name = "users_files" ,joinColumns = @JoinColumn (name = "file_id") , inverseJoinColumns = @JoinColumn(name = "user_id"))
    private  Set<User> usersFile;
    private String fileName ;
    @Lob
    private byte[] data ;

    public File(Long clientId, String fileName, byte[] data) {
        this.clientId = clientId;
        this.fileName = fileName;
        this.data = data;
    }

    public void addUser(User user)
    {
        usersFile.add(user) ;
    }
}
