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

    Grid<Rent> grid2 = new Grid<>(Rent.class);
    TextField filterText2 = new TextField();
    Button deleteButton = new Button("Delete"); //pamiętaj to dokończyć
    private RentService rentService;


    public RentView(RentService rentService) {
        this.rentService = rentService;
        addClassName("rent-view");
        setSizeFull();

        configureGrid2();

        add(
                getToolbar2(),
                grid2
        );

        updateList2();
    }

    private void updateList2() {
        grid2.setItems(rentService.findAllRents(filterText2.getValue()));
    }

    private void configureGrid2(){
        grid2.addClassName("Rents-grid");
        grid2.setSizeFull();
        grid2.setColumns("id","rentDate","returnDate","km","driver","car","miasto");
        grid2.getColumns().forEach(col->col.setAutoWidth(true));


//        grid2.asSingleSelect().addValueChangeListener(e -> editAuto(e.getValue()));
    }
//    private Component getContent2(){
//        HorizontalLayout content2= new HorizontalLayout(grid2);
//        content2.addClassName("content2");
//        content2.setSizeFull();
//        return content2;
//    }
    private Component getToolbar2(){
        filterText2.setPlaceholder("Filter by Car or Client");
        filterText2.setClearButtonVisible(true);
        filterText2.setValueChangeMode(ValueChangeMode.LAZY);
        filterText2.addValueChangeListener(e ->updateList2());

        HorizontalLayout toolbar2 = new HorizontalLayout(filterText2,deleteButton);
        toolbar2.addClassName("toolbar2");
        return toolbar2;
    }
//    private void closeEditor2() {
//        form2.setAuto(null);
//        form2.setVisible(false);
//        removeClassName("editing");
//    }
}
