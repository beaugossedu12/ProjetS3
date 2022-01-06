/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import fr.insa.zins.classe.GroupeModule;
import static fr.insa.zins.classe.ModuleDonnees.ensureTestDataGM;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class test3 extends VerticalLayout {
     private TextArea textArea;
      private VuePrincipale main;
      private String nom;
     private Grid<GroupeModule> grid = new Grid<>();
    private final GroupeModuleForm form;
    
   TextField filterText = new TextField();
   // private VerticalLayout form = new VerticalLayout(nom, prenom, save);
      public test3(VuePrincipale main) throws ClassNotFoundException{
   
       this.main = main;
       
      // this.etudiantDonneesTest = etudiantDonneesTest;
                   try  ( Connection con = testConnect()){
                    //textArea = new TextArea();
                   form = new GroupeModuleForm();
                   //form.addListener(EtudiantForm.SaveEvent.class, this::saveClicked);
                    //form.addListener(EtudiantForm.DeleteEvent.class, this::deleteEtudiant);
                    //form.addListener(EtudiantForm.CloseEvent.class, e -> closeEditor());
                   /*for (Integer elem :bdd2.getAllIds(con)){
                        //this.textArea.setValue("coucou");
                        textArea.setValue(Integer.toString(elem));
                        this.add(this.textArea);
                   }*/
                FlexLayout content = new FlexLayout(grid, form);
                content.setFlexGrow(2, grid);
                content.setFlexGrow(1, form);
                content.setFlexShrink(0, form);
                content.addClassNames("content", "gap-m");
                content.setSizeFull();
                  
                Grid<GroupeModule> grid = new Grid<>();
                grid.addColumn(GroupeModule::getNom).setHeader("Intitulé");
                grid.addColumn(GroupeModule::getDescription).setHeader("Description");
             
                
               
                //List<Module> people = DataService.getPeople();
                grid.setItems(ensureTestDataGM(con));
                grid.getColumns().forEach(col -> col.setAutoWidth(true));
                //grid.addSelectionListener(event -> setEtudiant(grid.asSingleSelect().getValue()));
                 //updateGrid();

              // save.addClickListener(event -> saveClicked());
               //create.addClickListener(event -> createClicked());

                // getContent().add(grid, create, form);
                //binder.bindInstanceFields(this);
               // binder.setBean(null);
                grid.asSingleSelect().addValueChangeListener(evt -> editGroupeModule(evt.getValue()));
                add(grid,content); 
               } catch (SQLException ex) {
                  throw new Error(ex);
               }
    }

   private void editGroupeModule(GroupeModule groupeModule){
        if (groupeModule == null){
            closeEditor();
        }else{
            form.setGroupeModule(groupeModule);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    
   private void closeEditor(){
        form.setGroupeModule(null);
        form.setVisible(false);
        //removeClassName("editor");
    }
}
