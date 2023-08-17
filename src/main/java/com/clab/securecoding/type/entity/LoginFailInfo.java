package com.clab.securecoding.type.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginFailInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user")
    private User user;

    private Long loginFailCount;

    private Boolean isLock;

    private LocalDateTime latestTryLoginDate;

    @Override
    public String toString() {
        return "LoginFailInfo{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", loginFailCount=" + loginFailCount +
                ", isLock=" + isLock +
                ", latestTryLoginDate=" + latestTryLoginDate +
                '}';
    }

}

