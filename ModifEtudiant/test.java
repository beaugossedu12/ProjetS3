/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifEtudiant;

import fr.insa.zins.testvaadin.Etudiant.EtudiantForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextArea;
import fr.insa.zins.classe.Etudiant;
import static fr.insa.zins.classe.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import fr.insa.zins.testvaadin.VuePrincipale;
import static fr.insa.zins.classe.Etudiant.getListeEtudiant;

/**
 *
 * @author sabin
 */


public class test extends VerticalLayout {
     private TextArea textArea;
      private VuePrincipale main;
      private String nom;
     private Grid<Etudiant> grid = new Grid<>();
     private final EtudiantForm form;
    /*private TextField nom = new TextField("Nom");
    private TextField prenom = new TextField("Prénom");
        private TextField email = new TextField("Email");
    private TextField mdp = new TextField("mot de passe");
    private final EtudiantDonneesTest etudiantDonneesTest;*/
    
    private Binder<Etudiant> binder = new Binder<>(Etudiant.class);
    private Etudiant etudiant;
     // private Button create = new Button("New", VaadinIcon.PLUS.create());
    // private Button save = new Button("Save", VaadinIcon.CHECK.create()); 
   TextField filterText = new TextField();
   // private VerticalLayout form = new VerticalLayout(nom, prenom, save);
      public test(VuePrincipale main) throws ClassNotFoundException{
   
       this.main = main;
       
      // this.etudiantDonneesTest = etudiantDonneesTest;
                   try  ( Connection con = testConnect()){
                    //textArea = new TextArea();
                    form = new EtudiantForm(main);
                   //form.addListener(EtudiantForm.SaveEvent.class, this::saveEtudiant);
                    //form.addListener(EtudiantForm.DeleteEvent.class, this::deleteEtudiant);
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
               
                //List<> people = DataService.getPeople();
                grid.setItems(Etudiant.getListeEtudiant());
                grid.getColumns().forEach(col -> col.setAutoWidth(true));
                //grid.addSelectionListener(event -> setEtudiant(grid.asSingleSelect().getValue()));
                 //updateGrid();

              // save.addClickListener(event -> saveClicked());
               //create.addClickListener(event -> createClicked());

                // getContent().add(grid, create, form);
                //binder.bindInstanceFields(this);
               // binder.setBean(null);
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
    
    
    /*private void createClicked() {
        grid.asSingleSelect().clear();
        setEtudiant(new Etudiant());
    }

    private void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        form.setEnabled(etudiant!= null);
        binder.setBean(etudiant);
    }*/

    private void saveClicked() {
        binder.readBean(etudiant);

            //etudiantDonneesTest.update(etudiant);
        
       // updateGrid();
        Notification.show("Saved!");
    }

   /* private void updateGrid() {
        grid.setItems(etudiantDonneesTest.findAll());
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
        //removeClassName("editor");
    }
   /* private void saveEtudiant(EtudiantForm.SaveEvent event) {
        getListeEtudiant().saveEtudiant(event.getEtudiantF());
        //updateList();
        closeEditor();
    }*/
/*
    private void deleteEtudiant(EtudiantForm.DeleteEvent event) {
        deleteEtudiant(event.getEtudiant());
        updateList();
        closeEditor();
    }
      private void updateList() {
        grid.setItems(Etudiant.tousLesEtudiants(con)(filterText.getValue()));
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
