package com.example.application.views.list;

import com.example.application.data.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

public class MainLayout extends AppLayout {

    private SecurityService securityService;

    public MainLayout(SecurityService securityService){
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createDrawer() {

        RouterLink listView= new RouterLink("List",ListView.class);
        RouterLink rentView= new RouterLink("Rents",RentView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());


        addToDrawer(new VerticalLayout(
                listView,
                rentView
        ));
    }

    private void createHeader() {
        H1 logo = new H1("Car Rental");
        logo.addClassNames("text-l","m-m");

        Button logOut = new Button("log out", e -> securityService.logout());
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(),logo,logOut);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0","px-m");

        addToNavbar(header);
    }
}
