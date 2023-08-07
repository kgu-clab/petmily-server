package com.clab.securecoding.type.entity;

import com.clab.securecoding.type.etc.OAuthProvider;
import com.clab.securecoding.type.etc.Role;
import com.clab.securecoding.type.etc.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String id;

    private String password;

    private String nickname;

    private String email;

    private String address;

    private String contact;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "writer", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    private List<Board> boards;

    @OneToMany(mappedBy = "writer", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    private List<Comment> comments;

    @OneToMany(mappedBy = "writer", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    private List<TradeComment> tradeComments;

    @OneToMany(mappedBy = "seller", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    private List<TradeBoard> tradeBoards;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    private List<Animal> animals;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(getRole().getKey()));
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
