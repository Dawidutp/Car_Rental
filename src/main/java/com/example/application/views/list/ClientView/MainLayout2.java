package com.example.application.views.list.ClientView;

import com.example.application.data.security.SecurityService;
import com.example.application.views.list.ListView;
import com.example.application.views.list.RentView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout2 extends AppLayout {

    private SecurityService securityService;

    public MainLayout2(SecurityService securityService){
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createDrawer() {

        RouterLink mainView= new RouterLink("Main View", MainView.class);
        mainView.setHighlightCondition(HighlightConditions.sameLocation());


        addToDrawer(new VerticalLayout(
                mainView
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
