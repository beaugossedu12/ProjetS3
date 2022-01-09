/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Etudiant;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import static com.vaadin.flow.component.icon.VaadinIcon.CHECK_CIRCLE;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import fr.insa.zins.testvaadin.VuePrincipale;

/**
 *
 * @author sabin
 */
public class EtudiantFin extends HorizontalLayout {
     private VuePrincipale main;
    private H3 titre;
    private Icon check;

    
    public EtudiantFin(VuePrincipale main) {
   
        this.main = main;
        
        this.check = new Icon(CHECK_CIRCLE);
        check.setSize("50px");
        check.setColor("green");
        this.add(this.check);
        this.titre= new H3("Vos choix ont bien été pris en compte, vous recevrez vos affectations par mail prochainement.");
        titre.setSizeFull();
        this.add(this.titre);
 
    }
}
