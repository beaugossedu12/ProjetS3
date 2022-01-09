/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifModule;

import fr.insa.zins.testvaadin.ModifModule.ModifModuleAjout;
import fr.insa.zins.testvaadin.ModifModule.ModifModule;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.GroupeModule;
import fr.insa.zins.classe.Module;
import static fr.insa.zins.classe.Module.trouveModule;
import fr.insa.zins.testvaadin.VuePrincipale;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifModuleModifDescription extends FormLayout{
      private VuePrincipale main;


    private TextField nom;
    private TextField nomModifie;
    private Button enregistrer;
    private final Button retour;
    
    //private EtudiantDonneesTest etudiantDonneesTest;
    public ModifModuleModifDescription(VuePrincipale main){
        //try (Connection con = testConnect()) {
            this.main = main;

            this.nom = new TextField("Intitulé du module");
            this.nomModifie = new TextField("Nouvelle description");

            this.enregistrer= new Button("Enregistrer");


            enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            this.enregistrer.addClickListener(event -> { 
                 try{
                     this.main.getConBdD().setAutoCommit(false); 
                 Module.ModifDescriptionModule(this.main.getConBdD(), this.nom.getValue(), this.nomModifie.getValue());
                 Module newModule =new Module (trouveModule(this.main.getConBdD(),this.nom.getValue()),Module.trouveIdGMModule(this.main.getConBdD(), this.nom.getValue()),this.nom.getValue(),this.nomModifie.getValue());
                 Module.getListeModule().set(trouveModule(this.main.getConBdD(),this.nom.getValue())-1,newModule);
                 
                 Notification.show("Nouvelle description enregistrée");
                } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifModuleModifDescription.class.getName()).log(Level.SEVERE, null, ex);
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
