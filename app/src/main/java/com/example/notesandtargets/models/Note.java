package com.example.notesandtargets.models;

public class Note {
    private String title;
    private String description;

    public Note(){
        title = "title";
        description = "description";
    }

    public Note(String title,String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
