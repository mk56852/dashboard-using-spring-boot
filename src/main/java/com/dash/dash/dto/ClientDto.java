package com.dash.dash.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClientDto implements Serializable {
    private Long id ;
    private String fullName ;
    private Date dateAdmit ;
    private String Status ;
    private String disease ;
    private String phoneNumber ;
}
