package br.com.minsait.apirestgerenciadoremprestimo.dto;

import java.math.BigDecimal;


public record EmprestimoSaveDto(
        BigDecimal valorInicial,
        BigDecimal valorFinal,
        String dataInicial,
        String dataFinal
) {}
