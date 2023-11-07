package com.rodrigo.flexmobilidade.model.reserva;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PersonalData {

    private String name;

    private String cpf;

    private String email;

    private String telefone;

}
