package com.example.notesandtargets.models;

public class Note {
    private String title;
    private String description;
    private String uid;

    public Note(){
        title = "title";
        description = "description";
    }

    public Note(String title,String description,String uid) {
        this.title = title;
        this.description = description;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
