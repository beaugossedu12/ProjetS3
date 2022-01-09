/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.GroupeModule;
import static fr.insa.zins.classe.ModuleDonnees.ensureTestDataV;
import fr.insa.zins.classe.Voeux;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class test4 extends VerticalLayout{
     private TextArea textArea;
      private VuePrincipale main;
      private String nom;
     private Grid<Voeux> grid = new Grid<>();
   
    
   TextField filterText = new TextField();
   // private VerticalLayout form = new VerticalLayout(nom, prenom, save);
      public test4(VuePrincipale main) throws ClassNotFoundException{
   
       this.main = main;
       
      // this.etudiantDonneesTest = etudiantDonneesTest;
                   try  ( Connection con = testConnect()){
                    //textArea = new TextArea();
                   
                Grid<Voeux> grid = new Grid<>();
                grid.addColumn(Voeux::getIdGM).setHeader("Identifiant du groupe de module associé");
                grid.addColumn(Voeux::getIdEtudiant).setHeader("Identifiant de l'étudiant");
                grid.addColumn(Voeux::getChoix1).setHeader("Choix 1");
                grid.addColumn(Voeux::getChoix2).setHeader("Choix 2");
                grid.addColumn(Voeux::getChoix3).setHeader("Choix 3");
               
                //List<Module> people = DataService.getPeople();
                grid.setItems(ensureTestDataV(con));
                grid.getColumns().forEach(col -> col.setAutoWidth(true));
                //grid.addSelectionListener(event -> setEtudiant(grid.asSingleSelect().getValue()));
                 //updateGrid();

              // save.addClickListener(event -> saveClicked());
               //create.addClickListener(event -> createClicked());

                // getContent().add(grid, create, form);
                //binder.bindInstanceFields(this);
               // binder.setBean(null);
                //grid.asSingleSelect().addValueChangeListener(evt -> editGroupeModule(evt.getValue()));
                add(grid); 
               } catch (SQLException ex) {
                  throw new Error(ex);
               }
    }

  /* private void editGroupeModule(GroupeModule groupeModule){
        if (groupeModule == null){
            closeEditor();
        }else{
            form.setGroupeModule(groupeModule);
            form.setVisible(true);
            addClassName("editing");
        }
    }*/
    
  /* private void closeEditor(){
        form.setGroupeModule(null);
        form.setVisible(false);
        //removeClassName("editor");
    }*/
}

