package com.nikos.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Platform")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PlatformID")
    private int PlatformID = 0;

    @Column(name="PlatformDescription", nullable = false)
    private String PlatformDescription = "";

    @Column(name="Developers", nullable = false)
    private String Developers = "";

    public Platform(){}

    public Platform(int platformID){
        PlatformID = platformID;
    }

    public int getPlatformID() {
        return PlatformID;
    }

    public String getDevelopers() {
        return Developers;
    }

    public String getPlatformDescription() {
        return PlatformDescription;
    }

    public void setPlatformDescription(String platformDescription) {
        PlatformDescription = platformDescription;
    }

    public void setPlatformID(int platformID) {
        PlatformID = platformID;
    }

    public void setDevelopers(String developers) {
        Developers = developers;
    }
}
