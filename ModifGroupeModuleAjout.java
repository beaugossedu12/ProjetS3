/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author sabin
 */
public class ModifGroupeModuleAjout  extends FormLayout{
   private VuePrincipale main;
    
    private TextField nom;
    private TextField description;

 
    private Button enregistrer;

    
    public ModifGroupeModuleAjout(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Intitulé");
        this.description = new TextField("Description");
 
        this.enregistrer= new Button("Enregistrer");
        
       
        nom.setClearButtonVisible(true);

        description.setClearButtonVisible(true);

        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom,description,enregistrer);
    }  
}
