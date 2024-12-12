package com.ideanet.test_reactive.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
}
