
package com.example.demo.controller;



import com.example.demo.model.Cliente;
import com.example.demo.config.Autenticacao;
import com.example.demo.services.ClienteService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cristina
 */
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;
    

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarCliente(@RequestBody Cliente cli) {

        clienteService.cadastrarCliente(cli);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    
    @RequestMapping(method = RequestMethod.POST,value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity autenticar(@RequestBody Cliente cli) {

        Cliente cliAuth = clienteService.autenticarCliente(cli);

        if (cliAuth == null || cliAuth.getEmail().equals("") || cliAuth.getSenha().equals("")) {
            return new ResponseEntity<>(cliAuth, HttpStatus.FORBIDDEN);
        }
      

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(cliAuth.getNome());
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        jwtBuilder.signWith(Autenticacao.key);

        String token = jwtBuilder.compact();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return new ResponseEntity<>("Bearer " + token, HttpStatus.OK);

    }



  

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerCliente(@PathVariable Long id) {

       clienteService.excluirCliente(id);
       
       return new ResponseEntity(HttpStatus.OK);
    }
	
	
	
	
	

    @RequestMapping(method = RequestMethod.PUT)
    void editarCliente() {
        System.out.println("edita");
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> mostraCliente(@PathVariable Long id) {
        
        Cliente cli;
        try {
            cli = clienteService.buscaCliente(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(cli, HttpStatus.OK);
    }
    
}
