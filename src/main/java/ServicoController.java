package com.paceirodemudanca.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServicoController {

    private List<Pedido> pedidos = new ArrayList<>();

    @GetMapping("/")
    public String inicio() {
        return "Backend funcionando!";
    }

    @GetMapping("/usuario")
    public String usuario() {
        return "Cliente conectado";
    }

    @PostMapping("/pedido")
    public String pedido(
            @RequestParam String origem,
            @RequestParam String destino,
            @RequestParam String servico
    ) {

        Pedido novoPedido = new Pedido(origem, destino, servico);
        pedidos.add(novoPedido);

        return "Pedido recebido com sucesso!";
    }

    @GetMapping("/pedidos")
    public List<Pedido> listarPedidos() {
        return pedidos;
    }

    @GetMapping("/pedidos/transportador")
    public List<Pedido> listarPedidosTransportador() {

        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos) {

            if (pedido.getServico().equalsIgnoreCase("Transporte")
                    || pedido.getServico().equalsIgnoreCase("Mudança Completa")) {

                resultado.add(pedido);
            }
        }

        return resultado;
    }

    @GetMapping("/pedidos/ajudante")
    public List<Pedido> listarPedidosAjudante() {

        List<Pedido> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos) {

            if (pedido.getServico().equalsIgnoreCase("Ajudante")
                    || pedido.getServico().equalsIgnoreCase("Mudança Completa")) {

                resultado.add(pedido);
            }
        }

        return resultado;
    }
}