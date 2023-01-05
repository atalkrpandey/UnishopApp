package com.unishop.model.admin;

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
public class CurrentAdminSession {
    @Id
    @Column(unique = true)
    private Integer adminID;
    private String uuid;
    private LocalDateTime localDateTime;
}
