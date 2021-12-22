/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import static fr.insa.zins.classe.bdd2.testConnect;
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


    public Administrateur2(VuePrincipale main) throws ClassNotFoundException{
        this.main = main;
        
            this.etudiant = new Button("Etudiant");
            this.add(this.etudiant);
            etudiant.setWidthFull();

            this.etudiant.addClickListener((e) -> {
                try ( Connection con = testConnect()) {
                    this.main.changeContenu(new ModifEtudiant(this.main));
                this.main.avancerBarre(this.main);  
                } catch (ClassNotFoundException|SQLException ex) {
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
                } catch (ClassNotFoundException|SQLException ex) {
                    throw new Error(ex);
                }
            });
            this.groupeModule = new Button("Groupe Module");
            this.add(this.groupeModule);
            groupeModule.setWidthFull();

            this.groupeModule.addClickListener((e) -> {
                try{
                this.main.changeContenu(new ModifGroupeModule(this.main));
                this.main.avancerBarre(this.main);
                }catch(ClassNotFoundException ex){
                    throw new Error(ex);
                }
            });

    }
    private Component newModifGroupeModule(VuePrincipale main) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
