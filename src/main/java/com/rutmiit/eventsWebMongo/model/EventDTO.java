package com.rutmiit.eventsWebMongo.model;
public class EventDTO {

    private String id;
    private String title;
    private String category;
    private String date;
    private String location;
    private String hostNames;
    private String hostEmails;
    private int price;

    protected EventDTO() {
    }

    public EventDTO(String id, String title, String category, String date, String location, String hostNames, String hostEmails, int price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.location = location;
        this.hostNames = hostNames;
        this.hostEmails = hostEmails;
        this.price = price;
    }

    public String getHostNames() {
        return hostNames;
    }

    public void setHostNames(String hostNames) {
        this.hostNames = hostNames;
    }

    public String getHostEmails() {
        return hostEmails;
    }

    public void setHostEmails(String hostEmails) {
        this.hostEmails = hostEmails;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
