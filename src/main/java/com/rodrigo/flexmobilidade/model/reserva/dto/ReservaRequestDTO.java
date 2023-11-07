package com.rodrigo.flexmobilidade.model.reserva.dto;

import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.accessories.dto.AccessoryDTO;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.additionalutility.dto.AdditionalUtilityDTO;
import com.rodrigo.flexmobilidade.model.protections.Protection;
import com.rodrigo.flexmobilidade.model.reserva.PersonalData;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReservaRequestDTO {
    private Integer id;

    private String location;

    private LocalDateTime inicial;

    private LocalDateTime finish;

    private PersonalData personalData;

    private Integer idProtection;

    private List<AccessoryDTO> accessories = new ArrayList<>();

    private List<AdditionalUtilityDTO> additionalUtilities = new ArrayList<>();

}