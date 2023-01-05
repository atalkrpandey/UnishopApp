package com.unishop.model.admin;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminID;

    @NotNull
    private String adminName;

    @Size(min=4,max=12,message = "Password should has minimum 4 to 12 characters")
    private String password;

    @NotNull
    private String address;

    @Size(min=10, message ="Mobile Number should be of 10 digits!")
    private String mobileNumber;

    @Email
    private String email;

}
