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
public class Etudiant4Choix2  extends Div {

    private VuePrincipale main;
    private H3 titre;

    //private final Button retour;
    private Button valider;
    
    
    public Etudiant4Choix2(VuePrincipale main) {
        this.main = main;
         try{
        ChoixGM2Form form = new ChoixGM2Form(this.main);
        GridModuleForm2 grid= new GridModuleForm2();
         SplitLayout splitLayout = new SplitLayout(grid, form);
        splitLayout.setSizeFull();
        add(splitLayout);
        }catch(Exception e){
            
        }
    }

}