package com.proyecto.ciclo4G17.mitiendavirtual.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @NotBlank @NotNull
    @Getter @Setter
    private String name;
    @NotNull @DecimalMin(value = "0.1")
    @Getter @Setter
    private Double price;
    @NotBlank @NotNull
    @Getter @Setter
    private String description;
    @NotBlank @NotNull
    @Getter @Setter
    private String category;
    @Getter @Setter
    private String image;
}