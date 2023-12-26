package com.rodrigo.flexmobilidade.dto.reserva;


import com.rodrigo.flexmobilidade.model.accessories.Accessory;
import com.rodrigo.flexmobilidade.model.additionalutility.AdditionalUtility;
import com.rodrigo.flexmobilidade.model.categories.Category;
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

    private Category category;

    private Protection protection;

    private List<Accessory> accessories;

    private List<AdditionalUtility> additionalUtilities;
}
