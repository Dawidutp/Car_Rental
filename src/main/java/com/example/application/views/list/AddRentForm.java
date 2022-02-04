package com.example.application.views.list;

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

public class AddRentForm extends FormLayout {
    Binder<Rent> binder = new BeanValidationBinder<>(Rent.class);

    IntegerField id = new IntegerField("Id");
    DatePicker rentDate = new DatePicker("Rent Date");
    DatePicker returnDate = new DatePicker("Return Date");
    ComboBox<Klient> driver = new ComboBox<>("Driver");
    ComboBox<Auto> car = new ComboBox<>("Car");
    ComboBox<Miasto> miasto = new ComboBox<>("City");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    private Rent rent;



    public AddRentForm(List<Miasto> miasta, List<Klient> client, List<Auto> auto) {
        addClassName("rent-form");
        binder.bindInstanceFields(this);

        miasto.setItems(miasta);
        miasto.setItemLabelGenerator(Miasto::getNazwa);

        car.setItems(auto);
        car.setItemLabelGenerator(Auto::getModel);

        driver.setItems(client);
        driver.setItemLabelGenerator(Klient::getEmail);

        add(id,
                rentDate,
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
        delete.addClickListener(event -> fireEvent(new AddRentForm.DeleteEvent(this, rent)));
        close.addClickListener(event -> fireEvent(new AddRentForm.CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(rent);
            fireEvent(new AddRentForm.SaveEvent(this, rent));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class RentFormEvent extends ComponentEvent<AddRentForm> {
        private Rent rent;

        protected RentFormEvent(AddRentForm source, Rent rent) {
            super(source, false);
            this.rent = rent;
        }

        public Rent getRent() {
            return rent;
        }
    }

    public static class SaveEvent extends AddRentForm.RentFormEvent {
        SaveEvent(AddRentForm source, Rent rent) {
            super(source, rent);
        }
    }

    public static class DeleteEvent extends AddRentForm.RentFormEvent {
        DeleteEvent(AddRentForm source, Rent rent) {
            super(source, rent);
        }

    }

    public static class CloseEvent extends AddRentForm.RentFormEvent {
        CloseEvent(AddRentForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
