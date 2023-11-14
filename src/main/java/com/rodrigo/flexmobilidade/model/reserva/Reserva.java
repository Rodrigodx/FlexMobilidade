package com.rodrigo.flexmobilidade.model.reserva;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.model.protections.dto.ProtectionResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String location;

    private LocalDateTime inicial;

    private LocalDateTime finish;

    @Embedded
    private PersonalData personalData;

    @OneToOne
    private Protection protection;

    @OneToMany
    @JoinTable(name = "reserva_accessories")
    private List<Accessory> accessories = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "reserva_utilities")
    private List<AdditionalUtility> additionalUtilities = new ArrayList<>();
}
