/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.Etudiant;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import fr.insa.zins.testvaadin.GridModuleForm2;
import fr.insa.zins.testvaadin.VuePrincipale;

/**
 *
 * @author sabin
 */
public class Etudiant5Choix3  extends Div {

    private VuePrincipale main;
    private H3 titre;

   // private final Button retour;
private Button valider;

    public Etudiant5Choix3(VuePrincipale main) {
        this.main = main;
                try{
        ChoixGM3Form form = new ChoixGM3Form(this.main);
        GridModuleForm2 grid= new GridModuleForm2();
         SplitLayout splitLayout = new SplitLayout(grid, form);
        splitLayout.setSizeFull();
        add(splitLayout);
        }catch(Exception e){
            
        }

        /*this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            this.main.changeContenu(new Etudiant4Choix2(this.main));
            this.main.reculerBarre(main);
        });*/
      
    }

}
