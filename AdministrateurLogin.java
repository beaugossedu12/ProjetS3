/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import fr.insa.zins.classe.Administrateur;
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
    
    public AdministrateurLogin(VuePrincipale main) {
   
        this.main = main;
        
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
            try{
            this.doLogin2();
            this.main.changeContenu(new Administrateur2(this.main));
            this.main.avancerBarre(this.main);
            }catch(ClassNotFoundException ex){
                
            }
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
                this.main.getSessionInfoA().setCurUser(user);
                 this.main.changeContenu(new Etudiant3Choix1(this.main));    
            }
        } catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }        
    }
}
