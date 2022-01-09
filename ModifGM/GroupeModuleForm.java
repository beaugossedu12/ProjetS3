/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin.ModifGM;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import fr.insa.zins.classe.GroupeModule;

/**
 *
 * @author sabin
 */
public class GroupeModuleForm extends FormLayout {
  private GroupeModule groupeModule;

  TextField intitule = new TextField("Intitulé");
  TextField description = new TextField("Description");
 

  Binder<GroupeModule> binder = new BeanValidationBinder<>(GroupeModule.class);

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");
    
 public GroupeModuleForm() {
 addClassName("groupeModule-form");
    binder.bindInstanceFields(this);
    add(intitule,
        description,
        

        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, groupeModule)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));


    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }

  public void setGroupeModule(GroupeModule groupeModule) {
    this.groupeModule = groupeModule;
    binder.readBean(groupeModule);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(groupeModule);
      fireEvent(new SaveEvent(this, groupeModule));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  // Events
  public static abstract class GroupeModuleFormEvent extends ComponentEvent<GroupeModuleForm> {
    private fr.insa.zins.classe.GroupeModule groupeModule;

    protected GroupeModuleFormEvent(GroupeModuleForm source, fr.insa.zins.classe.GroupeModule groupeModule) {
      super(source, false);
      this.groupeModule = groupeModule;
    }

    public fr.insa.zins.classe.GroupeModule getGroupeModule() {
      return groupeModule;
    }
  }

  public static class SaveEvent extends GroupeModuleFormEvent {
    SaveEvent(GroupeModuleForm source, fr.insa.zins.classe.GroupeModule groupeModule) {
      super(source, groupeModule);
    }
  }

  public static class DeleteEvent extends GroupeModuleFormEvent {
    DeleteEvent(GroupeModuleForm source, fr.insa.zins.classe.GroupeModule groupeModule) {
      super(source, groupeModule);
    }

  }

  public static class CloseEvent extends GroupeModuleFormEvent {
    CloseEvent(GroupeModuleForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }   
}
