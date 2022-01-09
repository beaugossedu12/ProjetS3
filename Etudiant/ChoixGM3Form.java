/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Etudiant;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import static fr.insa.zins.classe.Module.trouveModule2;
import fr.insa.zins.classe.Voeux;
import static fr.insa.zins.classe.Voeux.getListeVoeux;
import static fr.insa.zins.classe.Voeux.trouveVoeux;
import fr.insa.zins.classe.bdd2;
import fr.insa.zins.testvaadin.VuePrincipale;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ChoixGM3Form extends VerticalLayout {
    private VuePrincipale main;
    private H3 titre;

    //private final Button retour;
    private Button valider;
    public ChoixGM3Form(VuePrincipale main) {
         this.main = main;
        int a=0;
        TextField autre = new TextField("Autre :");
        TextField autre2 = new TextField("Autre :");
        TextField autre3 = new TextField("Autre :");
       autre.setValue("choix 1");
        autre2.setValue("choix 2");
        autre3.setValue("choix 3");
        this.titre = new H3("SPORT");
        titre.setSizeFull();
        this.add(this.titre);
        
        RadioButtonGroup<String> choix1= new RadioButtonGroup<>();
        choix1.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        choix1.setLabel("Choix 1");
        choix1.setItems("Kayak","Aviron","Course à pied");
        this.add(choix1,autre);
        
        RadioButtonGroup<String> choix2= new RadioButtonGroup<>();
        choix2.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        choix2.setLabel("Choix 2");
        choix2.setItems("Kayak","Aviron","Course à pied");
        add(choix2,autre2);
        
        RadioButtonGroup<String> choix3= new RadioButtonGroup<>();
        choix3.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        choix3.setLabel("Choix 3");
        choix3.setItems("Kayak","Aviron","Course à pied");
        this.add(choix3,autre3);
        

                 this.valider = new Button("Valider");
        this.valider.addClickListener((e) -> {
            try{
            if (choix1.getValue()==choix2.getValue()||choix1.getValue()==choix3.getValue()||choix2.getValue()==choix3.getValue()){
                Notification.show("Erreur : vous avez selectionné deux fois le même module");
            }if (autre.getValue()==autre2.getValue()||autre.getValue()==autre3.getValue()||autre2.getValue()==autre3.getValue()){
                Notification.show("Erreur : vous avez selectionné deux fois le même module");
            }if (autre.getValue()==choix2.getValue()||autre.getValue()==choix3.getValue()||autre2.getValue()==choix1.getValue()||autre2.getValue()==choix3.getValue()||autre3.getValue()==choix1.getValue()||autre3.getValue()==choix2.getValue()){
                Notification.show("Erreur : vous avez selectionné deux fois le même module");
            }if (trouveModule2(this.main.getConBdD(), autre.getValue(), 3)==-1||trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)==-1||trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)==-1){
                Notification.show("Erreur : ce module n'existe pas dans ce groupe de module");    
            }if (trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=-1 && choix2.getValue()!=choix3.getValue()&&trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)==-1&&trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)==-1){
                    if (trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix3.getValue(), 3)&& trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix2.getValue(), 3)){
                
                    this.main.getConBdD().setAutoCommit(false);        
                    int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                    Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                    getListeVoeux().add(newVoeux);
                    Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre.getValue(),3));
                    Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix2.getValue(),3));
                    Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix3.getValue(),3));
                    Notification.show("Choix 1 ok");
                    this.main.changeContenu(new EtudiantFin(this.main)); 
                    this.main.avancerBarre(this.main);
                }else{
                       Notification.show("Erreur ici");   
                    }
            }if (trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=-1 && choix1.getValue()!=choix3.getValue()&&trouveModule2(this.main.getConBdD(), autre.getValue(), 3)==-1&&trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)==-1){
                 if (trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix3.getValue(), 3)&& trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix1.getValue(), 3)){
               
                
                this.main.getConBdD().setAutoCommit(false);        
                int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                getListeVoeux().add(newVoeux);
                Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix1.getValue(),3));
                Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre2.getValue(),3));
                Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix3.getValue(),3));
                Notification.show("Choix 2 ok");
                this.main.changeContenu(new EtudiantFin(this.main)); 
                this.main.avancerBarre(this.main);
                }else{ 
                     Notification.show("Erreur !");
                 }  
            }if (trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=-1 && choix1.getValue()!=choix2.getValue()&&trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)==-1&&trouveModule2(this.main.getConBdD(), autre.getValue(), 3)==-1){
                if (trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix1.getValue(), 3)&& trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix2.getValue(), 3)){
              
                
                this.main.getConBdD().setAutoCommit(false);        
                int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                getListeVoeux().add(newVoeux);
                Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix1.getValue(),3));
                Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix2.getValue(),3));
                Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre3.getValue(),3));
                Notification.show("Choix 3 ok");
                this.main.changeContenu(new EtudiantFin(this.main)); 
                this.main.avancerBarre(this.main);
                }else{
                     Notification.show("Erreur !!");
                } 
            }if (trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=-1 &&trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=-1 && trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)){
                if (trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix3.getValue(), 3)&& trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix3.getValue(), 3)){
              
                
                this.main.getConBdD().setAutoCommit(false);        
                int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                getListeVoeux().add(newVoeux);
                Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre.getValue(),3));
                Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre2.getValue(),3));
                Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix3.getValue(),3));
                Notification.show("Choix 4 ok");
                this.main.changeContenu(new EtudiantFin(this.main)); 
                this.main.avancerBarre(this.main);
                }else{
                     Notification.show("Erreur !!!");
                } 
            }if (trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=-1 &&trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=-1  && trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)){
                if (trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix2.getValue(), 3)&& trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix2.getValue(), 3)){
              
                
                this.main.getConBdD().setAutoCommit(false);        
                int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                getListeVoeux().add(newVoeux);
                Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre.getValue(),3));
                Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix2.getValue(),3));
                Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre3.getValue(),3));
                Notification.show("Choix 5 ok");
                this.main.changeContenu(new EtudiantFin(this.main)); 
                this.main.avancerBarre(this.main);
                }else{
                     Notification.show("Erreur !!!!");
                } 
            }if (trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=-1 &&trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=-1  && trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)){
                if (trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix1.getValue(), 3)&& trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=trouveModule2(this.main.getConBdD(), choix1.getValue(), 3)){
              
                
                this.main.getConBdD().setAutoCommit(false);        
                int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                getListeVoeux().add(newVoeux);
                Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix1.getValue(),3));
                Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre2.getValue(),3));
                Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre3.getValue(),3));
                Notification.show("Choix 6 ok");
                this.main.changeContenu(new EtudiantFin(this.main)); 
                this.main.avancerBarre(this.main);
                }else{
                     Notification.show("Erreur !!!!!");
                }       


             //tout les textfield sont selectionnés
            }if(trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=-1 && trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=-1  && trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), autre3.getValue(), 3) && trouveModule2(this.main.getConBdD(), autre2.getValue(), 2)!=-1 && trouveModule2(this.main.getConBdD(), autre.getValue(), 3)!=trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)  && trouveModule2(this.main.getConBdD(), autre3.getValue(), 3)!=trouveModule2(this.main.getConBdD(), autre2.getValue(), 3)){
                    this.main.getConBdD().setAutoCommit(false);        
                    int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                    Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                    getListeVoeux().add(newVoeux);
                    Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre.getValue(),3));
                    Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre2.getValue(),3));
                    Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),autre3.getValue(),3));
                    Notification.show("Choix 24 ok");
                    this.main.changeContenu(new EtudiantFin(this.main)); 
                    this.main.avancerBarre(this.main);
            
            //les 3 boutons sont selectionnés    
            }if (choix1.getValue()!=null&choix2.getValue()!=null&choix3.getValue()!=null && choix1.getValue()!=choix2.getValue()&choix3.getValue()!=choix2.getValue()&choix1.getValue()!=choix3.getValue()){         
                try{
                this.main.getConBdD().setAutoCommit(false);        
                int id = bdd2.createVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID(), 3, a, a, a);
                Voeux newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
                getListeVoeux().add(newVoeux);
                Voeux.ModifChoix1AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix1.getValue(),3));
                Voeux.ModifChoix2AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix2.getValue(),3));
                Voeux.ModifChoix3AvecModule(this.main.getConBdD(), this.main.getSessionInfo().getUserID(),3, trouveModule2(this.main.getConBdD(),choix3.getValue(),3));
                /*int i=1;
                while (i!= trouveVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID())){
                    i++;
                }*/
                 Notification.show("ici");
                    try{
                    getListeVoeux().set(trouveVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID())-1,newVoeux);
                    }catch(Exception ex1){
                        System.out.println("erreur là");
                    }
                }catch(SQLException ex){
                    System.out.println("Erreur ici");
                     Logger.getLogger(Etudiant3Choix1.class.getName()).log(Level.SEVERE, null, ex);
                }
               this.main.changeContenu(new EtudiantFin(this.main)); 
                this.main.avancerBarre(this.main);
            }
            }catch(Exception ex){
                
            }
              });
        this.add(this.valider);
        valider.setWidthFull();
    }
    
}
