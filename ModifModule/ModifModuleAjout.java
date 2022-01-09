/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifModule;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.GroupeModule;
import fr.insa.zins.classe.Module;
import fr.insa.zins.classe.bdd2;

import fr.insa.zins.testvaadin.VuePrincipale;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifModuleAjout extends FormLayout{
   private VuePrincipale main;
    
    private TextField nom;
    private TextField description;
    private IntegerField idGM;
 
    private Button enregistrer;
    private final Button retour;
    
    public ModifModuleAjout(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Intitulé");
        this.description = new TextField("Description");
        this.idGM = new IntegerField("Numéro du groupe de module associé");
        this.enregistrer= new Button("Enregistrer");
        
       
        nom.setClearButtonVisible(true);

        description.setClearButtonVisible(true);
        try{
        this.idGM.setMin(1);
        this.idGM.setMax(GroupeModule.CompteGroupeModule(this.main.getConBdD()));
        idGM.setClearButtonVisible(true);
        idGM.setHasControls(true);
        }catch(SQLException ex2){
            
        }
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.enregistrer.addClickListener(event -> { 

         
            try{
         
                int id = bdd2.createModule(this.main.getConBdD(), this.nom.getValue(),this.description.getValue(), this.idGM.getValue());
                //createEtudiant(this.main.getConBdD(), this.vtnom.getValue(), this.vtprenom.getValue(), this.vtemail.getValue(), this.vtmdp.getValue());
                Module newModule = new Module(id, this.idGM.getValue(), this.nom.getValue(),this.description.getValue());
                //this.main.getSessionInfo().setCurUser(Optional.of(newEtudiant));
                Module.getListeModule().add(newModule);
                Notification.show("Module " + this.nom.getValue() + " créé");
                //méthode à appliquer au clic du bouton 
         
            } catch (Exception ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifModuleAjout.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        add(nom,description,idGM,enregistrer);
        
        this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifModule(this.main));
            } catch (SQLException ex) {
                Logger.getLogger(ModifModuleAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        }); 
    }  
}
