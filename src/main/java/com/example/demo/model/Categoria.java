
package com.example.demo.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/*
 
  @author Alcídia Cristina
 */
@Entity
public class Categoria {// 

    private Long id;
    private String nome;
   // private List<Produto> produtos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    @OneToMany(mappedBy = "categoria")
//    public List<Produto> getProdutos() {
//       return produtos;
//    }
//
//    public void setProdutos(List<Produto> produtos) {
//        this.produtos = produtos;
//    }

}
