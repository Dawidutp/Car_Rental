package com.example.application.views.list;

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

public class AddCarForm extends FormLayout {
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

    public AddCarForm(List<Miasto> miasta) {
        addClassName("auto-form");
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
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, auto)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(auto);
            fireEvent(new SaveEvent(this, auto));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class AutoFormEvent extends ComponentEvent<AddCarForm> {
        private Auto auto;

        protected AutoFormEvent(AddCarForm source, Auto auto) {
            super(source, false);
            this.auto = auto;
        }

        public Auto getAuto() {
            return auto;
        }
    }

    public static class SaveEvent extends AutoFormEvent {
        SaveEvent(AddCarForm source, Auto auto) {
            super(source, auto);
        }
    }

    public static class DeleteEvent extends AutoFormEvent {
        DeleteEvent(AddCarForm source, Auto auto) {
            super(source, auto);
        }

    }

    public static class CloseEvent extends AutoFormEvent {
        CloseEvent(AddCarForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
