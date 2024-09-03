package com.example.demo3.Service;

import com.example.demo3.Dao.UtilisateurRepository;
import com.example.demo3.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> consulterUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur modifierUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur existingUser = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        existingUser.setNom(utilisateur.getNom());
        existingUser.setEmail(utilisateur.getEmail());
        existingUser.setMotDePasse(utilisateur.getMotDePasse());
        return utilisateurRepository.save(existingUser);
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

}