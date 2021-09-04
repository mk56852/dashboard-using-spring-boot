package com.dash.dash.Repository;


import com.dash.dash.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email) ;
    User findByUserName(String userName) ;
    User findUserById(Long id) ;

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enable = TRUE WHERE a.email = ?1")
    int enableUser(String email);
    User findByResetPasswordToken(String token) ;
}
