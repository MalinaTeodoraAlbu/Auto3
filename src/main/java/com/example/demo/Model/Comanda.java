package com.example.demo.Model;

import Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private int id;
    private String stare; // preluată, pregătită, plătită, plasată
    private Client client;
    private List<Produs> produse;
    // getter și setter
    private List<Observer> observatori = new ArrayList<>();


    public Comanda() {
    }

    public Comanda(int id, String stare, Client client, List<Produs> produse) {
        this.id = id;
        this.stare = stare;
        this.client = client;
        this.produse = produse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", stare='" + stare + '\'' +
                ", client=" + client +
                ", produse=" + produse +
                '}';
    }

    public void addObserver(Observer observer) {
        observatori.add(observer);
    }

    public void removeObserver(Observer observer) {
        observatori.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observatori) {
            observer.update(this);
        }
    }
}
