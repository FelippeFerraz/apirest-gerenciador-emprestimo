package br.com.minsait.apirestgerenciadoremprestimo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.minsait.apirestgerenciadoremprestimo.dto.EmprestimoSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.BadRequestException;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.NotFoundException;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.model.EmprestimoEntity;
import br.com.minsait.apirestgerenciadoremprestimo.repository.ClienteRepository;
import br.com.minsait.apirestgerenciadoremprestimo.repository.EmprestimoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    

    public List<EmprestimoEntity> listar(String cpf) {
        List<EmprestimoEntity> emprestimos = emprestimoRepository.findAllByCpfCliente(cpf);
        if(emprestimos.isEmpty()) {
            throw new NotFoundException("Nenhum emprestimo encontrado para o CPF: " + cpf);
        }
        return emprestimos;
    }

    public EmprestimoEntity listarPeloId(String cpf, Long id) {
        return emprestimoRepository.findByCpfClienteAndId(cpf, id).orElseThrow(() -> {
            throw new NotFoundException("Nenhum emprestimo cadastrado com o CPF: " + cpf);
        });

    }

    public void salvar(EmprestimoSaveDto dto, String cpf){
        ClienteEntity cliente = clienteRepository.findByCpf(cpf).orElseThrow(() -> {
            throw new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf);
        });
        EmprestimoEntity emprestimo = new EmprestimoEntity(dto);
        emprestimo.setCliente(cliente);
        emprestimoRepository.save(emprestimo);
    }

    // public void atualizar(String cpf, ClienteUpdateDto dto){
    //     ClienteEntity cliente = repository.findByCpf(cpf).orElseThrow(() -> {
    //         throw new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf);
    //     });
    //     cliente.update(dto);
    //     repository.save(cliente);
    // }

    // public void deletar(String cpf) {
    //     ClienteEntity cliente = repository.findByCpf(cpf).orElseThrow(() -> {
    //         throw new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf);
    //     });
    //     repository.delete(cliente);
    // }

}
