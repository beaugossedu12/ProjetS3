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
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author sabin
 */
public class ModifEtudiantAjout extends FormLayout {
      private VuePrincipale main;
    
    private TextField nom;
    private TextField prenom;
    private TextField email;
    private TextField mdp;
    private Button enregistrer;

    
    public ModifEtudiantAjout(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Nom");
        this.prenom = new TextField("Prénom");
        this.email = new TextField("Adresse Email");
        this.mdp = new TextField("Mot de passe");
        this.enregistrer= new Button("Enregistrer");
        
        nom.setPrefixComponent(VaadinIcon.USER.create());
        nom.setClearButtonVisible(true);

        prenom.setClearButtonVisible(true);

        email.setSuffixComponent(new Div(new Text("@insa-strasbourg.fr")));
        email.setPrefixComponent(VaadinIcon.ENVELOPE.create());

        mdp.setClearButtonVisible(true);

        
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom,prenom,email,mdp,enregistrer);
    }  
}
