/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifEtudiantModif extends VerticalLayout{
    private H3 titre;
    private Button email;
    private Button mdp;
    private VuePrincipale main;
     private final Button retour;
     
     public ModifEtudiantModif (VuePrincipale main) {
   
        this.main = main;
        
        this.titre= new H3("Que voulez vous modifier ?");
        this.email= new Button("Email");
        this.mdp= new Button("Mot de passe");
        this.add(this.titre);
        this.add(this.email);
        email.setWidthFull();

        this.email.addClickListener((e) -> {
            this.main.changeContenu(new ModifEtudiantModifEmail(this.main));
            this.main.avancerBarre(this.main); 
            });
        this.add(this.mdp);
        mdp.setWidthFull();

        this.mdp.addClickListener((e) -> {
            this.main.changeContenu(new ModifEtudiantModifMdp(this.main));
            this.main.avancerBarre(this.main); 
            });
        this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifEtudiant(this.main));
            } catch (ClassNotFoundException |SQLException ex) {
                Logger.getLogger(ModifEtudiantAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
                
    }   
}
