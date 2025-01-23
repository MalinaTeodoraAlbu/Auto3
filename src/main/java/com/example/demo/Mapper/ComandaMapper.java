package com.example.demo.Mapper;

import com.example.demo.Model.Comanda;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComandaMapper {

    private List<Comanda> comenzi = new ArrayList<>();

    public void saveComanda(Comanda comanda) {
        // logica pentru salvarea comenzii într-un fișier sau în listă
        comenzi.add(comanda);
    }

    public Comanda loadComanda(int id) {
        // logica pentru încărcarea unei comenzi dintr-un fișier sau din listă
        return comenzi.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public List<Comanda> searchComenzi(String criteriu) {
        // logica pentru căutarea comenzilor pe baza unui criteriu
        return comenzi.stream()
                .filter(c -> c.getStare().contains(criteriu) ||
                        c.getClient().getNume().contains(criteriu))
                .collect(Collectors.toList());
    }

    public List<Comanda> loadAllComenzi() {
        // logica pentru încărcarea tuturor comenzilor
        return new ArrayList<>(comenzi);
    }
}