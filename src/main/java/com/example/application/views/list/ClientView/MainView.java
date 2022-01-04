package com.example.application.views.list.ClientView;

import com.example.application.data.entity.Auto;
import com.example.application.data.service.AutoService;
import com.example.application.data.service.MiastoService;
import com.example.application.views.list.AddCarForm;
import com.example.application.views.list.MainLayout;
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
    AddCarForm form;
    private AutoService autoService;
    private MiastoService miastoService;

    public MainView(AutoService autoService,MiastoService miastoService) {
        this.autoService = autoService;
        this.miastoService = miastoService;
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
        form.setAuto(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(autoService.findAllCars(filterText.getValue()));
    }

    private Component getContent(){
        HorizontalLayout content= new HorizontalLayout(grid, form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }
    private void configureForm() {
        form = new AddCarForm(miastoService.findAll());
        form.setWidth("25em");

        form.addListener(AddCarForm.SaveEvent.class, this::saveCar);
        form.addListener(AddCarForm.DeleteEvent.class, this::deleteCar);
        form.addListener(AddCarForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveCar(AddCarForm.SaveEvent event){
        autoService.saveCar(event.getAuto());
        updateList();
        closeEditor();
    }
    private void deleteCar(AddCarForm.DeleteEvent event){
        autoService.deleteCar(event.getAuto());
        updateList();
        closeEditor();
    }

    private Component getToolbar(){
        filterText.setPlaceholder("Filter by model or Vin");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e ->updateList());

        Button addcarButton = new Button("Add car");
        addcarButton.addClickListener(e -> addCar());

        Button login = new Button("Login", buttonClickEvent -> UI.getCurrent().navigate("login"));
        HorizontalLayout toolbar = new HorizontalLayout(filterText,addcarButton, login);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addCar() {
        grid.asSingleSelect().clear();
        editAuto(new Auto());
    }

    private void configureGrid(){
        grid.addClassName("Cars-grid");
        grid.setSizeFull();
        grid.setColumns("VINnumber","registrationNumber","model","przebieg");
        grid.getColumns().forEach(col->col.setAutoWidth(true));


        grid.asSingleSelect().addValueChangeListener(e -> editAuto(e.getValue()));

    }

    private void editAuto(Auto auto) {
        if(auto == null) {
            closeEditor();
        } else {
            form.setAuto(auto);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}
