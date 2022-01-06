/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.zins.testvaadin;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
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
import fr.insa.zins.classe.Module;

/**
 *
 * @author sabin
 */
public class ModuleForm extends FormLayout {
  private Module module;

  TextField intitule = new TextField("Intitulé");
  TextField description = new TextField("Description");
 
  TextField idGM = new TextField("Identifiant du groupe de module");
  //Binder<Module> binder = new BeanValidationBinder<>(Module.class);

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");
    
 public ModuleForm() {
 addClassName("module-form");
   // binder.bindInstanceFields(this);
    add(intitule,
        description,
        idGM,

        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, module)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));


   // binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }

  public void setModule(Module module) {
    this.module = module;
   // binder.readBean(module);
  }

  private void validateAndSave() {
    try {
     // binder.writeBean(module);
      fireEvent(new SaveEvent(this, module));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Events
  public static abstract class ModuleFormEvent extends ComponentEvent<ModuleForm> {
    private Module module;

    protected ModuleFormEvent(ModuleForm source, Module module) {
      super(source, false);
      this.module = module;
    }

    public Module getModule() {
      return module;
    }
  }

  public static class SaveEvent extends ModuleFormEvent {
    SaveEvent(ModuleForm source, Module module) {
      super(source, module);
    }
  }

  public static class DeleteEvent extends ModuleFormEvent {
    DeleteEvent(ModuleForm source, Module module) {
      super(source, module);
    }

  }

  public static class CloseEvent extends ModuleFormEvent {
    CloseEvent(ModuleForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }   
}

