package br.com.minsait.apirestgerenciadoremprestimo.controller;

import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.ClienteUpdateDto;
import br.com.minsait.apirestgerenciadoremprestimo.dto.EmprestimoSaveDto;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.model.EmprestimoEntity;
import br.com.minsait.apirestgerenciadoremprestimo.repository.ClienteRepository;
import br.com.minsait.apirestgerenciadoremprestimo.service.ClienteService;
import br.com.minsait.apirestgerenciadoremprestimo.service.EmprestimoService;
import br.com.minsait.apirestgerenciadoremprestimo.utils.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<?> listarClientes() {
        List<ClienteEntity> clientes = clienteService.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> listarClientesPeloId(@PathVariable String cpf) {
        ClienteEntity clientes = clienteService.listarPeloId(cpf);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteEntity> cadastrarCliente(@Valid @RequestBody ClienteSaveDto dto) {
        ClienteEntity cliente = clienteService.salvar(dto);
        return ResponseEntity.created(URI.create(String.format("/clientes/%s", cliente.getCpf()))).body(cliente);
    }



    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteEntity> atualizarCliente(@PathVariable String cpf, @Valid @RequestBody ClienteUpdateDto dto) {
        ClienteEntity cliente = clienteService.atualizar(cpf, dto);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deletarCliente(@PathVariable String cpf) {
        clienteService.deletar(cpf);
        return ResponseEntity.ok().build();
    }

    // EMPRESTIMOS

    @GetMapping("/{cpf}/emprestimos")
    public ResponseEntity<?> listar(@PathVariable String cpf) {
        List<EmprestimoEntity> emprestimos = emprestimoService.listar(cpf);
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/{cpf}/emprestimos/{id}")
    public ResponseEntity<?> listarEmprestimosPeloId(@PathVariable String cpf, @PathVariable Long id) {
        EmprestimoEntity emprestimo = emprestimoService.listarPeloId(cpf, id);
        return ResponseEntity.ok(emprestimo);
    }

    @PostMapping("/{cpf}/emprestimos")
    public ResponseEntity<?> cadastrarEmprestimo(@Valid @RequestBody EmprestimoSaveDto dto, @PathVariable String cpf) {
        emprestimoService.salvar(dto, cpf);
        return ResponseEntity.ok().body(new ResponseModel(200, "Emprestimo cadastrado com sucesso!"));
    }

    @DeleteMapping("/{cpf}/emprestimos/{id}")
    public ResponseEntity<?> cadastrarEmprestimo(@PathVariable String cpf, @PathVariable Long id) {
        emprestimoService.deletar(cpf, id);
        return ResponseEntity.ok().body(new ResponseModel(200, "Emprestimo cadastrado com sucesso!"));
    }

}

