/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;

/**
 *
 * @author arthurquarteroni
 */
public class Etudiant1 extends VerticalLayout {

    private VuePrincipale main;
    
    private PasswordField mdp;
    private EmailField mail;
    private Button valider;
    private String login;
    private LoginForm LoginForm;
    
    public Etudiant1(VuePrincipale main) {
   
        this.main = main;
        
    

    this.mail = new EmailField("Mail");
    this.add(this.mail);
    mail.setClearButtonVisible(true);
    mail.setErrorMessage("Entrez une adresse mail valide");
    mail.setWidthFull();
    
    
         this.mdp = new PasswordField("Mot de passe");
    this.add(this.mdp);
    mdp.setWidthFull();
   

     this.valider = new Button("Valider");
        this.add(this.valider);
     valider.setWidthFull();

this.valider.addClickListener((e) -> {
   
    this.main.changeContenu(new Etudiant3Choix1(this.main));
    this.main.avancerBarre(this.main);
    

} );
 
    }     
    }

