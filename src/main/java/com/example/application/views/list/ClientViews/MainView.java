package com.example.application.views.list.ClientViews;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Rent;
import com.example.application.data.service.AutoService;
import com.example.application.data.service.KlientService;
import com.example.application.data.service.MiastoService;
import com.example.application.data.service.RentService;
import com.example.application.views.list.AddCarForm;
import com.example.application.views.list.AddRentForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "MainClientView", layout = MainLayout2.class)
@PermitAll
public class MainView extends VerticalLayout {
    Grid<Auto> grid = new Grid<>(Auto.class);
    TextField filterText = new TextField();
    ClientRentForm rentForm;
    private AutoService autoService;
    private MiastoService miastoService;
    private KlientService klientService;
    private RentService rentService;

    public MainView(AutoService autoService,MiastoService miastoService,KlientService klientService,RentService rentService) {
        this.autoService = autoService;
        this.miastoService = miastoService;
        this.klientService = klientService;
        this.rentService = rentService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList();
        closeEditor();
    }

    private void closeEditor() {
        rentForm.setRent(null);
        rentForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(autoService.findAllCars(filterText.getValue()));
    }

    private Component getContent(){
        HorizontalLayout content= new HorizontalLayout(grid, rentForm);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,rentForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }
    private void configureForm() {
        rentForm = new ClientRentForm(miastoService.findAll(),klientService.findAll(),autoService.findAll());
        rentForm.setWidth("25em");

        rentForm.addListener(ClientRentForm.SaveEvent.class, this::saveRent);
        rentForm.addListener(ClientRentForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveRent(ClientRentForm.SaveEvent event){
        rentService.saveRent(event.getRent());
        updateList();
        closeEditor();
    }


    private Component getToolbar(){
        filterText.setPlaceholder("Filter by model or Vin");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e ->updateList());

        Button addrentButton = new Button("Rent");
        addrentButton.addClickListener(e -> addRent());


        HorizontalLayout toolbar = new HorizontalLayout(filterText,addrentButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addRent() {
        grid.asSingleSelect().clear();
        editRent(new Rent());
    }

    private void configureGrid(){
        grid.addClassName("Cars-grid");
        grid.setSizeFull();
        grid.setColumns("VINnumber","registrationNumber","model","przebieg");
        grid.getColumns().forEach(col->col.setAutoWidth(true));



    }


    private void editRent(Rent rent) {
        if(rent == null) {
            closeEditor();
        } else {
            rentForm.setRent(rent);
            rentForm.setVisible(true);
            addClassName("editing");
        }
    }

}
