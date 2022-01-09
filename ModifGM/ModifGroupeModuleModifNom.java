/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifGM;

import fr.insa.zins.testvaadin.ModifGM.ModifGroupeModuleModif;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.GroupeModule;
import static fr.insa.zins.classe.GroupeModule.trouveGroupeModule;
import fr.insa.zins.testvaadin.VuePrincipale;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifGroupeModuleModifNom extends FormLayout{
      private VuePrincipale main;


    private TextField nom;
    private TextField nomModifie;
    private Button enregistrer;
    private Button retour;
    
    public ModifGroupeModuleModifNom(VuePrincipale main){
        //try (Connection con = testConnect()) {
            this.main = main;

            this.nom = new TextField("Intitulé");
            this.nomModifie = new TextField("Nouvel intitulé");

            this.enregistrer= new Button("Enregistrer");


            enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                        this.enregistrer.addClickListener(event -> { 
                 try{
                     this.main.getConBdD().setAutoCommit(false); 
                 GroupeModule.ModifNomGroupeModule(this.main.getConBdD(), this.nom.getValue(), this.nomModifie.getValue());
                GroupeModule newGModule =new GroupeModule (trouveGroupeModule(this.main.getConBdD(),this.nom.getValue()), this.nomModifie.getValue(),GroupeModule.trouveDescriptionGroupeModule(this.main.getConBdD(), this.nom.getValue()));
                GroupeModule.getListeGroupeModule().set(trouveGroupeModule(this.main.getConBdD(),this.nom.getValue())-1,newGModule);
                 Notification.show("Nouveau nom enregistré");
                } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifGroupeModuleModifNom.class.getName()).log(Level.SEVERE, null, ex);
            } 
             });
            add(nom,nomModifie,enregistrer);

                   
      this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifGroupeModuleModif(this.main));
            } catch (Exception ex) {
                Logger.getLogger(ModifGroupeModuleModifNom.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
        }  
}
