package com.mario.rayas.atm.models;

import org.springframework.data.domain.Sort;

import javax.persistence.*;

/*
Desarrolldo por Mario Sergio Rayas Chávez
Correo mrayas@gmail.com
Fecha Mayo 8, 2022
Definicion del modelo, objeto y la tabla en base de datos mySQL utilizando persistencia con JPA
*/

@Entity
@Table(name="t_denomination")
public class DenominationModel {

    @Transient
    public static final Sort SORT_BY_CREATED_AT_DESC = 
                          Sort.by(Sort.Direction.DESC, "idDenomination");


    @Id

    @Column(unique = true,nullable = false)
    private double idDenomination;//valor de la denominación

    @Column(unique = false,nullable = false)
    private String type;// Si es billete o moneda

    @Column(unique = false,nullable = false)
    private Integer quantity;// Cantidad 

    public double getIdDenomination() {
        return idDenomination;
    }

    public void setIdDenomination(double idDenomination) {
        this.idDenomination = idDenomination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    

    
 
       
}
