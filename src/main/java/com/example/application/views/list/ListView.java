package com.example.application.views.list;

import com.example.application.data.entity.Auto;
import com.example.application.data.service.AutoService;
import com.example.application.data.service.MiastoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.TextField;
import org.hibernate.dialect.function.TemplateRenderer;

import javax.annotation.security.PermitAll;
import javax.swing.text.html.ImageView;


@Route(value = "ListView", layout = MainLayout.class)
@PermitAll
public class ListView extends VerticalLayout {
    Grid<Auto> grid = new Grid<>(Auto.class);
    TextField filterText = new TextField();
    AddCarForm form;
    private Auto auto;
    private VerticalLayout imageContainer;
    private AutoService autoService;
    private MiastoService miastoService;

    public ListView(AutoService autoService,MiastoService miastoService) {
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
        initImageContainer();
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

        HorizontalLayout toolbar = new HorizontalLayout(filterText,addcarButton);
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
        grid.asSingleSelect().addValueChangeListener(event-> {
            Auto selectedUser = event.getValue();
            if ( selectedUser != null){
                auto = selectedUser;
                showImage();
            }
        });

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
    private void showImage() {
        Image image = autoService.generateImage(auto);
        image.setHeight("100%");
        imageContainer.removeAll();
        imageContainer.add(image);
    }

    private void initImageContainer(){
        imageContainer = new VerticalLayout();
        imageContainer.setWidth("600px");
        imageContainer.setHeight("600px");
        imageContainer.getStyle().set("overflow-x", "auto");
        add(imageContainer);
    }

}
