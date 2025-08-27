package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_produto")
  public Long id_produto;

  public String nome;

  public int quantidade_estoque;

  public Produto() {}

  public Produto(String nome, int quantidade_estoque) {
    this.nome = nome;
    this.quantidade_estoque = quantidade_estoque;
  }
}
