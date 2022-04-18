package Model;

import java.sql.Date;

public class Reservation {
    private int id;
    private int idUsers;
    private int idMatch;
    private Date reservation;
    private Date confirm;

    /*
     * Getters and Setters
     * */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public Date getReservation() {
        return reservation;
    }

    public void setReservation(Date reservation) {
        this.reservation = reservation;
    }

    public Date getConfirm() {
        return confirm;
    }

    public void setConfirm(Date confirm) {
        this.confirm = confirm;
    }
}
