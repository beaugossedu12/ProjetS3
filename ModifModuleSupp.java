/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifModuleSupp extends FormLayout {

    private VuePrincipale main;

    private TextField nom;

    private Button enregistrer;
    private final Button retour;

    public ModifModuleSupp(VuePrincipale main) {

        this.main = main;

        this.nom = new TextField("Intitulé");
        this.enregistrer = new Button("Enregistrer");

        nom.setClearButtonVisible(true);

        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom, enregistrer);

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