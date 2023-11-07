package com.rodrigo.flexmobilidade.model.categories;


import jakarta.persistence.*;
import lombok.Data;





@Data
@Embeddable
public class Grupo {
    private String name;
}
