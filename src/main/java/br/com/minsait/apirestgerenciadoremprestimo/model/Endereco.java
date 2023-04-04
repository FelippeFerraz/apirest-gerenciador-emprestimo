package br.com.minsait.apirestgerenciadoremprestimo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Endereco {

    private String rua;
    private Integer numero;
    private String cep;


    public Endereco(String rua, int numero, String cep){

        this.rua = rua;
        this.numero = numero;
        this.cep = cep;

    }
}
