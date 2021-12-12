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

/**
 *
 * @author sabin
 */
public class ModifEtudiantModifMdp extends FormLayout{
    private VuePrincipale main;

    private TextField mdp;
    private TextField nom;
    private Button enregistrer;

    
    public ModifEtudiantModifMdp(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Nom");
        this.mdp = new TextField("Nouveau mot de passe");
 
        this.enregistrer= new Button("Enregistrer");


        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom,mdp,enregistrer);
    }  
}


