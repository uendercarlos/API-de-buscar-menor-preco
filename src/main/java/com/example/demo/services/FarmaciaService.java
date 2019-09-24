/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Farmacia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.FarmaciaRepository;

/**
 *
 * @author Alcídia Cristina
 */
@Service
public class FarmaciaService {
    
    @Autowired   
    FarmaciaRepository farmaciaRepository;

    public Farmacia autenticarAdministrador(Farmacia adm) {
        return farmaciaRepository.findByLoginAndSenha(adm.getLogin(),adm.getSenha());
        
    }
    
}
