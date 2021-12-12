/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author sabin
 */
public class ModifEtudiantSupp extends FormLayout {
          private VuePrincipale main;
    
    private TextField nom;

    private Button enregistrer;

    
    public ModifEtudiantSupp(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Nom");
        this.enregistrer= new Button("Enregistrer");
        
        nom.setPrefixComponent(VaadinIcon.USER.create());
        nom.setClearButtonVisible(true);


        
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom,enregistrer);
    }  
}
