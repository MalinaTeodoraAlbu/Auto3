package Observer;

import com.example.demo.Model.Client;
import com.example.demo.Model.Comanda;

public class NotificareClient implements Observer {
    private Client client;

    public NotificareClient(Client client) {
        this.client = client;
    }

    @Override
    public void update(Comanda comanda) {
        // logica pentru notificarea clientului
        System.out.println("Notificare pentru clientul " + client.getNume() + ": Comanda " + comanda.getId() + " este acum în starea " + comanda.getStare());
    }
}