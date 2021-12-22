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
import com.vaadin.flow.component.textfield.TextField;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final Button retour;

    
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