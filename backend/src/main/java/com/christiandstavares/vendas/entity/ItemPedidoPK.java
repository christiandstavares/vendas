package com.christiandstavares.vendas.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ItemPedidoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedidoPK that = (ItemPedidoPK) o;

        if (produto != null ? !produto.equals(that.produto) : that.produto != null) return false;
        return pedido != null ? pedido.equals(that.pedido) : that.pedido == null;
    }

    @Override
    public int hashCode() {
        int result = produto != null ? produto.hashCode() : 0;
        result = 31 * result + (pedido != null ? pedido.hashCode() : 0);
        return result;
    }
}
