package org.acme.Processador;

import java.util.concurrent.CompletionStage;

import org.acme.models.Pedido;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MessagingReceive {

    @Inject
    ProcessadorService processadorService;

    @Incoming("pedidos-in")
    @Transactional
    public CompletionStage<Void> receivePedido(Message<Pedido> message) {
        // Pega o ID da mensagem recebida
        Pedido pedido = message.getPayload();

    try {
        processadorService.processaPedido(pedido);
        // Processou OK, confirma a mensagem
        return message.ack();
    } catch (Exception e) {
        // Log do erro
        System.err.println("Erro ao processar pedido: " + e.getMessage());
        e.printStackTrace();

        // Não chama ack, para que a mensagem fique pendente para retry

        // Opcional: se quiser rejeitar a mensagem e não tentar retry,
        // pode usar message.nack(e) para negativar e possivelmente enviar para DLQ.
        return message.nack(e);
    }
    }
}
    