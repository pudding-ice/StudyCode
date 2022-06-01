package com.myjava.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Data
public class User {
    Integer u_id;
    String username;
    String password;
}
