package com.clab.securecoding.type.entity;

import com.clab.securecoding.type.etc.RequestState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REMOVE})
    private List<Contract> contracts;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    private AnimalAdoptionBoard animalAdoptionBoard;

    @Enumerated(EnumType.STRING)
    private RequestState requestState;

}
