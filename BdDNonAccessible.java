/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 *
 * @author sabin
 */
public class BdDNonAccessible extends VerticalLayout {
    
    private VuePrincipale main;
    
    public BdDNonAccessible(VuePrincipale main) {
        this.main = main;
        this.add(new H1("Base de donnée non accessible"));

    }
}
