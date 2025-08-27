package org.acme.Processador;

import java.sql.Timestamp;

import org.acme.models.Pedido;
import org.acme.models.Produto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessadorService {
    public void processaPedido(Pedido pedido){
        Pedido pedidoExistente = Pedido.findById(pedido.id_pedido);

        Produto produto = pedidoExistente.produto;
        if (produto == null) {
            throw new IllegalStateException("Produto não associado ao pedido ID: " + pedido.id_pedido);
        }
        if (produto.quantidade_estoque < pedidoExistente.quantidade) {
            pedidoExistente.status = "FAILED";
            pedidoExistente.data_atualizacao = new Timestamp(System.currentTimeMillis());
            pedidoExistente.persist();
            return;
        }

        produto.quantidade_estoque -= pedidoExistente.quantidade;
        produto.persist();

        pedidoExistente.status = "FINISHED";
        pedidoExistente.data_atualizacao = new Timestamp(System.currentTimeMillis());
        pedidoExistente.persist();
    }
}
