package org.acme.Processador;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.concurrent.CompletionStage;
import org.acme.models.Pedido;
import org.eclipse.microprofile.reactive.messaging.*;

@ApplicationScoped
public class MessagingReceive {

  @Inject ProcessadorService processadorService;

  @Incoming("pedidos-in")
  @Transactional
  public CompletionStage<Void> receivePedido(Message<Pedido> message) {

    Pedido pedido = message.getPayload();

    try {
      processadorService.processaPedido(pedido);
      return message.ack();
    } catch (Exception e) {
      System.err.println("Erro ao processar pedido: " + e.getMessage());
      e.printStackTrace();
      return message.nack(e);
    }
  }
}
