package com.proyecto.ciclo4G17.mitiendavirtual.model;

import com.proyecto.ciclo4G17.mitiendavirtual.security.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @NotNull
    @Getter @Setter
    private Double total;
    @NotNull
    @Getter @Setter
    @Column(columnDefinition = "DATE")
    private Date date;
    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @Getter @Setter
    private User client; 

    public Sale(Double total, Date date, User client) {
        this.total = total;
        this.date = date;
        this.client = client;
    }
    
    
}