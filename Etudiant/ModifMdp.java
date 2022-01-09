/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Etudiant;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.Etudiant;
import static fr.insa.zins.classe.Etudiant.getListeEtudiant;
import static fr.insa.zins.classe.Etudiant.trouveEmailEtudiant;
import static fr.insa.zins.classe.Etudiant.trouveIdEtudiant;
import static fr.insa.zins.classe.Etudiant.trouvePrenomEtudiant;
import fr.insa.zins.testvaadin.ModifEtudiant.ModifEtudiant;
import fr.insa.zins.testvaadin.ModifEtudiant.ModifEtudiantAjout;
import fr.insa.zins.testvaadin.VuePrincipale;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifMdp extends FormLayout{
    private VuePrincipale main;

    private TextField mdp;
    private TextField nom;
    private Button enregistrer;
     private final Button retour;
     //private EtudiantDonneesTest etudiantDonneesTest;
    public ModifMdp(VuePrincipale main) {
   
        this.main = main;
       // this.etudiantDonneesTest=etudiantDonneesTest;
        this.nom = new TextField("Nom");
        this.mdp = new TextField("Nouveau mot de passe");
 
        this.enregistrer= new Button("Enregistrer");


        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.enregistrer.addClickListener(event -> { 
                 try{
                 Etudiant.ModifmdpEtudiant(this.main.getConBdD(), this.nom.getValue(), this.mdp.getValue());
                 Etudiant newEtudiant = new Etudiant(trouveIdEtudiant(this.main.getConBdD(),this.nom.getValue()),this.nom.getValue(),trouvePrenomEtudiant(this.main.getConBdD(), this.nom.getValue()),trouveEmailEtudiant(this.main.getConBdD(), this.nom.getValue()),this.mdp.getValue());
                 getListeEtudiant().set(trouveIdEtudiant(this.main.getConBdD(),this.nom.getValue())-1,newEtudiant);
                 Notification.show("Nouveau mot de passe enregistré");
                } catch (SQLException ex) {
                Notification.show("Problème BdD : " + ex.getLocalizedMessage());
                 Logger.getLogger(ModifMdp.class.getName()).log(Level.SEVERE, null, ex);
            } 
             });
        add(nom,mdp,enregistrer);
                this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new Etudiant2(this.main));
            } catch (Exception ex) {
                Logger.getLogger(ModifMdp.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
    }  
}
