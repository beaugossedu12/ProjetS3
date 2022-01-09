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
import com.vaadin.flow.component.textfield.TextField;
import static fr.insa.zins.classe.Module.trouveModule;
import fr.insa.zins.testvaadin.VuePrincipale;
import fr.insa.zins.classe.Module;
import static fr.insa.zins.classe.Module.getListeModule;
import static fr.insa.zins.classe.Module.trouveDescriptionModule;
import static fr.insa.zins.classe.Module.trouveIdGMModule;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifModuleModifNom extends FormLayout{
      private VuePrincipale main;


    private TextField nom;
    private TextField nomModifie;
    private Button enregistrer;
    private final Button retour;
    
   // private EtudiantDonneesTest etudiantDonneesTest;
    public ModifModuleModifNom(VuePrincipale main){
        //try (Connection con = testConnect()) {
            this.main = main;
           //this.etudiantDonneesTest=etudiantDonneesTest;
            this.nom = new TextField("Intitulé");
            this.nomModifie = new TextField("Nouvel intitulé");

            this.enregistrer= new Button("Enregistrer");


            enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            this.enregistrer.addClickListener(event -> { 
                 try{
                 this.main.getConBdD().setAutoCommit(false); 
                 fr.insa.zins.classe.Module.ModifNomModule(this.main.getConBdD(), this.nom.getValue(), this.nomModifie.getValue());
                 Module newModule =new Module (trouveModule(this.main.getConBdD(),this.nom.getValue()),trouveIdGMModule(this.main.getConBdD(), this.nom.getValue()),this.nomModifie.getValue(),trouveDescriptionModule(this.main.getConBdD(), this.nom.getValue()));
                 getListeModule().set(trouveModule(this.main.getConBdD(),this.nom.getValue())-1,newModule);
                 Notification.show("Nouveau nom enregistré");
                } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifModuleModifNom.class.getName()).log(Level.SEVERE, null, ex);
            } 
             });
            add(nom,nomModifie,enregistrer);
            //Etudiant.ModifEmailEtudiant(con, nom, emailModifie);
           // email.addValueChangeListener(event -> Etudiant.ModifEmailEtudiant(con, nom.getValue(), email.getValue()) );
             /*} catch (SQLException ex) {
            throw new Error(ex);
                }*/
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
