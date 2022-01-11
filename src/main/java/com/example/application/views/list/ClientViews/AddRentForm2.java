//package com.example.application.views.list.ClientView;
//
//import com.example.application.data.entity.Auto;
//import com.example.application.data.entity.Klient;
//import com.example.application.data.entity.Miasto;
//import com.example.application.data.entity.Rent;
//import com.example.application.data.repository.AutoRepository;
//import com.example.application.data.repository.RentRepository;
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.ComponentEvent;
//import com.vaadin.flow.component.ComponentEventListener;
//import com.vaadin.flow.component.Key;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.datepicker.DatePicker;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.textfield.IntegerField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.BeanValidationBinder;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.data.binder.ValidationException;
//import com.vaadin.flow.shared.Registration;
//
//import java.util.List;
//
//public class AddRentForm extends FormLayout {
//    Binder<Rent> binder = new BeanValidationBinder<>(Rent.class);
//
////    IntegerField VINnumber = new IntegerField("Numer VIN");
////    TextField registrationNumber = new TextField("Numer rejestracyjny");
////    TextField model= new TextField("Model");
//    IntegerField carVin= new IntegerField("VIN samochodu");
//    IntegerField idKlienta= new IntegerField("Id Klienta");
//    IntegerField idMiasta= new IntegerField("Id Miasta");
////    ComboBox<Miasto> miasto = new ComboBox<>("Miasto");
//
//    DatePicker dataRezerwacji = new DatePicker("Data Rezerwacji");
//    DatePicker dataZwrotu = new DatePicker("Data Zwrotu");
//
//    Button save = new Button("Save");
//    //Button delete = new Button("Delete");
//    Button close = new Button("Cancel");
//    private Auto auto;
//
//    public AddRentForm() {
//        addClassName("rent-form");
//        binder.bindInstanceFields(this);
//
//
//        add(dataRezerwacji,
//                dataZwrotu,
//                carVin,
//                idKlienta,
//                idMiasta,
//
//                createButtonsLayout());
//    }
//
//    public void setRent(Auto auto){
//        this.auto = auto;
//        binder.readBean(auto);
//    }
//
//    private Component createButtonsLayout() {
//        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        //delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
//        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//
//        save.addClickListener(event -> validateAndSave());
//        //delete.addClickListener(event -> fireEvent(new com.example.application.views.list.ClientView.AddRentForm.DeleteEvent(this, auto)));
//        close.addClickListener(event -> fireEvent(new CloseEvent(this)));
//
//        save.addClickShortcut(Key.ENTER);
//        close.addClickShortcut(Key.ESCAPE);
//
//        return new HorizontalLayout(save, close);
//    }
//
//    private void validateAndSave() {
//        try {
//            binder.writeBean(auto);
//            fireEvent(new com.example.application.views.list.ClientView.AddRentForm.SaveEvent(this, auto));
//        } catch (ValidationException e){
//            e.printStackTrace();
//        }
//    }
//
//    // Events
//    public static abstract class RentFormEvent extends ComponentEvent<AddRentForm> {
//        private Rent rent;
//        private Auto auto;
//        private Klient klient;
//        private AutoRepository autoRepository;
//        private RentRepository rentRepository;
//
//        protected RentFormEvent(AddRentForm source, Auto auto, Klient klient) {
//            super(source, false);
//            this.rent = autoRepository.
//            this.auto = auto;
//        }
//
//        public Auto getAuto() {
//            return auto;
//        }
//    }
//
//    public static class SaveEvent extends AddRentForm.RentFormEvent {
//        SaveEvent(AddRentForm source, Rent rent) {
//            super(source, rent);
//        }
//    }
//
////    public static class DeleteEvent extends com.example.application.views.list.AddCarForm.AutoFormEvent {
////        DeleteEvent(com.example.application.views.list.ClientView.AddRentForm source, Auto auto) {
////            super(source, auto);
////        }
////
////    }
//
//    public static class CloseEvent extends AddRentForm.RentFormEvent {
//        CloseEvent(AddRentForm source) {
//            super(source, null);
//        }
//    }
//
//    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
//                                                                  ComponentEventListener<T> listener) {
//        return getEventBus().addListener(eventType, listener);
//    }
//}