/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifModule;

import fr.insa.zins.testvaadin.ModifModule.ModifModuleAjout;
import fr.insa.zins.testvaadin.ModifModule.ModifModule;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import fr.insa.zins.testvaadin.VuePrincipale;
//import fr.insa.zins.classe.EtudiantDonneesTest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifModuleModif extends VerticalLayout{
    private H3 titre;
    private Button intitule;
    private Button description;
    private VuePrincipale main;
    private final Button retour;
    
    //private EtudiantDonneesTest etudiantDonneesTest;
     public ModifModuleModif (VuePrincipale main) {
   
        this.main = main;
        //this.etudiantDonneesTest=etudiantDonneesTest;
        
        this.titre= new H3("Que voulez vous modifier ?");
        this.intitule= new Button("Intitule");
        this.description= new Button("Description");
        this.add(this.titre);
        this.add(this.intitule);
        intitule.setWidthFull();

        this.intitule.addClickListener((e) -> {
            this.main.changeContenu(new ModifModuleModifNom(this.main));
            this.main.avancerBarre(this.main); 
            });
        this.add(this.description);
        description.setWidthFull();

        this.description.addClickListener((e) -> {
            this.main.changeContenu(new ModifModuleModifDescription(this.main));
            this.main.avancerBarre(this.main); 
            });
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
