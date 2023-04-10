package br.com.minsait.apirestgerenciadoremprestimo.dto;

import br.com.minsait.apirestgerenciadoremprestimo.enuns.NivelRelacionamento;
import br.com.minsait.apirestgerenciadoremprestimo.model.Endereco;

import java.math.BigDecimal;

public record ClienteUpdateDto(
        String nome,
        Endereco endereco,
        BigDecimal rendimentoMensal,
        NivelRelacionamento relacionamento
) {}
