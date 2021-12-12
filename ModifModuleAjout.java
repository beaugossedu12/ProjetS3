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
public class ModifModuleAjout extends FormLayout{
   private VuePrincipale main;
    
    private TextField nom;
    private TextField description;
    private TextField idGM;
 
    private Button enregistrer;

    
    public ModifModuleAjout(VuePrincipale main) {
   
        this.main = main;
        
        this.nom = new TextField("Intitulé");
        this.description = new TextField("Description");
        this.idGM = new TextField("Numéro du groupe de module associé");
        this.enregistrer= new Button("Enregistrer");
        
       
        nom.setClearButtonVisible(true);

        description.setClearButtonVisible(true);


        idGM.setClearButtonVisible(true);

        
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(nom,description,idGM,enregistrer);
    }  
}
