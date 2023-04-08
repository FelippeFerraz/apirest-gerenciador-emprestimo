package br.com.minsait.apirestgerenciadoremprestimo.controller;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteDto;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.repository.ClienteRepository;
import br.com.minsait.apirestgerenciadoremprestimo.service.ClienteService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar (@Valid  @RequestBody ClienteDto dto){
        return service.salvar(dto);

    }
    @GetMapping ("/listar")
    public ResponseEntity<?> listar(){
        return service.listar();
    }


}
//pesquisar como transformar uma Lista de objeto x em uma Lista de objeto y.