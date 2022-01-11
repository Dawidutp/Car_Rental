package com.example.application.views.list;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Rent;
import com.example.application.data.service.AutoService;
import com.example.application.data.service.KlientService;
import com.example.application.data.service.MiastoService;
import com.example.application.data.service.RentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "Rents", layout = MainLayout.class)
@PageTitle("Rents | CarRent")
@PermitAll
public class RentView extends VerticalLayout {

    Grid<Rent> grid2 = new Grid<>(Rent.class);
    TextField filterText2 = new TextField();
    AddRentForm rentForm;
//    Button deleteButton = new Button("Delete"); //pamiętaj to dokończyć
    private RentService rentService;
    private MiastoService miastoService;
    private KlientService klientService;
    private AutoService autoService;


    public RentView(MiastoService miastoService, KlientService klientService,AutoService autoService,RentService rentService) {
        this.rentService = rentService;
        this.autoService = autoService;
        this.miastoService = miastoService;
        this.klientService = klientService;
        addClassName("rent-view");
        setSizeFull();

        configureGrid2();
        configureForm2();

        add(
                getToolbar2(),
                getContent2()
        );

        updateList2();
        closeEditor2();
    }

    private void configureForm2() {
        rentForm = new AddRentForm(miastoService.findAll(),klientService.findAll(),autoService.findAll());
        rentForm.setWidth("25em");

        rentForm.addListener(AddRentForm.SaveEvent.class, this::saveRent);
        rentForm.addListener(AddRentForm.DeleteEvent.class, this::deleteRent);
        rentForm.addListener(AddRentForm.CloseEvent.class, e -> closeEditor2());

    }

    private void saveRent(AddRentForm.SaveEvent event){
        rentService.saveRent(event.getRent());
        updateList2();
        closeEditor2();
    }
    private void deleteRent(AddRentForm.DeleteEvent event){
        rentService.deleteRent(event.getRent());
        updateList2();
        closeEditor2();
    }
    private void updateList2() {
        grid2.setItems(rentService.findAllRents(filterText2.getValue()));
    }

    private void configureGrid2(){
        grid2.addClassName("Rents-grid");
        grid2.setSizeFull();
        grid2.setColumns("id","rentDate","returnDate","driver","car","miasto");
        grid2.getColumns().forEach(col->col.setAutoWidth(true));


        grid2.asSingleSelect().addValueChangeListener(e -> editRent(e.getValue()));
    }
    private Component getContent2(){
        HorizontalLayout content2= new HorizontalLayout(grid2,rentForm);
        content2.setFlexGrow(2,grid2);
        content2.setFlexGrow(1,rentForm);
        content2.addClassName("content2");
        content2.setSizeFull();
        return content2;
    }
    private Component getToolbar2(){
        filterText2.setPlaceholder("Filter by Car or Client");
        filterText2.setClearButtonVisible(true);
        filterText2.setValueChangeMode(ValueChangeMode.LAZY);
        filterText2.addValueChangeListener(e ->updateList2());

        Button addRentButton = new Button("Add Rent");
        addRentButton.addClickListener(e -> addRent());

        HorizontalLayout toolbar2 = new HorizontalLayout(filterText2, addRentButton);
        toolbar2.addClassName("toolbar2");
        return toolbar2;
    }
    private void closeEditor2() {
        rentForm.setRent(null);
        rentForm.setVisible(false);
        removeClassName("editing");
    }
    private void editRent(Rent rent) {
        if(rent == null) {
            closeEditor2();
        } else {
            rentForm.setRent(rent);
            rentForm.setVisible(true);
            addClassName("editing");
        }
    }
    private void addRent() {
        grid2.asSingleSelect().clear();
        editRent(new Rent());
    }
}
