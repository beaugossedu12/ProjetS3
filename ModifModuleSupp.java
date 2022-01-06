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

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifModuleSupp extends FormLayout {
          private VuePrincipale main;
    
    private TextField nom;

    private Button enregistrer;
    private final Button retour;
    
   // private EtudiantDonneesTest etudiantDonneesTest;
    public ModifModuleSupp(VuePrincipale main) {
   
        this.main = main;
        //this.etudiantDonneesTest=etudiantDonneesTest;
        this.nom = new TextField("Intitulé");
        this.enregistrer= new Button("Enregistrer");
 
        nom.setClearButtonVisible(true);


        
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.enregistrer.addClickListener(event -> { 
                 try{
                 fr.insa.zins.classe.Module.deleteModule(this.main.getConBdD(), this.nom.getValue());
                 Notification.show("Module supprimé");
                } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifModuleSupp.class.getName()).log(Level.SEVERE, null, ex);
            } 
             });
        add(nom,enregistrer);
        this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifModule(this.main));
            } catch (SQLException ex) {
                Logger.getLogger(ModifModuleSupp.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
    }  
}
