/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifModule;

import fr.insa.zins.testvaadin.ModifModule.ModuleForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import static fr.insa.zins.classe.ModuleDonnees.ensureTestDataM;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;
import fr.insa.zins.classe.Module;
import fr.insa.zins.testvaadin.VuePrincipale;
/**
 *
 * @author sabin
 */
public class test2 extends VerticalLayout {
     private TextArea textArea;
      private VuePrincipale main;
      private String nom;
     private Grid<Module> grid = new Grid<>();
    private final ModuleForm form;
    
   TextField filterText = new TextField();
   // private VerticalLayout form = new VerticalLayout(nom, prenom, save);
      public test2(VuePrincipale main) throws ClassNotFoundException{
   
       this.main = main;
       
      // this.etudiantDonneesTest = etudiantDonneesTest;
                   try  ( Connection con = testConnect()){
                    //textArea = new TextArea();
                   form = new ModuleForm();
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
                  
                Grid<Module> grid = new Grid<>();
                grid.addColumn(Module::getNom).setHeader("Intitulé");
                grid.addColumn(Module::getDescription).setHeader("Description");
                grid.addColumn(Module::getIdGM).setHeader("Identifiant du groupe de modules");
                
               
                //List<Module> people = DataService.getPeople();
                grid.setItems(Module.getListeModule());
                grid.getColumns().forEach(col -> col.setAutoWidth(true));
                //grid.addSelectionListener(event -> setEtudiant(grid.asSingleSelect().getValue()));
                 //updateGrid();

              // save.addClickListener(event -> saveClicked());
               //create.addClickListener(event -> createClicked());

                // getContent().add(grid, create, form);
                //binder.bindInstanceFields(this);
               // binder.setBean(null);
                grid.asSingleSelect().addValueChangeListener(evt -> editModule(evt.getValue()));
                add(grid,content); 
               } catch (SQLException ex) {
                  throw new Error(ex);
               }
    }

   private void editModule(Module module){
        if (module == null){
            closeEditor();
        }else{
            form.setModule(module);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    
   private void closeEditor(){
        form.setModule(null);
        form.setVisible(false);
        //removeClassName("editor");
    }

}

