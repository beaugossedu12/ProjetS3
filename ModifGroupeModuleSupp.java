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

/**
 *
 * @author sabin
 */
public class ModifGroupeModuleSupp extends FormLayout {
          private VuePrincipale main;
    
    private TextField nom;

    private Button enregistrer;

    
    public ModifGroupeModuleSupp(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Intitulé");
        this.enregistrer= new Button("Enregistrer");
 
        nom.setClearButtonVisible(true);


        
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom,enregistrer);
    }  
}
