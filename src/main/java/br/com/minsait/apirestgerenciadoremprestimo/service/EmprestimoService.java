package br.com.minsait.apirestgerenciadoremprestimo.service;


import br.com.minsait.apirestgerenciadoremprestimo.dto.EmprestimoSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.BadRequestException;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.NotFoundException;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.model.EmprestimoEntity;
import br.com.minsait.apirestgerenciadoremprestimo.repository.ClienteRepository;
import br.com.minsait.apirestgerenciadoremprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public List<EmprestimoEntity> listar(String cpf) {
        List<EmprestimoEntity> emprestimos = emprestimoRepository.findAllByCpfCliente(cpf);
        if (emprestimos.isEmpty()) {
            throw new NotFoundException("Nenhum emprestimo encontrado para o CPF: " + cpf);
        }
        return emprestimos;
    }

    public EmprestimoEntity listarPeloId(String cpf, Long id) {
        return emprestimoRepository.findByCpfClienteAndId(cpf, id).orElseThrow(() -> new NotFoundException("Nenhum emprestimo cadastrado com o CPF: " + cpf));

    }

    public void salvar(EmprestimoSaveDto dto, String cpf) {
        ClienteEntity cliente = getCliente(cpf);
        List<EmprestimoEntity> emprestimosEncontrados = emprestimoRepository.findAllByCpfCliente(cpf);
        BigDecimal totalInicial = new BigDecimal(0);
        for (EmprestimoEntity emprestimo : emprestimosEncontrados) {
            totalInicial = totalInicial.add(emprestimo.getValorInicial());
        }
        if (totalInicial.doubleValue() > cliente.getRendimentoMensal().doubleValue() * 10) {
            throw new BadRequestException("Cliente não está apto à ter um novo emprestimo");
        }
        EmprestimoEntity emprestimo = new EmprestimoEntity(dto);
        emprestimo.setCliente(cliente);
        emprestimo.setValorFinal(calcularValorFinal(cliente.getRelacionamento(), emprestimo.getValorInicial(), emprestimosEncontrados.size()));

        emprestimoRepository.save(emprestimo);
    }

    public ClienteEntity getCliente(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Nenhum cliente cadastrado com o CPF: " + cpf));
    }

    public BigDecimal calcularValorFinal(NivelRelacionamento nivelRelacionamento, BigDecimal valorInicial, int totalEmprestimos) {

        if(totalEmprestimos < 1){
            throw new IllegalArgumentException("O cliente não possui empréstimos");
        }

        if (nivelRelacionamento.equals(NivelRelacionamento.BRONZE)) {
            return BigDecimal.valueOf(valorInicial.doubleValue() * 1.8);
        } else if (nivelRelacionamento.equals(NivelRelacionamento.PRATA)) {
            if (valorInicial.doubleValue() > 5000) {
                return BigDecimal.valueOf(valorInicial.doubleValue() * 1.4);
            }
            return BigDecimal.valueOf(valorInicial.doubleValue() * 1.6);
        } else if (nivelRelacionamento.equals(NivelRelacionamento.OURO)) {
            if (totalEmprestimos == 1) {
                return BigDecimal.valueOf(valorInicial.doubleValue() * 1.2);
            }
            return BigDecimal.valueOf(valorInicial.doubleValue() * 1.3);
        } else {
            throw new BadRequestException("Nivel Relacionamento inválido!");
        }
    }

    public void deletar(String cpf, Long id) {
        EmprestimoEntity emprestimo = emprestimoRepository.findByCpfClienteAndId(cpf, id).orElseThrow(() -> new NotFoundException("Emprestimo não encontrado"));
        emprestimoRepository.delete(emprestimo);
    }

}
