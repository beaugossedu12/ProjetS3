/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import static fr.insa.quarteroni.BDD.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class Administrateur2 extends VerticalLayout {

    private VuePrincipale main;
    private Button etudiant;
    private Button module;
    private Button groupeModule;
    private final Button fichiertexte;


    public Administrateur2(VuePrincipale main) {
        this.main = main;
        
            this.etudiant = new Button("Etudiant");
            this.add(this.etudiant);
            etudiant.setWidthFull();

            this.etudiant.addClickListener((e) -> {
                try ( Connection con = testConnect()) {
                    this.main.changeContenu(new ModifEtudiant(this.main));
                this.main.avancerBarre(this.main);  
                } catch (SQLException ex) {
                    throw new Error(ex);
                }
            });
            this.module = new Button("Module");
            this.add(this.module);
            module.setWidthFull();

            this.module.addClickListener((e) -> {
                try ( Connection con1 = testConnect()) {
                    this.main.changeContenu(new ModifModule(this.main));
                    this.main.avancerBarre(this.main);
                } catch (SQLException ex) {
                    throw new Error(ex);
                }
            });
            this.groupeModule = new Button("Groupe Module");
            this.add(this.groupeModule);
            groupeModule.setWidthFull();

            this.groupeModule.addClickListener((e) -> {

                this.main.changeContenu(newModifGroupeModule(this.main));
                this.main.avancerBarre(this.main);
            });
            
            this.fichiertexte = new Button("Afficher le fichier texte");
            this.add(this.fichiertexte);
            fichiertexte.setWidthFull();
            fichiertexte.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
            
    }

    private Component newModifGroupeModule(VuePrincipale main) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}