/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author sabin
 */
public class Login extends VerticalLayout{
    
    private TextField vtfNom;
    private Button vbValidate;
    
    
    
    public Login(){
        this.vtfNom = new TextField();
        this.vbValidate = new Button("save");
        this.add(this.vtfNom,this.vbValidate);
    }
}
