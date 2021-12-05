/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.quarteroni.Interface;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import static com.vaadin.flow.component.icon.VaadinIcon.CORNER_UPPER_LEFT;
import static com.vaadin.flow.component.icon.VaadinIcon.CORNER_UPPER_RIGHT;
import static com.vaadin.flow.component.icon.VaadinIcon.ELLIPSIS_DOTS_V;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.awt.Panel;

/**
 *
 * @author arthurquarteroni
 */
@Route("")
@PageTitle("VuePrincipale")
public class VuePrincipale extends VerticalLayout {

    private HorizontalLayout entete;
    private HorizontalLayout reste;
    private VerticalLayout menugauche;
    private VerticalLayout contenu;
    private Icon trianglegauche;
    private Icon triangledroite;
    private Icon pointille;
    private Button retour;
    private TextField titre;
    private Accordion electifs;
   

    private Integer idUser = null;
    private final VerticalLayout contenuetbarre;
    private final VerticalLayout barrelayout;
    private final ProgressBar barre;
    private Icon espace;
  

    public final void changeContenu(Component c) {
        this.contenu.removeAll();
        this.contenu.add(c);
    }

    public final void avancerBarre(Component c) {
        double value = barre.getValue() + 15;
        if (value > barre.getMax()) {
            value = barre.getMin();
        }
        barre.setValue(value);

    }

    public VuePrincipale() {

        // ENTETE
        this.entete = new HorizontalLayout();
        this.entete.setWidthFull();
        this.entete.setHeight("25%");
        this.entete.setJustifyContentMode(JustifyContentMode.CENTER);

        this.add(this.entete);
        this.entete.setMargin(true);
        Style setentete = this.entete.getStyle().set("border", "1px solid grey");

        this.trianglegauche = new Icon(CORNER_UPPER_LEFT);
        this.entete.add(this.trianglegauche);
        trianglegauche.setSize("50px");
        trianglegauche.setColor("blue");

        this.titre = new TextField();
        this.titre.setValue("LOGICIEL DE CHOIX DES ELECTIFS");
        this.titre.setReadOnly(true);
        this.titre.setSizeFull();
        this.titre.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        
        this.entete.add(this.titre);
       
        
        this.triangledroite = new Icon(CORNER_UPPER_RIGHT);
        this.entete.add(this.triangledroite);
        triangledroite.setSize("50px");
        triangledroite.setColor("blue");

        //Layout RESTE (menu gauche + contenuetbarre)
        this.reste = new HorizontalLayout();
        this.reste.setWidthFull();
        this.reste.setHeightFull();
        this.reste.setMargin(true);
        Style setreste = this.reste.getStyle().set("border", "1px solid grey");
        this.add(this.reste);

        //MENU GAUCHE
        this.menugauche = new VerticalLayout();
        this.menugauche.setHeightFull();
        this.menugauche.setWidth("25%");
        this.menugauche.setMargin(true);
        this.menugauche.setJustifyContentMode(JustifyContentMode.CENTER);
        this.reste.add(this.menugauche);

        //accordéons menus électifs
        this.electifs = new Accordion();

        FormLayout accountForm = new FormLayout();
        accountForm.add(new Span("prap"));
        accountForm.add(new Span("prap2"));
        accountForm.add(new Span("prap3"));
        accountForm.add(new Text("prap4"));
        electifs.add("Groupe éléctifs 1", accountForm);

        FormLayout accountFor = new FormLayout();
        accountFor.add(new Span("prap"));
        accountFor.add(new Span("prap2"));
        accountFor.add(new Span("prap3"));
        accountFor.add(new Text("prap4"));

        electifs.add("Groupe éléctifs 2", accountFor);

        this.menugauche.add(this.electifs);

        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");
        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.menugauche.add(this.espace);
        espace.setSize("30px");
        espace.setColor("white");

        this.retour = new Button("Retour à l'accueil");
        this.menugauche.add(this.retour);

        //Layout pour organiser les 2 suivants
        this.contenuetbarre = new VerticalLayout();
        this.contenuetbarre.setWidthFull();
        this.contenuetbarre.setHeightFull();
        this.contenuetbarre.setMargin(true);
        this.reste.add(this.contenuetbarre);

        //Layout contenu (dans contenuetbarre)
        this.contenu = new VerticalLayout();
        this.contenu.setHeightFull();
        this.contenu.setWidthFull();
        this.contenu.setMargin(true);
        Style setcontenu = this.contenu.getStyle().set("border", "1px solid grey");
        this.contenuetbarre.add(this.contenu);

        //Layout barre chargement (dans contenuetbarre)
        this.barrelayout = new VerticalLayout();
        this.barrelayout.setHeightFull();
        this.barrelayout.setWidthFull();
        this.barrelayout.setMargin(true);
        this.contenuetbarre.add(this.barrelayout);

        this.espace = new Icon(ELLIPSIS_DOTS_V);
        this.add(this.espace);
        espace.setSize("100px");
        espace.setColor("white");

        this.barre = new ProgressBar(10, 100, 20);
        barre.setWidthFull();
        this.barrelayout.add(this.barre);

        this.changeContenu(new Accueil(this));
    }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
