package com.anysound.bean;

import com.anysound.model.TransportType;
import com.anysound.model.Trip;
import com.anysound.service.TripService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.List;

@Named("tripBean")
@SessionScoped
public class TripBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private TripService tripService;

    private List<Trip> trips;
    private Trip selectedTrip;
    private String searchQuery;

    @PostConstruct
    public void init() {
        loadTrips();
        selectedTrip = new Trip();
    }

    private void loadTrips() {
        trips = tripService.findAll();
    }

    // Метод для AJAX-поиска (когда печатаешь текст)
    public void searchAjax(AjaxBehaviorEvent event) {
        trips = tripService.search(searchQuery);
    }

    // Сохранение рейса
    public String save() {
        tripService.save(selectedTrip);
        return cancel(); // После сохранения сбрасываем всё и идем в каталог
    }

    // Переход на пустую форму добавления
    public String prepareAdd() {
        selectedTrip = new Trip();
        return "form?faces-redirect=true";
    }

    // Переход на форму для редактирования
    public void edit(Trip trip) {
        this.selectedTrip = new Trip(trip.getId(), trip.getFlightNumber(), trip.getDeparture(),
                trip.getDestination(), trip.getTransportType(), trip.getPrice(), trip.getSeats());
    }

    // Удаление
    public void delete(Long id) {
        tripService.delete(id);
        if (searchQuery != null && !searchQuery.isBlank()) {
            trips = tripService.search(searchQuery); // сохраняем фильтр после удаления
        } else {
            loadTrips();
        }
    }

    // Универсальный метод сброса: очищает поиск, форму и возвращает в каталог
    public String cancel() {
        selectedTrip = new Trip();
        searchQuery = null;
        loadTrips();
        return "catalog?faces-redirect=true";
    }

    public TransportType[] getTransportTypes() { return TransportType.values(); }

    // Геттеры и сеттеры
    public List<Trip> getTrips() { return trips; }
    public void setTrips(List<Trip> trips) { this.trips = trips; }

    public Trip getSelectedTrip() { return selectedTrip; }
    public void setSelectedTrip(Trip selectedTrip) { this.selectedTrip = selectedTrip; }

    public String getSearchQuery() { return searchQuery; }
    public void setSearchQuery(String searchQuery) { this.searchQuery = searchQuery; }
}