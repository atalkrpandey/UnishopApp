package com.unishop.model.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserDTO {
    @Id
    private Integer userID;
    @Size(min = 5,message = "User should be customer")
    private String userType = "User";
    @Size(min=4,max=12,message = "Password should has minimum 4 to 12 characters")
    private String password;
}
