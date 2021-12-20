/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.quarteroni.Interface;

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
public class ModifModuleModif extends VerticalLayout {

    private H3 titre;
    private Button intitule;
    private Button description;
    private VuePrincipale main;
    private final Button retour;

    public ModifModuleModif(VuePrincipale main) {

        this.main = main;

        this.titre = new H3("Que voulez vous modifier ?");
        this.intitule = new Button("Intitule");
        this.description = new Button("Description");
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
