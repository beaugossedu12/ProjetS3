/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Etudiant;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import fr.insa.zins.testvaadin.VuePrincipale;

/**
 *
 * @author sabin
 */
public class Etudiant2 extends VerticalLayout{
private Button modifmdp;    
private Button choix;
private VuePrincipale main;
    public Etudiant2(VuePrincipale main) {
     this.main = main;   
     this.modifmdp = new Button("Modifier le mot de passe");
     this.add(this.modifmdp);
     modifmdp.setWidthFull(); 
     this.modifmdp.addClickListener((e) -> {
            this.main.changeContenu(new Etudiant3Choix1(this.main));
            this.main.avancerBarre(this.main); 
            });
    this.choix = new Button("Faire vos voeux");
     this.add(this.choix);
     choix.setWidthFull();        
          this.choix.addClickListener((e) -> {
            this.main.changeContenu(new Etudiant3Choix1(this.main));
            this.main.avancerBarre(this.main); 
            });
    }
}
