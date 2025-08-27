package org.acme.Publicador;

import org.acme.models.Pedido;
import org.acme.models.PedidoDTO;
import org.acme.models.Produto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PedidoService {

    @Inject
    MessagingSend messagingSend;

    public void processarPedido(PedidoDTO pedidoDTO) {
        Produto produto = Produto.findById(pedidoDTO.produto_id());
        if(produto ==null){
            throw new IllegalArgumentException("Produto não encontrado");
        }
        Pedido pedido = new Pedido(pedidoDTO,produto);
        pedido.persist();
        messagingSend.sendPedido(pedido);
    }
}
