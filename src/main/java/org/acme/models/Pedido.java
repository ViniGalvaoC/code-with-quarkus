package org.acme.models;

import java.sql.Timestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "pedidos")
public class Pedido extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id_pedido;

    public int cliente_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    public Produto produto;

    public int quantidade;

    public String status;

    public Timestamp data_criacao;

    public Timestamp data_atualizacao;

    public Pedido() {
    }

    public Pedido(PedidoDTO pedidoDTO, Produto produto) {
        this.cliente_id = pedidoDTO.cliente_id();
        this.produto = produto;
        this.quantidade = pedidoDTO.quantidade();
        this.status = "PENDING";
        this.data_criacao = new Timestamp(System.currentTimeMillis());
    }
}
