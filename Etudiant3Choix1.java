/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBox.ItemFilter;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;

/**
 *
 * @author arthurquarteroni
 */
public class Etudiant3Choix1 extends VerticalLayout {
        
    private VuePrincipale main;
    private ComboBox Choix1;
    private ComboBox Choix2;
    private ComboBox Choix3;
    private TextField titre;
    private Button valider;

    public Etudiant3Choix1(VuePrincipale main) {
        
        this.main = main;
        

        this.titre = new TextField();
        this.titre.setValue("Groupe de Modules 1");
        this.titre.setReadOnly(true);
        this.titre.setSizeFull();
        this.titre.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        this.add(this.titre);

        this.Choix1 = new ComboBox();
        this.add(this.Choix1);
        this.Choix1.setLabel("Choix 1");
        this.Choix1.setWidth("20%");
        this.Choix1.getStyle().set("--vaadin-combo-box-overlay-width", "350px");
        this.Choix1.setPlaceholder("Choisissez un module");
        //Collection Modules = collection des modules dans la bdd;
        //this.Choix1.setItems(Modules);

        this.Choix2 = new ComboBox();
        this.add(this.Choix2);
        this.Choix2.setLabel("Choix 2");
        this.Choix2.setWidth("20%");
        this.Choix2.getStyle().set("--vaadin-combo-box-overlay-width", "350px");
        this.Choix2.setPlaceholder("Choisissez un module");
        //Collection Modules = collection des modules dans la bdd;
        //this.Choix1.setItems(Modules);

        this.Choix3 = new ComboBox();
        this.add(this.Choix3);
        this.Choix3.setLabel("Choix 3");
        this.Choix3.setWidth("20%");
        this.Choix3.getStyle().set("--vaadin-combo-box-overlay-width", "350px");
        this.Choix3.setPlaceholder("Choisissez un module");
        //Collection Modules = collection des modules dans la bdd;
        //this.Choix1.setItems(Modules);

        this.valider = new Button("Valider");
        this.add(this.valider);
        valider.setWidthFull();

        this.valider.addClickListener((e) -> {

            this.main.changeContenu(new Etudiant4Choix2(this.main));
            this.main.avancerBarre(this.main);
            
        } );
        }
    }
