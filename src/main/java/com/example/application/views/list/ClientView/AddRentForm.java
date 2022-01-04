package com.example.application.views.list.ClientView;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Miasto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;
/*
public class AddRentForm extends FormLayout {
    Binder<Auto> binder = new BeanValidationBinder<>(Auto.class);

    IntegerField VINnumber = new IntegerField("VIN Number");
    TextField registrationNumber = new TextField("Numer rejestracyjny");
    TextField model= new TextField("Model");
    IntegerField przebieg= new IntegerField("Przebieg");
    ComboBox<Miasto> miasto = new ComboBox<>("Miasto");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private Auto auto;

    public AddRentForm(List<Miasto> miasta) {
        addClassName("rent-form");
        binder.bindInstanceFields(this);

        miasto.setItems(miasta);
        miasto.setItemLabelGenerator(Miasto::getNazwa);

        add(VINnumber,
                registrationNumber,
                model,
                przebieg,
                miasto,
                createButtonsLayout());
    }

    public void setAuto(Auto auto){
        this.auto = auto;
        binder.readBean(auto);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new com.example.application.views.list.ClientView.AddRentForm.DeleteEvent(this, auto)));
        close.addClickListener(event -> fireEvent(new com.example.application.views.list.ClientView.AddRentForm.CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(auto);
            fireEvent(new com.example.application.views.list.ClientView.AddRentForm.SaveEvent(this, auto));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class AutoFormEvent extends ComponentEvent<com.example.application.views.list.AddCarForm> {
        private Auto auto;

        protected AutoFormEvent(com.example.application.views.list.ClientView.AddRentForm source, Auto auto) {
            super(source, false);
            this.auto = auto;
        }

        public Auto getAuto() {
            return auto;
        }
    }

    public static class SaveEvent extends com.example.application.views.list.AddCarForm.AutoFormEvent {
        SaveEvent(com.example.application.views.list.ClientView.AddRentForm source, Auto auto) {
            super(source, auto);
        }
    }

    public static class DeleteEvent extends com.example.application.views.list.AddCarForm.AutoFormEvent {
        DeleteEvent(com.example.application.views.list.ClientView.AddRentForm source, Auto auto) {
            super(source, auto);
        }

    }

    public static class CloseEvent extends com.example.application.views.list.AddCarForm.AutoFormEvent {
        CloseEvent(com.example.application.views.list.AddCarForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}*/