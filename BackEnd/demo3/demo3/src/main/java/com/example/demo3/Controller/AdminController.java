package com.example.demo3.Controller;

import com.example.demo3.Service.IAdminService;
import com.example.demo3.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;

    @Autowired
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        return adminService.ajouterUtilisateur(utilisateur);
    }

    @GetMapping("/all")
    public List<Utilisateur> consulterUtilisateurs() {
        return adminService.consulterUtilisateurs();
    }

    @PutMapping("/utilisateurs/{id}")
    public Utilisateur modifierUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return adminService.modifierUtilisateur(id, utilisateur);
    }

    @DeleteMapping("/utilisateurs/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
        adminService.supprimerUtilisateur(id);
    }

}