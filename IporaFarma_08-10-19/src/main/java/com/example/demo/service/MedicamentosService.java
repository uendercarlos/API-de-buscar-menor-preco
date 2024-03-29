
package com.example.demo.service;

import com.example.demo.model.Medicamentos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MedicamentosRepository;

/*
 
  @author Alcídia Cristina
 */
@Service
public class MedicamentosService {
    
     @Autowired   
    MedicamentosRepository produtoRepository;
    
    public Medicamentos cadastrarProduto(Medicamentos pro) {
       return produtoRepository.save(pro);
    }

    public Medicamentos editarProduto(Medicamentos pro) {
       return produtoRepository.save(pro);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Medicamentos buscaProduto(Long id) {
        return produtoRepository.findById(id).get();
    }
    
    public List<Medicamentos> buscaTodosProdutos(){
        return produtoRepository.findAll();
        
    }
    
     public List<Medicamentos> ordenarPorMenorPreco(String nome, String principioAtivo){
        
    
        return produtoRepository.findByNomeOrPrincipioAtivoOrderByPrecoAsc(nome, principioAtivo);
        
    }
    
}
