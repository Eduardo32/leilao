package br.com.alura.leilao.util.builder;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoBuiler {

    private String nome;
    private BigDecimal valorInicial;
    private Usuario usuario;
    private LocalDate data;

    public LeilaoBuiler comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuiler comValorInicial(String valorInicial) {
        this.valorInicial = new BigDecimal(valorInicial);
        return this;
    }

    public LeilaoBuiler comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public LeilaoBuiler comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public Leilao build() {
        return new Leilao(this.nome, this.valorInicial, this.data, this.usuario);
    }
}
