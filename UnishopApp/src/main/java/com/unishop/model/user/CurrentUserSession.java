package com.unishop.model.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CurrentUserSession {
    @Id
    @Column(unique = true)
    private Integer userID;
    private String unqID;
    private LocalDateTime localDateTime;
}
