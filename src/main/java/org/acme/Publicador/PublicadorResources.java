package org.acme.Publicador;

import jakarta.ws.rs.Path;
import org.acme.models.PedidoDTO;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;


@Path("/pedidos")
public class PublicadorResources {

    @Inject
    PedidoService pedidoService;

    @POST
    @Transactional
    public void criarPedido(PedidoDTO pedidoDTO) {
        pedidoService.processarPedido(pedidoDTO);
    }
}
