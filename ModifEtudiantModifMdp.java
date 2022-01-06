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
import fr.insa.zins.classe.Etudiant;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifEtudiantModifMdp extends FormLayout{
    private VuePrincipale main;

    private TextField mdp;
    private TextField nom;
    private Button enregistrer;
     private final Button retour;
     //private EtudiantDonneesTest etudiantDonneesTest;
    public ModifEtudiantModifMdp(VuePrincipale main) {
   
        this.main = main;
       // this.etudiantDonneesTest=etudiantDonneesTest;
        this.nom = new TextField("Nom");
        this.mdp = new TextField("Nouveau mot de passe");
 
        this.enregistrer= new Button("Enregistrer");


        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.enregistrer.addClickListener(event -> { 
                 try{
                 Etudiant.ModifmdpEtudiant(this.main.getConBdD(), this.nom.getValue(), this.mdp.getValue());
                 Notification.show("Nouveau mot de passe enregistré");
                } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifEtudiantModifMdp.class.getName()).log(Level.SEVERE, null, ex);
            } 
             });
        add(nom,mdp,enregistrer);
                this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifEtudiant(this.main));
            } catch (ClassNotFoundException|SQLException ex) {
                Logger.getLogger(ModifEtudiantAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
    }  
}


