package com.rodrigo.flexmobilidade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String model;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "cars_grupo_name"))
    private Grupo grupo;

}
