package com.georgy.androidtodolist;

/**
 * Created by georgy on 26.06.15.
 */
public class Todo {

    private int id;
    private String title;
    private String description;
    private String forDate;

    public Todo() {}

    public Todo(String label, String description, String forDate) {
        this.title = label;
        this.description = description;
        this.forDate = forDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForDate() {
        return forDate;
    }

    public void setForDate(String forDate) {
        this.forDate = forDate;
    }


    @Override
    public String toString() {
        return  id + "\n"+
                title + "\n"+
                description + "\n"+
                forDate + "\n"
               ;
    }

}
