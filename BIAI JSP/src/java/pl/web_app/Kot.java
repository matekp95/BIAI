/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.web_app;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Kamil
 */
@Entity
@Table(name="koty")
public class Kot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name="IMIE", nullable=false)
    private String imie;
    @Column(name="DATAURODZENIA", nullable=false)
    private Date dataUrodzenia;
    @Column(name="WAGA", nullable=false)
    private Float waga;
    @Column(name="IMIEOPIEKUNA", nullable=false)
    private String imieOpiekuna;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public Float getWaga() {
        return waga;
    }

    public void setWaga(Float waga) {
        this.waga = waga;
    }

    public String getImieOpiekuna() {
        return imieOpiekuna;
    }

    public void setImieOpiekuna(String imieOpiekuna) {
        this.imieOpiekuna = imieOpiekuna;
    }

    public Kot() {
    }
    
}
/*CREATE TABLE KOTY (
  id INTEGER GENERATED ALWAYS AS IDENTITY,
  imie varchar(100),
  dataUrodzenia date,
  waga float,
  imieOpiekuna varchar(100),
  PRIMARY KEY (id)
)*/