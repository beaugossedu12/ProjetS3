/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;
import static fr.insa.zins.classe.Etudiant.afficheTousEtudiants;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class ModifEtudiant extends VerticalLayout {
    private VuePrincipale main;
    private Button ajouter;
    private Button supprimer;
    private Button modifier;
    private Button afficher;
    private Grid tableau;
    private Button retour;
     
    public ModifEtudiant(VuePrincipale main)throws SQLException, ClassNotFoundException {
        this.main = main;
 
        
            this.tableau = new Grid();
            ValueProvider renderer = null;
            tableau.addColumn(renderer);


            this.ajouter = new Button("Ajouter un étudiant");
            this.add(this.ajouter);
            ajouter.setWidthFull();

            this.ajouter.addClickListener((e) -> {
                try{
                this.main.changeContenu(new ModifEtudiantAjout(this.main));
                this.main.avancerBarre(this.main); 
                //méthode à appliquer au clic du bouton 
                }catch(ClassNotFoundException ex){
                    throw new Error(ex);
                }
            });
            this.supprimer = new Button("Supprimer un étudiant");
            this.add(this.supprimer);
            supprimer.setWidthFull();

            this.supprimer.addClickListener((e) -> {
                this.main.changeContenu(new ModifEtudiantSupp(this.main));
                this.main.avancerBarre(this.main); 
                //méthode à appliquer au clic du bouton 
            });
            this.modifier = new Button("Modifier des informations");
            this.add(this.modifier);
            modifier.setWidthFull();

            this.modifier.addClickListener((e) -> {
                this.main.changeContenu(new ModifEtudiantModif(this.main));
                this.main.avancerBarre(this.main); 
                //méthode à appliquer au clic du bouton 
            });
            this.afficher = new Button("Afficher les étudiants");
            this.add(this.afficher);
            afficher.setWidthFull();

            this.afficher.addClickListener((e) -> {
                try (Connection con = testConnect()) {
                 afficheTousEtudiants(con);
                //méthode à appliquer au clic du bouton 
                 } catch (ClassNotFoundException| SQLException ex) {
                    throw new Error(ex);
                }
            });
            
            this.retour = new Button("Retour");
            this.add(this.retour);
            this.retour.addClickListener((e) -> {
               try (Connection con = testConnect()) {
               this.main.changeContenu(new Administrateur2(this.main));
               this.main.reculerBarre(main);
                } catch (ClassNotFoundException| SQLException ex) {
                    throw new Error(ex);
                }
            });

    }

}
