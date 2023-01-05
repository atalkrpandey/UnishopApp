package com.unishop.model.user;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignInDTO {
    private String mob;
    @Size(min=4,max=12,message = "Password should has minimum 4 to 12 characters")
    private String password;
}
