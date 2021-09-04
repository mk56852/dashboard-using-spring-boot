package com.dash.dash.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
public class AddClientRequest {

    private String fullName ;
    private String Status ;
    private String disease ;
    private String phoneNumber ;
}
