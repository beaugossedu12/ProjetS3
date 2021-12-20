/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;
import static fr.insa.quarteroni.BDD.Etudiant.afficheTousEtudiants;
import static fr.insa.quarteroni.BDD.bdd2.testConnect;
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

    public ModifEtudiant(VuePrincipale main)throws SQLException {
        this.main = main;
 
        
            this.tableau = new Grid();
            ValueProvider renderer = null;
            tableau.addColumn(renderer);


            this.ajouter = new Button("Ajouter un étudiant");
            this.add(this.ajouter);
            ajouter.setWidthFull();

            this.ajouter.addClickListener((e) -> {
                this.main.changeContenu(new ModifEtudiantAjout(this.main));
                this.main.avancerBarre(this.main); 
                //méthode à appliquer au clic du bouton 
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
            
            this.retour = new Button("Retour");
            this.add(this.retour);
            this.retour.addClickListener((e) -> {
               this.main.changeContenu(new Administrateur2(this.main));
               this.main.reculerBarre(main);
            });

            //this.afficher.addClickListener((e) -> {
               // try (Connection con = testConnect()) {
               //  afficheTousEtudiants(con);
                //méthode à appliquer au clic du bouton 
              //   } catch (SQLException ex) {
            //throw new Error(ex);
                }
          //  });

    }

