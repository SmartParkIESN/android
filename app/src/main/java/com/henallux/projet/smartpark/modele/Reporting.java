package com.henallux.projet.smartpark.modele;
import java.util.Date;
/**
 * Created by LucasF on 06-12-16.
 */

public class Reporting {

    int Id;
    Date date;
    Announcement Announce;
    int AnnoucementId;

    public Reporting(Date date, int annoucementId) {
        this.date = date;
        AnnoucementId = annoucementId;
    }

    public Reporting(int id, Date date, int annoucementId, Announcement announce) {
        Id = id;
        this.date = date;
        Announce = announce;
        AnnoucementId = annoucementId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAnnoucementId() {return AnnoucementId;}

    public void setAnnoucementId(int annoucementId) {AnnoucementId = annoucementId;}

    public Announcement getAnnounce() {
        return Announce;
    }

    public void setAnnounce(Announcement announce) {
        Announce = announce;
    }
}
