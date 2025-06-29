package com.incendios.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incendios.api.model.Incendio;
import com.incendios.api.repository.IncendioRepository;

@RestController
@RequestMapping("/api/incendios")
@CrossOrigin(origins = "*")
public class IncendioController {

    private final IncendioRepository repo;

    public IncendioController(IncendioRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Incendio> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Incendio crear(@RequestBody Incendio i) {
        return repo.save(i);
    }

    @PutMapping("/{id}")
    public Incendio actualizar(@PathVariable Long id, @RequestBody Incendio nuevo) {
        return repo.findById(id).map(i -> {
            i.setNombre(nuevo.getNombre());
            i.setUbicacion(nuevo.getUbicacion());
            i.setSeveridad(nuevo.getSeveridad());
            i.setEstado(nuevo.getEstado());
            i.setFecha(nuevo.getFecha());
            return repo.save(i);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
