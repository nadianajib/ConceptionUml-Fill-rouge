package com.example.demo3.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonneDto {
    private Long id;
    private String nom;
    private String email;
    // Vous pouvez ajouter ou omettre des attributs selon vos besoins
}
