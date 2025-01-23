# Auto3
pai eu vreau sa faci tot tot Sã se realizeze o aplicatie pentru gestiunea reclamatiilor clientilor unui magazin virtual, referitoare la produsele si serviciile oferite. O reclamatie poate trece prin urmätoarele stäri: înregistrata, in analiza, solutionatã. Clientii aplicatiei vor fi notificati la trecerea dintr-o stare in alta a solicitärii. Interfata aplicatiei permite vizualizarea, filtrarea/cäutarea solicitãrilor, precum si modificarea stãrii acestora. Pentru implementare, utilizati o arhitectură MVC . Asigurati persistenta datelor prin intermediul unui Data Mapper. Datele pot fi stocate in fisiere text sau binare. Pot fi utilizate limbajele de programare Java sau C#, iar aplicatia poate fi desktop, Web sau mobilă. Notă: datele despre comenzi, produse/servicii si clienti se preiau din fisierele aplicatiei.
Am înțeles. Îți voi oferi un exemplu detaliat pentru a realiza aplicația, utilizând Java și arhitectura MVC. Să începem!

1. Model
Definim clasele pentru Reclamatie, Client și Produs/Serviciu. Folosim Data Mapper pentru a asigura persistența datelor în fișiere text sau binare.

Clasa Reclamatie
java
public class Reclamatie {
    private String id;
    private String stare;
    private String descriere;
    private String clientId;
    // Getters și Setters

    public Reclamatie(String id, String descriere, String clientId) {
        this.id = id;
        this.descriere = descriere;
        this.clientId = clientId;
        this.stare = "înregistrată";
    }

    public void setStare(String stare) {
        this.stare = stare;
        // Notificarea clientului la schimbarea stării
    }
}
Clasa Client
java
public class Client {
    private String id;
    private String nume;
    private String email;
    // Getters și Setters

    public Client(String id, String nume, String email) {
        this.id = id;
        this.nume = nume;
        this.email = email;
    }
}
Clasa Produs
java
public class Produs {
    private String id;
    private String nume;
    private double pret;
    // Getters și Setters

    public Produs(String id, String nume, double pret) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
    }
}
2. Data Mapper
Asigură persistența datelor în fișiere.

Clasa DataMapper
java
import java.io.*;
import java.util.*;

public class DataMapper {
    public void salveazaReclamatie(Reclamatie reclamatie) throws IOException {
        // Logica pentru salvarea unei reclamații în fișier text
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reclamatii.txt", true))) {
            writer.write(reclamatie.getId() + "," + reclamatie.getDescriere() + "," + reclamatie.getClientId() + "," + reclamatie.getStare());
            writer.newLine();
        }
    }

    public List<Reclamatie> incarcaReclamatii() throws IOException {
        List<Reclamatie> reclamatii = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("reclamatii.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Reclamatie reclamatie = new Reclamatie(parts[0], parts[1], parts[2]);
                reclamatie.setStare(parts[3]);
                reclamatii.add(reclamatie);
            }
        }
        return reclamatii;
    }
}
3. Controller
Gestionează logica aplicației și tranziția între stările reclamațiilor.

Clasa ReclamatieController
java
public class ReclamatieController {
    private DataMapper dataMapper = new DataMapper();

    public void actualizeazaStare(String reclamatieId, String nouaStare) throws IOException {
        List<Reclamatie> reclamatii = dataMapper.incarcaReclamatii();
        for (Reclamatie reclamatie : reclamatii) {
            if (reclamatie.getId().equals(reclamatieId)) {
                reclamatie.setStare(nouaStare);
                dataMapper.salveazaReclamatie(reclamatie);
                // Logica pentru notificarea clientului
                System.out.println("Clientul a fost notificat despre schimbarea stării reclamației.");
                break;
            }
        }
    }
}
4. View
Interfața utilizator pentru vizualizarea și modificarea stării reclamațiilor.

Interfața utilizator (JavaFX)
java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReclamatieView extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField idField = new TextField();
        TextField stareField = new TextField();
        Button actualizeazaButton = new Button("Actualizează");

        actualizeazaButton.setOnAction(e -> {
            String id = idField.getText();
            String nouaStare = stareField.getText();
            ReclamatieController controller = new ReclamatieController();
            try {
                controller.actualizeazaStare(id, nouaStare);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Starea reclamației a fost actualizată.");
                alert.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Eroare la actualizarea stării.");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10, new Label("ID Reclamație:"), idField, new Label("Noua Stare:"), stareField, actualizeazaButton);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestionare Reclamații");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
