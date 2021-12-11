/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;

/**
 *
 * @author arthurquarteroni
 */
public class Administrateur2 extends VerticalLayout {

    private VuePrincipale main;
    private Button ajouter;
    private Button supprimer;
    private Button modifier;
    private Grid tableau;

    public Administrateur2(VuePrincipale main) {

        
        this.tableau = new Grid();
        ValueProvider renderer = null;
        tableau.addColumn(renderer);
        
                
        this.ajouter = new Button("Ajouter un étudiant");
        this.add(this.ajouter);
        ajouter.setWidthFull();

        this.ajouter.addClickListener((e) -> {

            //méthode à appliquer au clic du bouton 
        });
        this.supprimer = new Button("Supprimer un étudiant");
        this.add(this.supprimer);
        supprimer.setWidthFull();

        this.supprimer.addClickListener((e) -> {

            //méthode à appliquer au clic du bouton 
        });
        this.modifier = new Button("Modifier des informations");
        this.add(this.modifier);
        modifier.setWidthFull();

        this.modifier.addClickListener((e) -> {

            //méthode à appliquer au clic du bouton 
        });

    }

}
