/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 *
 * @author sabin
 */
public class GridModuleForm2 extends VerticalLayout {

    public GridModuleForm2() {
         Grid<fr.insa.zins.classe.Module> grid = new Grid<>();
                grid.addColumn(fr.insa.zins.classe.Module::getNom).setHeader("Intitulé");
                grid.addColumn(fr.insa.zins.classe.Module::getDescription).setHeader("Description");
                grid.addColumn(fr.insa.zins.classe.Module::getIdGM).setHeader("Identifiant du groupe de modules");
                
               
                //List<Module> people = DataService.getPeople();
                grid.setItems(fr.insa.zins.classe.Module.getListeModule());
                grid.getColumns().forEach(col -> col.setAutoWidth(true));
                add(grid); 
    }
    
}
