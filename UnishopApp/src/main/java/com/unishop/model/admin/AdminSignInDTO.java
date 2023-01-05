package com.unishop.model.admin;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminSignInDTO {


    private String mobile;
    @Size(min=4,max=12,message = "Password should has minimum 4 to 12 characters")
    private String password;
}
