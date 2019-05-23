
package com.example.demo.controller;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.ItemVenda;
import com.example.demo.model.Produto;
import com.example.demo.model.Venda;
import com.example.demo.services.CarrinhoService;
import com.example.demo.services.ProdutoService;
import com.example.demo.services.VendaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 
  @author Alcídia Cristina
 */
@RestController
@RequestMapping(value = "/venda")
public class VendaController {
    
    @Autowired
    VendaService vendaService;
    
    @Autowired
    CarrinhoService carrinhoService;
    
    @Autowired
    ProdutoService produtoService;
    
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity cadastrarVenda(@RequestHeader(value = "Authorization") String autorizacao) {
        
        Carrinho car = carrinhoService.retornaTodoCarrinho(autorizacao);
        
        Venda v = new Venda();
        ArrayList<ItemVenda> itensVenda = new ArrayList();
        
        double valorVenda = 0;
        
        for (ItemCarrinho i : car.getItens()) {
            ItemVenda iv = new ItemVenda();
            iv.setProduto(i.getProduto());
            iv.setCusto(i.getProduto().getCusto());
            iv.setQuantidade(i.getQuantidade());
            iv.setVenda(v);
            
            valorVenda += i.getQuantidade() * i.getProduto().getPreco();
            
            itensVenda.add(iv);
            Produto prod = i.getProduto();
            prod.setQuantidade(prod.getQuantidade() - iv.getQuantidade());
            produtoService.cadastrarProduto(prod);
        }
        
        v.setItensVenda(itensVenda);
        v.setData(new Date(System.currentTimeMillis()));
        v.setCliente(car.getCliente());
        v.setValor(valorVenda);
        
        vendaService.cadastrarVenda(v);
        
        carrinhoService.limpaCarrinho(car);
        return new ResponseEntity(HttpStatus.CREATED);
        
    }
    
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerVenda(@PathVariable Long id) {
        
        vendaService.excluirVenda(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    void editarVenda() {
        System.out.println("edita");
    }
    
    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraVenda(@PathVariable Long id) {
        
        Venda ven;
        try {
            ven = vendaService.buscaVenda(id);
            
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(ven, HttpStatus.OK);
    }
    
}
