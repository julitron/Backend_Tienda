package com.proyecto.ciclo4G17.mitiendavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private Product product;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private Sale sale;
    @NotNull
    @Getter @Setter
    private int amount;
}
