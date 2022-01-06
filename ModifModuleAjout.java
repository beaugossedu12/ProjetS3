/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.ModuleDonnees;
import fr.insa.zins.classe.Module;
import fr.insa.zins.classe.bdd2;
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
    private TextField idGM;
 
    private Button enregistrer;

    
    public ModifModuleAjout(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Intitulé");
        this.description = new TextField("Description");
        this.idGM = new TextField("Numéro du groupe de module associé");
        this.enregistrer= new Button("Enregistrer");
        
       
        nom.setClearButtonVisible(true);

        description.setClearButtonVisible(true);


        idGM.setClearButtonVisible(true);

        
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                this.enregistrer.addClickListener(event -> { 

         
            try{
         
                int id = bdd2.createModule(this.main.getConBdD(), this.nom.getValue(),this.description.getValue(), Integer.parseInt(this.idGM.getValue()));
                //createEtudiant(this.main.getConBdD(), this.vtnom.getValue(), this.vtprenom.getValue(), this.vtemail.getValue(), this.vtmdp.getValue());
                Module newModule = new Module(id, Integer.parseInt(this.idGM.getValue()), this.nom.getValue(),this.description.getValue());
                //this.main.getSessionInfo().setCurUser(Optional.of(newEtudiant));
                Module.getListeModule().add(newModule);
                Notification.show("Module " + this.nom.getValue() + " créé");
                //méthode à appliquer au clic du bouton 
         
            } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifModuleAjout.class.getName()).log(Level.SEVERE, null, ex);
            } catch(Exception ex){
                Logger.getLogger(ModifModuleAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(nom,description,idGM,enregistrer);
    }  
}
