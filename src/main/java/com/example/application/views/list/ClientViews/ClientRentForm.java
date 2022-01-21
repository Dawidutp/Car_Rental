package com.example.application.views.list.ClientViews;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Klient;
import com.example.application.data.entity.Miasto;
import com.example.application.data.entity.Rent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;


public class ClientRentForm extends FormLayout {
    Binder<Rent> binder = new BeanValidationBinder<>(Rent.class);

    DatePicker rentDate = new DatePicker("Data wypożyczenia");
    DatePicker returnDate = new DatePicker("Data oddania");
    ComboBox<Klient> driver = new ComboBox<>("Kierowca");
    ComboBox<Auto> car = new ComboBox<>("Samochód");
    ComboBox<Miasto> miasto = new ComboBox<>("Miasto");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private Rent rent;



    public ClientRentForm(List<Miasto> miasta, List<Klient> client, List<Auto> auto) {
        addClassName("rent-form");
        binder.bindInstanceFields(this);

        miasto.setItems(miasta);
        miasto.setItemLabelGenerator(Miasto::getNazwa);

        car.setItems(auto);
        car.setItemLabelGenerator(Auto::getModel);

        driver.setItems(client);
        driver.setItemLabelGenerator(Klient::getEmail);

        add(rentDate,
                returnDate,
                driver,
                car,
                miasto,
                createButtonsLayout());
    }

    public void setRent(Rent rent){
        this.rent = rent;
        binder.readBean(rent);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> validateAndSave());
        close.addClickListener(event -> fireEvent(new ClientRentForm.CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(rent);
            fireEvent(new ClientRentForm.SaveEvent(this, rent));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ClientRentFormEvent extends ComponentEvent<ClientRentForm> {
        private Rent rent;

        protected ClientRentFormEvent(ClientRentForm source, Rent rent) {
            super(source, false);
            this.rent = rent;
        }

        public Rent getRent() {
            return rent;
        }
    }

    public static class SaveEvent extends ClientRentForm.ClientRentFormEvent {
        SaveEvent(ClientRentForm source, Rent rent) {
            super(source, rent);
        }
    }

    public static class CloseEvent extends ClientRentForm.ClientRentFormEvent {
        CloseEvent(ClientRentForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}