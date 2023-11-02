package com.rodrigo.flexmobilidade.model.reserva.dto;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.model.reserva.PersonalData;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ReservaResponseDTO {
    private Integer id;

    private String location;

    private LocalDateTime inicial;

    private LocalDateTime finish;

    private PersonalData personalData;

    private Protection protection;

    private List<Accessory> accessory;

    private List<AdditionalUtility> additionalUtility;
}
