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
import fr.insa.zins.classe.Etudiant;

import static fr.insa.zins.classe.EtudiantDonnees.ensureTestData;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class test extends VerticalLayout {
      private TextArea textArea;
      private VuePrincipale main;
      private String nom;
      Grid<Etudiant> grid = new Grid<>();
     private final EtudiantForm form;
      
       public test(VuePrincipale main) throws ClassNotFoundException{
       this.main = main;
       
                   try  ( Connection con = testConnect()){
                    textArea = new TextArea();
                    form = new EtudiantForm();
                   // form.addListener(EtudiantForm.SaveEvent.class, this::saveEtudiant);
                    //form.addListener(EtudiantForm.DeleteEvent.class, this::deleteContact);
                    form.addListener(EtudiantForm.CloseEvent.class, e -> closeEditor());
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
                  
                Grid<Etudiant> grid = new Grid<>();
                grid.addColumn(Etudiant::getNom).setHeader("Nom");
                grid.addColumn(Etudiant::getPrenom).setHeader("Prénom");
                grid.addColumn(Etudiant::getEmail).setHeader("Adresse email");
                grid.addColumn(Etudiant::getMdp).setHeader("Mot de passe");

                //List<Module> people = DataService.getPeople();
                grid.setItems(ensureTestData(con));
                grid.getColumns().forEach(col -> col.setAutoWidth(true));
                grid.asSingleSelect().addValueChangeListener(evt -> editEtudiant(evt.getValue()));
                add(grid,content); 
               } catch (SQLException ex) {
                  throw new Error(ex);
               }
    }
   /* private void configureGrid() {
        //grid.addClassNames("contact-grid");
        
        grid.setSizeFull();
        grid.setColumns("Nom", "Prénom", "email","mot de passe");;
  
    }*/
   private void editEtudiant(Etudiant etudiant){
        if (etudiant == null){
            closeEditor();
        }else{
            form.setEtudiant(etudiant);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    
    private void closeEditor(){
        form.setEtudiant(null);
        form.setVisible(false);
        removeClassName("editor");
    }
       /* private void saveEtudiant(EtudiantForm.SaveEvent event) {
        saveEtudiant(event.getEtudiant());
        updateList();
        closeEditor();
    }*/

    /*private void deleteEtudiant(EtudiantForm.DeleteEvent event) {
        service.deleteEtudiant(event.getEtudiant());
        updateList();
        closeEditor();
    }*/
   /* EtudiantForm form;
    Grid<Etudiant> grid = new Grid <>(Etudiant.class);
    TextField filterText = new TextField();
    
    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email","mot de passe");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }*/
}
