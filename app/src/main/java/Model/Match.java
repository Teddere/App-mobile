package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Match implements Serializable {
    private String id;
    //Equipe A
    private String teamsA;
    //Equipe B
    private String teamsB;
    //Lieu
    private String place;
    //rencontre
    private String meet;
    //Nbre de place
    private String square;

    /*
     * Getters And Setters
     * */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamsA() {
        return teamsA;
    }

    public void setTeamsA(String teamsA) {
        this.teamsA = teamsA;
    }

    public String getTeamsB() {
        return teamsB;
    }

    public void setTeamsB(String teamsB) {
        this.teamsB = teamsB;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }
}
