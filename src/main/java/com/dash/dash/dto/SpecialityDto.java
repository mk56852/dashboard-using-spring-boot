package com.dash.dash.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SpecialityDto implements Serializable {
    private Long id ;
    private String name ;
}
