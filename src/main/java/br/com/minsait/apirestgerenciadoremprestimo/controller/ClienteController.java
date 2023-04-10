package br.com.minsait.apirestgerenciadoremprestimo.controller;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteUpdateDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.EmprestimoSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.TesteSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.exceptions.NotFoundException;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.model.EmprestimoEntity;
import br.com.minsait.apirestgerenciadoremprestimo.projections.ClienteProjection;
import br.com.minsait.apirestgerenciadoremprestimo.repository.ClienteRepository;
import br.com.minsait.apirestgerenciadoremprestimo.service.ClienteService;
import br.com.minsait.apirestgerenciadoremprestimo.service.EmprestimoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private ClienteRepository clienteRepository;

    // CLIENTES

    @GetMapping
    public ResponseEntity<?> listarClientes(){
        List<ClienteEntity> clientes = clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping ("/{cpf}")
    public ResponseEntity<?> listarClientesPeloId(@PathVariable String cpf) {
        ClienteEntity clientes = clienteService.listarPeloId(cpf);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente (@Valid  @RequestBody ClienteSaveDto dto){
        clienteService.salvar(dto);
        return ResponseEntity.ok().body("Cliente cadastrado com sucesso!");
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> atualizarCliente (@PathVariable String cpf, @Valid  @RequestBody ClienteUpdateDto dto){
        clienteService.atualizar(cpf, dto);
        return ResponseEntity.ok().body("Cliente atualizado com sucesso!");
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deletarCliente (@PathVariable String cpf){
        clienteService.deletar(cpf);
        return ResponseEntity.ok().body("Cliente deletado com sucesso!");
    }

    // EMPRESTIMOS

    @GetMapping("/{cpf}/emprestimos")
    public ResponseEntity<?> listar(@PathVariable String cpf){
        List<EmprestimoEntity> emprestimos = emprestimoService.listar(cpf);
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping ("/{cpf}/emprestimos/{id}")
    public ResponseEntity<?> listarEmprestimosPeloId(@PathVariable String cpf, @PathVariable Long id) {
        EmprestimoEntity emprestimo = emprestimoService.listarPeloId(cpf, id);
        return ResponseEntity.ok(emprestimo);
    }

    @PostMapping("/{cpf}/emprestimos")
    public ResponseEntity<?> cadastrarEmprestimo (@Valid  @RequestBody EmprestimoSaveDto dto, @PathVariable String cpf){
        emprestimoService.salvar(dto, cpf);
        return ResponseEntity.ok().body("Emprestimo cadastrado com sucesso!");
    }
    @GetMapping("/teste")
    public ClienteProjection teste() {
        return clienteRepository.fazendoTest("Lucas Baiano").orElseThrow(() -> {
            throw new NotFoundException("Nenhum cliente cadastrado com o nome: \"Lucas Baiano\"");
        });
    }




}
