package com.example.application.views.list;

import com.example.application.data.entity.Klient;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class AddClientForm extends FormLayout {
    Binder<Klient> binder = new BeanValidationBinder<>(Klient.class);

    IntegerField id = new IntegerField("Client Id");
    TextField Email = new TextField("Email");
    TextField Password= new TextField("Password");
    TextField Name= new TextField("Name");
    TextField LastName = new TextField("Last Name");
    IntegerField enabled = new IntegerField("Account Status");
    TextField role = new TextField("Role");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private Klient klient;

    public AddClientForm() {
        addClassName("client-form");
        binder.bindInstanceFields(this);


        add(id,
                Email,
                Password,
                Name,
                LastName,
                enabled,
                role,
                createButtonsLayout());
    }

    public void setClient(Klient klient){
        this.klient = klient;
        binder.readBean(klient);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new AddClientForm.DeleteEvent(this, klient)));
        close.addClickListener(event -> fireEvent(new AddClientForm.CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(klient);
            fireEvent(new AddClientForm.SaveEvent(this, klient));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ClientFormEvent extends ComponentEvent<AddClientForm> {
        private Klient klient;

        protected ClientFormEvent(AddClientForm source, Klient klient) {
            super(source, false);
            this.klient = klient;
        }

        public Klient getKlient() {
            return klient;
        }
    }

    public static class SaveEvent extends AddClientForm.ClientFormEvent {
        SaveEvent(AddClientForm source, Klient klient) {
            super(source, klient);
        }
    }

    public static class DeleteEvent extends AddClientForm.ClientFormEvent {
        DeleteEvent(AddClientForm source, Klient klient) {
            super(source, klient);
        }

    }

    public static class CloseEvent extends AddClientForm.ClientFormEvent {
        CloseEvent(AddClientForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
