package com.dash.dash.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String firstName ;
    private String lastName ;
    @Column(unique = true)
    private String userName ;
    @Column(unique = true)
    private String email ;
    private String password ;
    private String profileImageUrl ;
    private Date lastLoginDate ;
    private boolean enable = true ;
    private boolean isLocked = false ;
    private String resetPasswordToken= null;

    @ManyToMany
    @JoinTable(name = "users_specialities" ,joinColumns = @JoinColumn (name = "user_id") , inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities;


    @ManyToMany()
    @JoinTable(name = "user_friend" ,joinColumns = @JoinColumn (name = "user_id") , inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends ;
    @ManyToMany
    @JoinTable(name = "user_client" ,joinColumns = @JoinColumn (name = "user_id") , inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> clients ;

    @ManyToMany(mappedBy = "usersFile")
    private Set<File> files;

    public User(String firstName, String lastName, String userName, String email, String password, String profileImageUrl, Date lastLoginDate, boolean enable, boolean isLocked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.lastLoginDate = lastLoginDate;
        this.enable = enable;
        this.isLocked = isLocked;


    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null ;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isLocked ;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }




    public void setClient(Client client )
    {
        this.clients.add(client) ;
    }

    public void setFriend(User user)
    {
        this.friends.add(user) ;
    }

    public boolean isFriend(User user)
    {
        if(friends.contains(user))
            return true ;
        else
            return false ;
    }


    public void addFile(File file)
    {
        files.add(file) ;
    }

    public void addSpecility(Speciality speciality)
    {
        specialities.add(speciality) ;
    }

}
