package com.rodrigo.flexmobilidade.model.categories;


import com.rodrigo.flexmobilidade.model.cars.Cars;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categories_cars")
    private List<Cars> carsList;
}
