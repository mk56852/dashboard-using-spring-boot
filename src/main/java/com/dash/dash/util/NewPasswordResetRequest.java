package com.dash.dash.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NewPasswordResetRequest {

    private String newPassword ;
    private String confirmNewPassword ;
}
