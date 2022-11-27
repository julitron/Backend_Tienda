package com.proyecto.ciclo4G17.mitiendavirtual.model;

import com.proyecto.ciclo4G17.mitiendavirtual.security.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private Product product;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private User client;
    @NotNull
    @Getter @Setter
    private int amount;
}
