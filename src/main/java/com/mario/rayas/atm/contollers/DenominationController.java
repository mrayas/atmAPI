package com.mario.rayas.atm.contollers;

import java.util.ArrayList;
import java.util.Optional;

import com.mario.rayas.atm.models.DenominationModel;
import com.mario.rayas.atm.services.DenominationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/*
Desarrolldo por Mario Sergio Rayas Chávez
Correo mrayas@gmail.com
Fecha Mayo 8, 2022
Metodos de la API rest
*/

@RestController
@RequestMapping("/denomination")
public class DenominationController {
    @Autowired
    DenominationService oDenominationService;

    //Lista las denominaciones almacenadas en la BD
    @GetMapping(path = "/about")//http://localhost:8080/denomination/about
    public String  about(){
        
        String about="{'name':'Mario Sergio Rayas Chávez','mail':'mrayas@gmail.com','version':'20220508.1.0.0'}";
        return  about;
    }
    //Lista las denominaciones almacenadas en la BD
    @GetMapping(path = "/getDenominations")//http://localhost:8080/denomination/getDenominations
    public ArrayList<DenominationModel>  getDenominations(){
        return oDenominationService.getDenominations();
    }
    //Agrega una denominación a la base de datos
    @PostMapping(path = "/saveDenomination")//http://localhost:8080/denomination/saveDenomination
    public DenominationModel saveDenomination(@RequestBody DenominationModel oDenomination){
        
        return oDenominationService.saveDenomination(oDenomination);

    }
    //Agrega una lista de denominaciones a la base de datos
    @PostMapping(path = "/saveDenominations")//http://localhost:8080/denomination/saveDenominations
    public DenominationModel[] saveDenominations(@RequestBody DenominationModel[] aoDenomination){
        
        return oDenominationService.saveDenominations(aoDenomination);

    }

    //Muestra los datos de la denominación por id denominacio (monto)
    @GetMapping(path = "/getDenominationById/{id}")//http://localhost:8080/denomination/getDenominationById/100
    public Optional<DenominationModel>  getDenominationById(@PathVariable("id") Double id){
        return oDenominationService.getDenominationId(id);
    }

    //Muestra lista de nominaciones por tipo billete type=b o moneda type=m
    @GetMapping(path = "/query")//http://localhost:8080/denomination/query?type=b
    public ArrayList<DenominationModel>  getDenominationByType(@RequestParam("type") String type){
        return oDenominationService.getDenominationType(type);
    }

    //Elimina denominación de la base de datos por el id denominación
    @DeleteMapping(path = "/deleteDenominationById/{id}")//http://localhost:8080/denomination/deleteDenominationById/0.5
    public String  deleteDenominationById(@PathVariable("id") Double id){
        if (oDenominationService.deleteDenomination(id)){
            return "Se eliminó la denominacion de: $"+id;
        }else{
            return "No se eliminó la denominación de: $"+id;
        }
    }
    /*
        Obtiene las denominaciones y cantidades de la base de datos necesarias para cubrir el monto solicitado
        Siempre se considera el monto total inicial de $12,550.00, es decir no decrementa las denominaciones disonibles
        Si se solicitan montos inferiores a 0.50 no se consideran
    */
    @GetMapping(path = "/getDenominationsByAmount/{amount}")//http://localhost:8080/denomination/getDenominationsByAmount/100.50
    public ArrayList<DenominationModel>  getDenominationsByAmount(@PathVariable("amount") Double amount){
        if (amount<0.50 || amount>12550){
            ArrayList<DenominationModel> aL = new ArrayList<>();
            return aL;
        }
        else
            return oDenominationService.getDenominationsByAmount(amount);
    }
}
