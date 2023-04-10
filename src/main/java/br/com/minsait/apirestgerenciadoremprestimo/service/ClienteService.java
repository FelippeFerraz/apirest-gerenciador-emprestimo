package br.com.minsait.apirestgerenciadoremprestimo.service;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteUpdateDto;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.BadRequestException;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.NotFoundException;
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

    public List<ClienteEntity> listar() {
        List<ClienteEntity> clientes = repository.findAll();
        if(clientes.isEmpty()) {
            throw new NotFoundException("Nenhum cliente encontrado!");
        }
        return clientes;
    }

    public ClienteEntity listarPeloId(String cpf) {
        ClienteEntity cliente = repository.findByCpf(cpf).orElseThrow(() -> {
            throw new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf);
        });
        return cliente;
    }

    public void salvar(ClienteSaveDto dto){
        if (repository.existsByCpf(dto.cpf())){
            throw new BadRequestException("CPF já exstente");
        }
        ClienteEntity cliente = new ClienteEntity(dto);
        repository.save(cliente);
    }

    public void atualizar(String cpf, ClienteUpdateDto dto){
        ClienteEntity cliente = repository.findByCpf(cpf).orElseThrow(() -> {
            throw new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf);
        });
        cliente.update(dto);
        repository.save(cliente);
    }

    public void deletar(String cpf) {
        ClienteEntity cliente = repository.findByCpf(cpf).orElseThrow(() -> {
            throw new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf);
        });
        repository.delete(cliente);
    }

}
