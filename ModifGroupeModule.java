package fr.insa.quarteroni.Interface;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;
import fr.insa.quarteroni.Interface.ModifGroupeModuleAjout;
import fr.insa.quarteroni.Interface.ModifGroupeModuleModif;
import fr.insa.quarteroni.Interface.ModifGroupeModuleSupp;
import fr.insa.quarteroni.Interface.VuePrincipale;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class ModifGroupeModule extends VerticalLayout {

    private VuePrincipale main;
    private Button ajouter;
    private Button supprimer;
    private Button modifier;
    private Button afficher;
    private Grid tableau;

    public ModifGroupeModule(VuePrincipale main) {
        this.main = main;

        this.tableau = new Grid();
        ValueProvider renderer = null;
        tableau.addColumn(renderer);

        this.ajouter = new Button("Ajouter un groupe de modules");
        this.add(this.ajouter);
        ajouter.setWidthFull();

        this.ajouter.addClickListener((e) -> {
            this.main.changeContenu(new ModifGroupeModuleAjout(this.main));
            this.main.avancerBarre(this.main);
            //méthode à appliquer au clic du bouton 
        });
        this.supprimer = new Button("Supprimer un groupe de modules");
        this.add(this.supprimer);
        supprimer.setWidthFull();

        this.supprimer.addClickListener((e) -> {
            this.main.changeContenu(new ModifGroupeModuleSupp(this.main));
            this.main.avancerBarre(this.main);
            //méthode à appliquer au clic du bouton 
        });
        this.modifier = new Button("Modifier des informations");
        this.add(this.modifier);
        modifier.setWidthFull();

        this.modifier.addClickListener((e) -> {
            this.main.changeContenu(new ModifGroupeModuleModif(this.main));
            this.main.avancerBarre(this.main);
            //méthode à appliquer au clic du bouton 
        });
        this.afficher = new Button("Afficher les groupes modules");
        this.add(this.afficher);
        afficher.setWidthFull();

        
        

    }
}
        