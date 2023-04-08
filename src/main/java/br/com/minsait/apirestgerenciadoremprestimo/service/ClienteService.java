package br.com.minsait.apirestgerenciadoremprestimo.service;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteDto;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity<?>salvar(ClienteDto dto){
        if (repository.existsByCpf(dto.cpf())){
            return ResponseEntity.badRequest().body("CPF já exstente");
        }
        ClienteEntity cliente = new ClienteEntity(dto);
        repository.save(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    public ResponseEntity<?>listar(){
        List<ClienteEntity> clientes = repository.findAll();
        return  ResponseEntity.ok().body(clientes);
    }
}
