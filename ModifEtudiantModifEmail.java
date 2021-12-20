/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.quarteroni.BDD.Etudiant;
import static fr.insa.quarteroni.BDD.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sabin
 */
public class ModifEtudiantModifEmail extends FormLayout {

    private VuePrincipale main;

    private TextField email;
    private TextField nom;
    private Button enregistrer;
    private final Button retour;

    public ModifEtudiantModifEmail(VuePrincipale main) {
        //try (Connection con = testConnect()) {
        this.main = main;

        this.nom = new TextField("Nom");
        this.email = new TextField("Nouvelle adresse email");

        this.enregistrer = new Button("Enregistrer");

        email.setSuffixComponent(new Div(new Text("@insa-strasbourg.fr")));
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());

        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom, email, enregistrer);
        //Etudiant.ModifEmailEtudiant(con, nom, emailModifie);
        // email.addValueChangeListener(event -> Etudiant.ModifEmailEtudiant(con, nom.getValue(), email.getValue()) );
        /*} catch (SQLException ex) {
            throw new Error(ex);
                }*/

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
