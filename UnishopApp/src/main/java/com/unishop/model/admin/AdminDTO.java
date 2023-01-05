package com.unishop.model.admin;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AdminDTO {

    @Id
    private Integer adminID;
    @Size(min = 5,message = "User should be admin")
    private String userType = "Admin";
    @Size(min=4,max=12,message = "Password should has minimum 4 to 12 characters")
    private String password;
}
