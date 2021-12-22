/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import static com.vaadin.flow.component.ComponentUtil.fireEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import fr.insa.zins.classe.Etudiant;

/**
 *
 * @author sabin
 */
public class EtudiantForm extends FormLayout {
  private Etudiant etudiant;

  TextField prenom = new TextField("Prénom");
  TextField nom = new TextField("Nom");
  EmailField email = new EmailField("Email");
  TextField mdp = new TextField("Mot de passe");
  Binder<Etudiant> binder = new BeanValidationBinder<>(Etudiant.class);

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");
    
 public EtudiantForm() {
 addClassName("etudiant-form");
    binder.bindInstanceFields(this);
    add(prenom,
        nom,
        email,
        mdp,

        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, etudiant)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));


    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }

  public void setEtudiant(Etudiant etudiant) {
    this.etudiant = etudiant;
    binder.readBean(etudiant);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(etudiant);
      fireEvent(new SaveEvent(this, etudiant));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  // Events
  public static abstract class EtudiantFormEvent extends ComponentEvent<EtudiantForm> {
    private Etudiant etudiant;

    protected EtudiantFormEvent(EtudiantForm source, Etudiant etudiant) {
      super(source, false);
      this.etudiant = etudiant;
    }

    public Etudiant getEtudiant() {
      return etudiant;
    }
  }

  public static class SaveEvent extends EtudiantFormEvent {
    SaveEvent(EtudiantForm source, Etudiant etudiant) {
      super(source, etudiant);
    }
  }

  public static class DeleteEvent extends EtudiantFormEvent {
    DeleteEvent(EtudiantForm source, Etudiant etudiant) {
      super(source, etudiant);
    }

  }

  public static class CloseEvent extends EtudiantFormEvent {
    CloseEvent(EtudiantForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }    
}
