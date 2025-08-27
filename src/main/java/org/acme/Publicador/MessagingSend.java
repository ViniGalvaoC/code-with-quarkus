package org.acme.Publicador;

import org.acme.models.Pedido;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessagingSend {

    @Inject
    @Channel("pedidos-out")
    Emitter<Pedido> emitter;

    public void sendPedido(Pedido pedido) {
        emitter.send(pedido);
    }
}
