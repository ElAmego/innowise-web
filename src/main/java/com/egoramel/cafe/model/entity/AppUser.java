package com.egoramel.cafe.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {
    private Long userId;
    private String login;
    private String userPassword;
    private String phoneNumber;
    private Integer loyaltyPoints;
    private Role userRole;
    private Status userStatus;
}