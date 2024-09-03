package com.example.demo3.Service;


import com.example.demo3.model.Utilisateur;
import org.apache.catalina.Service;

import java.util.List;

public interface IAdminService {
    Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
    List<Utilisateur> consulterUtilisateurs();
    Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur);
    void supprimerUtilisateur(Long id);


}