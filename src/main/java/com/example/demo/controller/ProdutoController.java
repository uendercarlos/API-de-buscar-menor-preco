
package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.services.ProdutoService;
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
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.POST,value = "/admin/produto",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarProduto(@RequestBody Produto pro) {

     if(produtoService.cadastrarProduto(pro) == null){
         return new ResponseEntity(HttpStatus.BAD_REQUEST);
     }

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/admin/produto/{id}")
    ResponseEntity removerProduto(@PathVariable Long id) {

       produtoService.excluirProduto(id);
       
       return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/produto")
    void editarProduto() {
        System.out.println("edita");
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/produto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Produto> mostraProduto(@PathVariable Long id) {
        
        Produto pro;
        try {
            pro = produtoService.buscaProduto(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pro, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET,
            value = "/produto/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List <Produto>> mostraTodosProdutos(){
    
            List <Produto> pro;
        try {
            pro = produtoService.buscaTodosProdutos();

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pro, HttpStatus.OK);
    }
    
    
    
}
