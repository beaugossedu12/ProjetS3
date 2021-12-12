/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.Etudiant;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class ModifEtudiantModifEmail extends FormLayout{
      private VuePrincipale main;

    private TextField email;
    private TextField nom;
    private Button enregistrer;

    
    public ModifEtudiantModifEmail(VuePrincipale main){
        //try (Connection con = testConnect()) {
            this.main = main;

            this.nom = new TextField("Nom");
            this.email = new TextField("Nouvelle adresse email");

            this.enregistrer= new Button("Enregistrer");


            email.setSuffixComponent(new Div(new Text("@insa-strasbourg.fr")));
            email.setPrefixComponent(VaadinIcon.ENVELOPE.create());

            enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            add(nom,email,enregistrer);
            //Etudiant.ModifEmailEtudiant(con, nom, emailModifie);
           // email.addValueChangeListener(event -> Etudiant.ModifEmailEtudiant(con, nom.getValue(), email.getValue()) );
             /*} catch (SQLException ex) {
            throw new Error(ex);
                }*/
        }  
}
