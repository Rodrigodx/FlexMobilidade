package com.rodrigo.flexmobilidade.model.reserva;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalData {

    private String name;

    private String cpf;

    private String email;

    private String telefone;
}
