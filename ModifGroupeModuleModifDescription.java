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
public class ModifGroupeModuleModifDescription extends FormLayout{
      private VuePrincipale main;


    private TextField nom;
    private TextField nomModifie;
    private Button enregistrer;

    
    public ModifGroupeModuleModifDescription(VuePrincipale main){
        //try (Connection con = testConnect()) {
            this.main = main;

            this.nom = new TextField("Intitulé du groupe de modules");
            this.nomModifie = new TextField("Nouvelle description");

            this.enregistrer= new Button("Enregistrer");


            enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            add(nom,nomModifie,enregistrer);
            //Etudiant.ModifEmailEtudiant(con, nom, emailModifie);
           // email.addValueChangeListener(event -> Etudiant.ModifEmailEtudiant(con, nom.getValue(), email.getValue()) );
             /*} catch (SQLException ex) {
            throw new Error(ex);
                }*/
        }  
}
