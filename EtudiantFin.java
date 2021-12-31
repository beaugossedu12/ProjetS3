package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import static com.vaadin.flow.component.icon.VaadinIcon.CHECK_CIRCLE;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 *
 * @author arthurquarteroni
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

