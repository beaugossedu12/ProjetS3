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
import fr.insa.zins.classe.bdd2;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class Accueil extends VerticalLayout {
    private Connection conBdD;
    private VuePrincipale main;

    private Label accueil;
    private Button etudiant;
    private Button administrateur;
    private ProgressBar barre;
    private Icon espace;

    public Connection getConBdD() {
        return conBdD;
    }

    public void setConBdD(Connection conBdD) {
        this.conBdD = conBdD;
    }

    public Accueil(VuePrincipale main) {

        this.main = main;

 
        this.etudiant = new Button("Vous êtes un ETUDIANT");
        etudiant.setWidthFull();
        this.add(this.etudiant);

        this.etudiant.addClickListener((e) -> {
            this.main.changeContenu(new EtudiantLogin(this.main));
            this.main.avancerBarre(this.main);
        });
  
        this.administrateur = new Button("Vous êtes un ADMINISTRATEUR");
        this.add(this.administrateur);
        administrateur.setWidthFull();

        this.administrateur.addClickListener((e) -> {
            try {
            this.main.changeContenu(new AdministrateurLogin(this.main));
            this.main.avancerBarre(this.main);
            }catch(Exception ex){
                    throw new Error(ex);
                }
        });

    }
}
