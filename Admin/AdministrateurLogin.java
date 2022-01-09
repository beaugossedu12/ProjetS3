/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import fr.insa.zins.classe.Administrateur;
import fr.insa.zins.testvaadin.VuePrincipale;
import static fr.insa.zins.classe.Administrateur.loginA;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author sabin
 */
public class AdministrateurLogin extends VerticalLayout {

   
    private VuePrincipale main;
    
    private PasswordField vmdp;
    private EmailField vmail;
    private Button vbvalider;
    private String login;
    private LoginForm LoginForm;
    //private EtudiantDonneesTest etudiantDonneesTest;
    public AdministrateurLogin(VuePrincipale main) {
   
        this.main = main;
        //this.etudiantDonneesTest=etudiantDonneesTest;
        this.vmail = new EmailField("Mail");
        this.add(this.vmail);
        vmail.setClearButtonVisible(true);
        vmail.setErrorMessage("Entrez une adresse mail valide");
        vmail.setWidthFull();
        
    
        this.vmdp = new PasswordField("Mot de passe");
        this.add(this.vmdp);
        vmdp.setWidthFull();
   

        this.vbvalider = new Button("Valider");
        this.add(this.vbvalider);
        vbvalider.setWidthFull();

        this.vbvalider.addClickListener((e) -> {
            
            this.doLogin2();
            
            this.main.avancerBarre(this.main);

        } );
        
    }   
    
        public void doLogin2() {
        String mail = this.vmail.getValue();
        String mdp = this.vmdp.getValue();
        try {
            Connection con = this.main.getSessionInfoA().getConBdDA();
            Optional<Administrateur> user = loginA(con, mail, mdp);
            if(user.isEmpty()) {
                Notification.show("Utilisateur ou pass invalide");
            } else {
                try{
                this.main.getSessionInfoA().setCurUserA(user);
                 this.main.changeContenu(new Administrateur2(this.main)); 
                }catch(ClassNotFoundException ex){
                
                   }   
            }
        } catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }        
    }
}
