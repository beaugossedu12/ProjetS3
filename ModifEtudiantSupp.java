/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifEtudiantSupp extends FormLayout {

    private VuePrincipale main;

    private TextField nom;

    private Button enregistrer;
    private final Button retour;

    public ModifEtudiantSupp(VuePrincipale main) {

        this.main = main;

        this.nom = new TextField("Nom");
        this.enregistrer = new Button("Enregistrer");

        nom.setPrefixComponent(VaadinIcon.USER.create());
        nom.setClearButtonVisible(true);

        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom, enregistrer);

        this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            try {
                this.main.changeContenu(new ModifEtudiant(this.main));
            } catch (SQLException ex) {
                Logger.getLogger(ModifEtudiantAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.main.reculerBarre(main);
        });
    }
}
