
package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.model.Endereco;
import com.example.demo.model.Medicamentos;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.EnderecoService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 
  @author Alcídia Cristina
 */
@RestController
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/admin/cadastrarcategoria", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarCategoria(@RequestBody Categoria cat) {

        categoriaService.cadastrarCategoria(cat);

        return new ResponseEntity(HttpStatus.CREATED);

    }
/*
    @RequestMapping(method = RequestMethod.POST,value = "/admin/categoria", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarCategoria(@RequestBody Categoria cat) {

        categoriaService.cadastrarCategoria(cat);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    */
    
    
    
    
    

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/categoria/{id}")
    ResponseEntity removerCategoria(@PathVariable Long id) {

       categoriaService.excluirCategoria(id);
       
       return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    void editarCategoria() {
        System.out.println("edita");
    }

    
     
    
   
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/categoria/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity <List<Medicamentos>> mostraCategoria(@PathVariable Long id) {
        
        Categoria cat = new Categoria();
        cat.setId(id);
        List<Medicamentos> produtos;
        
        try {
            produtos = categoriaService.buscaTodosProdutosCategoria(cat);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(produtos, HttpStatus.OK);
    }
    
    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List <Categoria>> mostraTodasCategorias(){
    
            List <Categoria> cat;
        try {
            cat = categoriaService.buscaTodasCategorias();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(cat, HttpStatus.OK);
    }
    
    
    
}
