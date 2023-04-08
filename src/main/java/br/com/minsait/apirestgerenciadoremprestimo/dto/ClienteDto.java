package br.com.minsait.apirestgerenciadoremprestimo.dto;

import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import br.com.minsait.apirestgerenciadoremprestimo.model.ClienteEntity;
import br.com.minsait.apirestgerenciadoremprestimo.model.Endereco;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public record ClienteDto(
        @NotBlank String nome,
        @Pattern(regexp = "\\d{11}",message = "CPF inválido")
        String cpf,
        Endereco endereco,
        BigDecimal rendimentoMensal,
        NivelRelacionamento relacionamento
) {}
