package com.example.demo.Controller;

import Observer.NotificareClient;
import com.example.demo.Mapper.ComandaMapper;
import com.example.demo.Model.Comanda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comenzi")
public class ComandaController {

    private final ComandaMapper comandaMapper;

    @Autowired
    public ComandaController(ComandaMapper comandaMapper) {
        this.comandaMapper = comandaMapper;
    }

    @GetMapping
    public String getComenzi(Model model) {
        List<Comanda> comenzi = comandaMapper.loadAllComenzi();
        model.addAttribute("comenzi", comenzi);
        return "comenzi";
    }

    @PostMapping("/changeStare/{comandaId}")
    public String changeStareComanda(@PathVariable int comandaId, @RequestParam String stareNoua) {
        Comanda comanda = comandaMapper.loadComanda(comandaId);
        NotificareClient notificareClient = new NotificareClient(comanda.getClient());
        comanda.addObserver(notificareClient);
        comanda.setStare(stareNoua);
        comandaMapper.saveComanda(comanda);
        return "redirect:/comenzi";
    }

    @GetMapping("/search")
    public String searchComenzi(@RequestParam String criteriu, Model model) {
        List<Comanda> comenzi = comandaMapper.searchComenzi(criteriu);
        model.addAttribute("comenzi", comenzi);
        return "comenzi";
    }
}
