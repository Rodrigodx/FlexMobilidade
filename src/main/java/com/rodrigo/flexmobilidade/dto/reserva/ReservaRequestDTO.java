package com.rodrigo.flexmobilidade.dto.reserva;

import com.rodrigo.flexmobilidade.dto.accessories.AccessoryDTO;
import com.rodrigo.flexmobilidade.dto.additonalutility.AdditionalUtilityDTO;
import com.rodrigo.flexmobilidade.model.reserva.PersonalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDTO {

    private String location;

    private LocalDateTime inicial;

    private LocalDateTime finish;

    private PersonalData personalData;

    private Integer Category;

    private Integer Protection;

    private List<AccessoryDTO> accessories = new ArrayList<>();

    private List<AdditionalUtilityDTO> additionalUtilities = new ArrayList<>();

}
