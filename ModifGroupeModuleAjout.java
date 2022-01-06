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
import fr.insa.zins.classe.GroupeModule;
import fr.insa.zins.classe.ModuleDonnees;
import fr.insa.zins.classe.bdd2;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifGroupeModuleAjout  extends FormLayout{
   private VuePrincipale main;
    
    private TextField nom;
    private TextField description;

    private Button retour;
    private Button enregistrer;

    
    public ModifGroupeModuleAjout(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Intitulé");
        this.description = new TextField("Description");
 
        this.enregistrer= new Button("Enregistrer");
        
       
        nom.setClearButtonVisible(true);

        description.setClearButtonVisible(true);

        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                        this.enregistrer.addClickListener(event -> { 
            //Connection con = this.main.getSessionInfo().getConBdDE();
             //Connection con= this.main.testConnect();
            //String nom = this.vtnom.getValue();
            //String prenom= this.vtprenom.getValue();
            //String email = this.vtemail.getValue();
           // String mdp = this.vtmdp.getValue();
         
            try{
         
                int id = bdd2.createGroupeModule(this.main.getConBdD(), this.nom.getValue(),this.description.getValue());
                //createEtudiant(this.main.getConBdD(), this.vtnom.getValue(), this.vtprenom.getValue(), this.vtemail.getValue(), this.vtmdp.getValue());
                fr.insa.zins.classe.GroupeModule newGModule = new fr.insa.zins.classe.GroupeModule(id, this.nom.getValue(),this.description.getValue());
                //this.main.getSessionInfo().setCurUser(Optional.of(newEtudiant));
                GroupeModule.getListeGroupeModule().add(newGModule);
                Notification.show("Module " + this.nom.getValue() + " créé");
                //méthode à appliquer au clic du bouton 
         
            } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifGroupeModuleAjout.class.getName()).log(Level.SEVERE, null, ex);
            } catch(Exception ex){
                Logger.getLogger(ModifGroupeModuleAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        add(nom,description,enregistrer);
        
        this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifGroupeModule(this.main));
            } catch (Exception ex) {
                Logger.getLogger(ModifGroupeModuleAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
    }  
}
