package com.example.application.views.list;

import com.example.application.data.service.AutoService;
import com.example.application.data.service.MiastoService;
import com.example.application.data.service.RentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "Statistics", layout = MainLayout.class)
@PageTitle("Stastics | Car Rental")
public class StatisticsView extends VerticalLayout {

    private RentService rentService;
    private AutoService autoService;
    private MiastoService miastoService;


    public StatisticsView(RentService rentService, AutoService autoService, MiastoService miastoService){
        this.rentService = rentService;
        this.autoService = autoService;
        this.miastoService = miastoService;
        addClassName("statistics-view");

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getRentStats(), getMiastaCharts());
    }

    private Component getMiastaCharts() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        miastoService.findAll().forEach(miasto -> dataSeries.add(new DataSeriesItem(miasto.getNazwa(),miasto.getRentCount())));
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    private Component getRentStats() {
        Span stats = new Span(rentService.countRents() + " wypożyczenia");
        stats.addClassName("text-xl");
        return stats;
    }
}
