package com.example.notesandtargets.models;

import java.util.Date;

public class Target {

    private String description;
    private Date creationDate;
    private Boolean isChecked;
    private String uid;

    public Target(){
        description = "description";
        isChecked = false;
        creationDate = new Date();
    }

    @Override
    public String toString() {
        return "Target{" +
                "description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", isChecked=" + isChecked +
                ", uid='" + uid + '\'' +
                '}';
    }

    public Target(String description, Date creationDate, Boolean isChecked, String uid) {
        this.description = description;
        this.creationDate = creationDate;
        this.isChecked = isChecked;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public String getDescription() {
        return description;
    }
}
