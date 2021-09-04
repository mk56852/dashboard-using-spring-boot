package com.dash.dash.dto;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto implements Serializable {

    private Long id ;
    private String firstName ;
    private String lastName ;
    private String email ;
    private String profileImageUrl ;
    private Date lastLoginDate ;
}
