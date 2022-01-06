/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import fr.insa.zins.classe.Voeux;
import static fr.insa.zins.classe.Voeux.trouveVoeux;
import fr.insa.zins.classe.bdd2;
import java.sql.SQLException;

/**
 *
 * @author sabin
 */
public class Etudiant5Choix3  extends VerticalLayout {

    private VuePrincipale main;
    private H3 titre;
    /*private HorizontalLayout choix1;
    private TextField titrechoix1;
    private Button module1;
    private Button module2;
    private Button module3;
    
    private final TextField titrechoix2;
    private final HorizontalLayout choix2;
    private final Button module12;
    private final Button module22;
    private final Button module32;
    private final TextField titrechoix3;
    private final HorizontalLayout choix3;
    private final Button module13;
    private final Button module23;
    private final Button module33;*/
    private final Button retour;
private Button valider;
    private Voeux newVoeux;
   private IntegerField m1;
    private IntegerField m2;
    private IntegerField m3;
    public Etudiant5Choix3(VuePrincipale main) {
        this.main = main;
        int a=0;
        try{
        int id = bdd2.createVoeux(this.main.getConBdD(), 3,this.main.getSessionInfo().getUserID(), a, a, a);
        this.newVoeux = new Voeux(id,3,this.main.getSessionInfo().getUserID(),a,a,a);
        }catch(SQLException e){
            
        }
        this.titre = new H3("GROUPE DE MODULES 3");
        titre.setSizeFull();
        this.add(this.titre);
        
        this.m1 = new IntegerField("Choix 1");
        this.m1.setMin(1);
        this.m1.setMax(3);
        this.m2 = new IntegerField("Choix 2");
        this.m2.setMin(1);
        this.m2.setMax(3);        
        this.m3= new IntegerField("Choix 3");
        this.m3.setMin(1);
        this.m3.setMax(3);
        this.add(this.m1,this.m2,this.m3);
        
         this.valider = new Button("Valider");
        this.valider.addClickListener((e) -> {
            if (this.m1.getValue()==this.m2.getValue()||this.m1.getValue()==this.m3.getValue()||this.m2.getValue()==this.m3.getValue()){
                Notification.show("Erreur : vous avez selectionné deux fois le même module");
            }else{
               this.main.changeContenu(new EtudiantFin(this.main)); 
                this.main.avancerBarre(this.main);
                try{
                Voeux.ModifChoix1(this.main.getConBdD(), this.main.getSessionInfo().getUserID(), this.m1.getValue());
                Voeux.ModifChoix2(this.main.getConBdD(), this.main.getSessionInfo().getUserID(), this.m2.getValue());
                Voeux.ModifChoix3(this.main.getConBdD(), this.main.getSessionInfo().getUserID(), this.m3.getValue());
                /*int i=1;
                while (i!= trouveVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID())){
                    i++;
                }*/
                Voeux.getListeVoeux().set(trouveVoeux(this.main.getConBdD(),this.main.getSessionInfo().getUserID()),newVoeux);
                }catch(SQLException ex){
                    
                }  
            }
            this.main.reculerBarre(main);
        });
        this.add(this.valider);
        valider.setWidthFull();

        this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            this.main.changeContenu(new Etudiant4Choix2(this.main));
            this.main.reculerBarre(main);
        });
       /* this.main = main;

        this.titre = new H3("GROUPE DE MODULES 3");
        titre.setSizeFull();
        this.add(this.titre);

        this.titrechoix1 = new TextField();
        this.titrechoix1.setValue("Choix 1");
        this.titrechoix1.setReadOnly(true);
        this.titrechoix1.setSizeFull();
        this.titrechoix1.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        this.add(this.titrechoix1);

        this.choix1 = new HorizontalLayout();
        this.choix1.setWidthFull();
        this.choix1.setHeight("25%");
        this.choix1.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.add(this.choix1);
        this.choix1.setMargin(true);

        this.module1 = new Button("Module 1");
        this.choix1.add(this.module1);
        module1.setWidth("30%");
        module1.setDisableOnClick(true);
        this.module2 = new Button("Module 2");
        this.choix1.add(this.module2);
        module2.setWidth("30%");
        module2.setDisableOnClick(true);
        this.module3 = new Button("Module 3");
        this.choix1.add(this.module3);
        module3.setWidth("30%");
        module3.setDisableOnClick(true);

        this.titrechoix2 = new TextField();
        this.titrechoix2.setValue("Choix 2");
        this.titrechoix2.setReadOnly(true);
        this.titrechoix2.setSizeFull();
        this.titrechoix2.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        this.add(this.titrechoix2);

        this.choix2 = new HorizontalLayout();
        this.choix2.setWidthFull();
        this.choix2.setHeight("25%");
        this.choix2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.add(this.choix2);
        this.choix2.setMargin(true);

        this.module12 = new Button("Module 1");
        this.choix2.add(this.module12);
        module12.setWidth("30%");
        module12.setDisableOnClick(true);
        this.module22 = new Button("Module 2");
        this.choix2.add(this.module22);
        module22.setDisableOnClick(true);
        module22.setWidth("30%");
        this.module32 = new Button("Module 3");
        this.choix2.add(this.module32);
        module32.setWidth("30%");
        module32.setDisableOnClick(true);

        this.titrechoix3 = new TextField();
        this.titrechoix3.setValue("Choix 3");
        this.titrechoix3.setReadOnly(true);
        this.titrechoix3.setSizeFull();
        this.titrechoix3.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        this.add(this.titrechoix3);

        this.choix3 = new HorizontalLayout();
        this.choix3.setWidthFull();
        this.choix3.setHeight("25%");
        this.choix3.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        this.add(this.choix3);
        this.choix3.setMargin(true);

        this.module13 = new Button("Module 1");
        this.choix3.add(this.module13);
        module13.setWidth("30%");
        module13.setDisableOnClick(true);
        this.module23 = new Button("Module 2");
        this.choix3.add(this.module23);
        module23.setWidth("30%");
        module23.setDisableOnClick(true);
        this.module33 = new Button("Module 3");
        this.choix3.add(this.module33);
        module33.setWidth("30%");
        module33.setDisableOnClick(true);

        this.valider = new Button("Valider");
        this.add(this.valider);
        valider.setWidthFull();

        this.retour = new Button("Retour");
        this.add(this.retour);
        this.retour.addClickListener((e) -> {
            this.main.changeContenu(new Etudiant4Choix2(this.main));
            this.main.reculerBarre(main);
        });

        this.valider.addClickListener((e) -> {

            this.main.changeContenu(new EtudiantFin(this.main));
            this.main.avancerBarre(this.main);
            this.main.avancerBarre(this.main);

        });*/
    }

}
