package br.com.minsait.apirestgerenciadoremprestimo.dto;

import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import br.com.minsait.apirestgerenciadoremprestimo.model.Endereco;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record ClienteSaveDto(
        @NotBlank(message = "Nome é obrigatório") String nome,

        @Pattern(regexp = "\\d{11}", message = "Telefone inválido")
        String telefone,

        @Pattern(regexp = "\\d{11}", message = "CPF inválido")
        String cpf,

        @Valid Endereco endereco,

        @NotNull(message = "Rendimento é obrigatorio")
        @Positive(message = "Rendimento deve ser maior que 0")
        BigDecimal rendimentoMensal,

        @Enumerated(EnumType.STRING)
        NivelRelacionamento relacionamento
) {
}
