/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Alcídia Cristina
 */
@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    

    public void cadastrarCategoria(Categoria cat) {
        categoriaRepository.save(cat);
    }

    public Categoria editarCategoria(Categoria cat) {
        return categoriaRepository.save(cat);
    }

    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    

    public Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id).get();

    }
    
   
    public List<Categoria> buscarTodasCategorias(){
        return categoriaRepository.findAll();
    }
    


}
