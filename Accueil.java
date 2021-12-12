/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;

/**
 *
 * @author sabin
 */
public class Accueil extends VerticalLayout {

    private VuePrincipale main;

    private Label accueil;
    private Button etudiant;
    private Button administrateur;
    private ProgressBar barre;
    private Icon espace;

    public Accueil(VuePrincipale main) {
        this.main = main;

 
        this.etudiant = new Button("Vous êtes un ETUDIANT");
        etudiant.setWidthFull();
        this.add(this.etudiant);

        this.etudiant.addClickListener((e) -> {
            this.main.changeContenu(new Etudiant1(this.main));
            this.main.avancerBarre(this.main);
        });
  
        this.administrateur = new Button("Vous êtes un ADMINISTRATEUR");
        this.add(this.administrateur);
        administrateur.setWidthFull();

        this.administrateur.addClickListener((e) -> {
            this.main.changeContenu(new Administrateur1(this.main));
            this.main.avancerBarre(this.main);
    
        });

    }
}
