package br.com.minsait.apirestgerenciadoremprestimo.service;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteUpdateDto;
import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.BadRequestException;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.NotFoundException;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteEntity> listar() {
        List<ClienteEntity> clientes = repository.findAll();
        if (clientes.isEmpty()) {
            throw new NotFoundException("Nenhum cliente encontrado!");
        }
        return clientes;
    }

    public ClienteEntity listarPeloId(String cpf) {
        return getCliente(cpf);

    }

    public ClienteEntity salvar(ClienteSaveDto dto) {
        if (repository.existsByCpf(dto.cpf())) {
            throw new BadRequestException("CPF já exstente");
        }
        validationRelacionamento(dto.relacionamento());
        ClienteEntity cliente = new ClienteEntity(dto);
        return repository.save(cliente);
    }

    public ClienteEntity atualizar(String cpf, ClienteUpdateDto dto) {
        ClienteEntity cliente = getCliente(cpf);
        cliente.update(dto);
        return repository.save(cliente);
    }

    public void deletar(String cpf) {
        ClienteEntity cliente = getCliente(cpf);
        repository.delete(cliente);
    }


    public ClienteEntity getCliente(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf));
    }

    public void validationRelacionamento(NivelRelacionamento nivelRelacionamento) {
        if (nivelRelacionamento != null && !(nivelRelacionamento.equals(BRONZE) || nivelRelacionamento.equals(PRATA) || nivelRelacionamento.equals(OURO))) {
            throw new BadRequestException("Nivel de relacionamento inválido!");
        }
    }

}
