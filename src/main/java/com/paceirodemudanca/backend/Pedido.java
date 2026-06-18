package com.paceirodemudanca.backend;

public class Pedido {

    private String origem;
    private String destino;
    private String servico;

    public Pedido(String origem, String destino, String servico) {
        this.origem = origem;
        this.destino = destino;
        this.servico = servico;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getServico() {
        return servico;
    }
}