package com.dash.dash.util;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PasswordResetRequest {

    private final String userName ;
    private final String email ;



}
