/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
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
public class ModifEtudiantModifEmail extends FormLayout{
      private VuePrincipale main;

    private TextField email;
    private TextField nom;
    private Button enregistrer;
    private final Button retour;
     
    //private EtudiantDonneesTest etudiantDonneesTest;
    public ModifEtudiantModifEmail(VuePrincipale main){
        //try (Connection con = testConnect()) {
            this.main = main;
            //this.etudiantDonneesTest= etudiantDonneesTest;
            this.nom = new TextField("Nom");
            this.email = new TextField("Nouvelle adresse email");

            this.enregistrer= new Button("Enregistrer");


            email.setSuffixComponent(new Div(new Text("@insa-strasbourg.fr")));
            email.setPrefixComponent(VaadinIcon.ENVELOPE.create());

            enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
             this.enregistrer.addClickListener(event -> { 
                 try{
                 Etudiant.ModifEmailEtudiant(this.main.getConBdD(), this.nom.getValue(), this.email.getValue());
                 Notification.show("Nouvelle adresse mail enregistrée");
                } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifEtudiantModifEmail.class.getName()).log(Level.SEVERE, null, ex);
            } 
             });
            add(nom,email,enregistrer);
            //Etudiant.ModifEmailEtudiant(con, nom, emailModifie);
           // email.addValueChangeListener(event -> Etudiant.ModifEmailEtudiant(con, nom.getValue(), email.getValue()) );
             /*} catch (SQLException ex) {
            throw new Error(ex);
                }*/
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
