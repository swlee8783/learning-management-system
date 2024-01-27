package com.example.learningmanagementsystem.member.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Member {

    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt;

    private boolean isEmailAuthorized;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

}
