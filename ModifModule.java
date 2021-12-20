/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.function.ValueProvider;
import static fr.insa.quarteroni.BDD.bdd2.testConnect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class ModifModule extends VerticalLayout{
   private VuePrincipale main;
    private Button ajouter;
    private Button supprimer;
    private Button modifier;
    private Button afficher;
    private Grid tableau;
    private TextArea textArea;
    private final Button retour;
    
    public ModifModule(VuePrincipale main) throws SQLException{
        this.main = main;
        try ( Connection con = testConnect()) {
        
            this.tableau = new Grid();
            ValueProvider renderer = null;
            tableau.addColumn(renderer);


            this.ajouter = new Button("Ajouter un module");
            this.add(this.ajouter);
            ajouter.setWidthFull();

            this.ajouter.addClickListener((e) -> {
                this.main.changeContenu(new ModifModuleAjout(this.main));
                this.main.avancerBarre(this.main); 
                //méthode à appliquer au clic du bouton 
            });
            this.supprimer = new Button("Supprimer un module");
            this.add(this.supprimer);
            supprimer.setWidthFull();

            this.supprimer.addClickListener((e) -> {
               this.main.changeContenu(new ModifModuleSupp(this.main));
                this.main.avancerBarre(this.main); 
                //méthode à appliquer au clic du bouton 
            });

            this.modifier = new Button("Modifier des informations");
            this.add(this.modifier);
            modifier.setWidthFull();

            this.modifier.addClickListener((e) -> {
               this.main.changeContenu(new ModifModuleModif(this.main));
                this.main.avancerBarre(this.main); 
                //méthode à appliquer au clic du bouton 
            });
            this.afficher = new Button("Afficher les modules");
            this.add(this.afficher);
            afficher.setWidthFull();
            
            this.retour = new Button("Retour");
            this.add(this.retour);
            this.retour.addClickListener((e) -> {
               this.main.changeContenu(new Administrateur2(this.main));
               this.main.reculerBarre(main);
            });

            
        }
    }
}
           // this.afficher.addClickListener((e) -> {
                /*Grid<Module> grid = new Grid<>(Module.class, false);
                grid.addColumn(Module::getId).setHeader("Identificateur");
                grid.addColumn(Module::getIdGM).setHeader("Groupe de modules associé");
                grid.addColumn(Module::getNom).setHeader("Intitulé");
                grid.addColumn(Module::getDescription).setHeader("Description");
                List<Module> people = DataService.getPeople();
                grid.setItems(people);
                add(grid)*/    
           //     try ( Connection con1 = testConnect()) {
            //        for (int i=1; i<=chargeModule(con1).size();i++){
               //         textArea.setValue(chargeModule(con).get(i).toString());
              //          add(textArea);
               //     }
             //   } catch (SQLException ex) {
              //      throw new Error(ex);
           //     }
                //méthode à appliquer au clic du bouton 
         //   });
      //  } catch (SQLException ex) {
      //      throw new Error(ex);
      //  }
  //  }
    
//}