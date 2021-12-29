package com.example.application.views.list;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Rent;
import com.example.application.data.service.AutoService;
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
    private RentService rentService;
    Grid<Rent> grid2 = new Grid<>(Rent.class);
    TextField filterText = new TextField();
    AddForm form2;


    public RentView(Rent rent) {
        this.rentService = rentService;
        addClassName("rent-view");
        setSizeFull();

        configureGrid2();

        add(
                getToolbar2(),
                getContent2()
        );

        updateList2();
        closeEditor2();
    }

    private void configureGrid2(){
        grid2.addClassName("Rents-grid");
        grid2.setSizeFull();
        grid2.addColumn(Rent::getId).setHeader("Id");
        grid2.addColumn(Rent::getRentDate).setHeader("Rent Date");
        grid2.addColumn(Rent::getReturnDate).setHeader("Return Date");
        grid2.addColumn(Rent::getKm).setHeader("Km");
        grid2.addColumn(Rent::getDriver).setHeader("Client");
        grid2.addColumn(Rent::getCar).setHeader("Car");
        grid2.addColumn(Rent::getMiasto).setHeader("Miasto");
    }
    private Component getContent2(){
        HorizontalLayout content2= new HorizontalLayout(grid2);
        content2.addClassName("content2");
        content2.setSizeFull();
        return content2;
    }
    private Component getToolbar2(){
        filterText.setPlaceholder("Filter by Car or Client");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e ->updateList2());

        HorizontalLayout toolbar2 = new HorizontalLayout(filterText);
        toolbar2.addClassName("toolbar");
        return toolbar2;
    }
    private void closeEditor2() {
        form2.setAuto(null);
        form2.setVisible(false);
        removeClassName("editing");
    }
    private void updateList2() {
        grid2.setItems(rentService.findAllRents(filterText.getValue()));
    }

}
