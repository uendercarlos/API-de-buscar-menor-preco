/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristina
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
}
