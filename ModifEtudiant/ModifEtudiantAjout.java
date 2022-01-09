/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifEtudiant;

import fr.insa.zins.testvaadin.ModifEtudiant.ModifEtudiant;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.Etudiant;
import fr.insa.zins.classe.EtudiantDonnees;

import fr.insa.zins.classe.bdd2;
import fr.insa.zins.testvaadin.VuePrincipale;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifEtudiantAjout extends FormLayout {
      private VuePrincipale main;
    
    private TextField vtnom;
    private TextField vtprenom;
    private TextField vtemail;
    private TextField vtmdp;
    private Button enregistrer;
    //Binder<Etudiant> binder = new BeanValidationBinder<>(Etudiant.class);
    private Button retour;
    //private List <Etudiant> etudiantListe;
    //private Connection conBdD;
    //private EtudiantDonneesTest etudiantDonneesTest;
    public ModifEtudiantAjout(VuePrincipale main) {
        //binder.bindInstanceFields(this);
        this.main = main;

        this.vtnom = new TextField("Nom");
        this.vtprenom = new TextField("Prénom");
        this.vtemail = new TextField("Adresse Email");
        this.vtmdp = new TextField("Mot de passe");
        this.enregistrer= new Button("Enregistrer");
            
        vtnom.setPrefixComponent(VaadinIcon.USER.create());
        vtnom.setClearButtonVisible(true);

        vtprenom.setClearButtonVisible(true);

        vtemail.setSuffixComponent(new Div(new Text("@insa-strasbourg.fr")));
        vtemail.setPrefixComponent(VaadinIcon.ENVELOPE.create());

        vtmdp.setClearButtonVisible(true);

        
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);  
       
        this.enregistrer.addClickListener(event -> { 
            //Connection con = this.main.getSessionInfo().getConBdDE();
             //Connection con= this.main.testConnect();
            //String nom = this.vtnom.getValue();
            //String prenom= this.vtprenom.getValue();
            //String email = this.vtemail.getValue();
           // String mdp = this.vtmdp.getValue();
         
            try{
         
                int id = bdd2.createEtudiant(this.main.getConBdD(), this.vtnom.getValue(),this.vtprenom.getValue(), this.vtemail.getValue(), this.vtmdp.getValue());
                //createEtudiant(this.main.getConBdD(), this.vtnom.getValue(), this.vtprenom.getValue(), this.vtemail.getValue(), this.vtmdp.getValue());
                Etudiant newEtudiant = new Etudiant (id, this.vtnom.getValue(), this.vtprenom.getValue(), this.vtemail.getValue(), this.vtmdp.getValue());
                //this.main.getSessionInfo().setCurUser(Optional.of(newEtudiant));
                Etudiant.getListeEtudiant().add(newEtudiant);
                Notification.show("Etudiant " + this.vtnom.getValue() + " créé");
                //méthode à appliquer au clic du bouton 
         
            } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifEtudiantAjout.class.getName()).log(Level.SEVERE, null, ex);
            }     
        });
         this.add(this.vtnom,this.vtprenom,this.vtemail,this.vtmdp,this.enregistrer);

        this.retour = new Button("Retour");
        
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifEtudiant(this.main));
            } catch (ClassNotFoundException |SQLException ex) {
                Logger.getLogger(ModifEtudiantAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
        this.add(this.retour);
    }  
}

            
               