/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author sabin
 */
public class ModifGroupeModuleAjout extends FormLayout{
   private VuePrincipale main;
    
    private TextField nom;
    private TextField description;
    private Button retour;
 
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
