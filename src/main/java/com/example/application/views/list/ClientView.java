package com.example.application.views.list;

import com.example.application.data.entity.Auto;
import com.example.application.data.entity.Klient;
import com.example.application.data.service.AutoService;
import com.example.application.data.service.KlientService;
import com.example.application.data.service.MiastoService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.checkerframework.checker.units.qual.K;

import javax.annotation.security.PermitAll;

@Route(value = "Clients", layout = MainLayout.class)
@PageTitle("Clients | CarRent")
@PermitAll
public class ClientView extends VerticalLayout {
    Grid<Klient> grid = new Grid<>(Klient.class);
    TextField filterText = new TextField();
    AddClientForm form;
    private KlientService klientService;

    public ClientView(KlientService klientService) {

        this.klientService = klientService;

        addClassName("client-view");
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
        form.setClient(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(klientService.findAllClients(filterText.getValue()));
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
        form = new AddClientForm();
        form.setWidth("25em");

        form.addListener(AddClientForm.SaveEvent.class, this::saveClient);
        form.addListener(AddClientForm.DeleteEvent.class, this::deleteClient);
        form.addListener(AddClientForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveClient(AddClientForm.SaveEvent event){
        klientService.saveClient(event.getKlient());
        updateList();
        closeEditor();
    }
    private void deleteClient(AddClientForm.DeleteEvent event){
        klientService.deleteClient(event.getKlient());
        updateList();
        closeEditor();
    }

    private Component getToolbar(){
        filterText.setPlaceholder("Filter by id or Last Name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e ->updateList());

        Button addcarButton = new Button("Add Client");
        addcarButton.addClickListener(e -> addClient());

        HorizontalLayout toolbar = new HorizontalLayout(filterText,addcarButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addClient() {
        grid.asSingleSelect().clear();
        editClient(new Klient());
    }

    private void configureGrid(){
        grid.addClassName("Clients-grid");
        grid.setSizeFull();
        grid.setColumns("id","email","password","name","lastName","enabled","role");
        grid.getColumns().forEach(col->col.setAutoWidth(true));


        grid.asSingleSelect().addValueChangeListener(e -> editClient(e.getValue()));

    }

    private void editClient(Klient klient) {
        if(klient == null) {
            closeEditor();
        } else {
            form.setClient(klient);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}
