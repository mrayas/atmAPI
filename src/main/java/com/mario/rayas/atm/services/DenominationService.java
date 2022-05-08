package com.mario.rayas.atm.services;

import java.util.ArrayList;


import java.util.Optional;


import com.mario.rayas.atm.models.DenominationModel;
import com.mario.rayas.atm.repositories.DenominationRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
Desarrolldo por Mario Sergio Rayas Chávez
Correo mrayas@gmail.com
Fecha Mayo 8, 2022
Las funciones utilizada para aplicar las reglas de negocio
*/


@Service
public class DenominationService {
    @Autowired
    DenominationRepository oDenominationRepository;

    public ArrayList<DenominationModel> getDenominations(){
        //Obtiene todas las denominaciones en la base de datos
        return (ArrayList<DenominationModel>) oDenominationRepository.findAll();
    }

    public DenominationModel saveDenomination(DenominationModel oDenomination){
        //Alacena la denomiación en la base de datos
        return oDenominationRepository.save(oDenomination);
    }

    public DenominationModel[] saveDenominations(DenominationModel[] aoDenomination){
        //Almacena una lista de denominaciones en la base de datos NO esta optimizado por razones de tiempo
        for (DenominationModel oDenomination : aoDenomination) {
            oDenominationRepository.save(oDenomination);    
        }

        return aoDenomination;

        
    }
    //
    public ArrayList<DenominationModel> getDenominationsByAmount(Double amount){
        //La lista que se devolvera con las denominaciones necesarias para cubir el monto solicitado
        ArrayList<DenominationModel> lDenomination = new ArrayList<DenominationModel>();
        //Lista de denominaciones disponibles en base de datos
        ArrayList<DenominationModel> lAvailableDenominations=(ArrayList<DenominationModel>) oDenominationRepository.findAll();

        DenominationModel[] aoAvailableDenominations = lAvailableDenominations.stream().toArray( n -> new DenominationModel[n]);
        //La lista NO esta ordenada por ello no se utiliza 
        /*for (DenominationModel oAvailableDenomination : aoAvailableDenominations){
            oAvailableDenomination.setQuantity(1);
            lDenomination.add(oAvailableDenomination);
        }*/
        double dAmout=amount;
        DenominationModel oDenomination = null;
        /*
            --Codigo para saber las denominaciones y las cantidades de necesitan para cubir el monto solicitado--
        */
        while(dAmout>=0.5){//Monto minimo $0.50
            for (int i=aoAvailableDenominations.length-1;i>=0;i--){
                //Si hay denominaciones disponibles y la denominación no sea mayor al monto requerido
                while(aoAvailableDenominations[i].getQuantity()>0 && dAmout-aoAvailableDenominations[i].getIdDenomination()>=0){
                    
                    if (oDenomination==null){
                        oDenomination = new DenominationModel();
                        oDenomination.setIdDenomination(aoAvailableDenominations[i].getIdDenomination());
                        oDenomination.setType(aoAvailableDenominations[i].getType());
                        oDenomination.setQuantity(0);
                    }

                    dAmout=dAmout-aoAvailableDenominations[i].getIdDenomination();
                    oDenomination.setQuantity(oDenomination.getQuantity()+1);
                    aoAvailableDenominations[i].setQuantity(aoAvailableDenominations[i].getQuantity()-1);
                    
                }
                if (oDenomination!=null)
                    lDenomination.add(oDenomination);

                
                if (dAmout<0.5)
                    break;//Si el monto restante requerido es menor que $0.50 termina;
                else
                    oDenomination = null;//Continua con la siguiente denominación ya que aun no se cubre el monto
                
            }
        }
        

        return lDenomination;
    }

    public Optional<DenominationModel> getDenominationId(Double id){
        return oDenominationRepository.findById(id);
    }

    public ArrayList<DenominationModel> getDenominationType(String type){
        return oDenominationRepository.findByType(type);
    }

    public boolean deleteDenomination(Double id){
        try{
            oDenominationRepository.deleteById(id);
            return true;
        }catch( Exception e){
            return false;

        }
    }
}
