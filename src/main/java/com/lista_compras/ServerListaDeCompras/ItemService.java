package com.lista_compras.ServerListaDeCompras;

import java.util.List;

public interface ItemService {
    List<Item> listarTodos();
    Item buscarPorId(Long id);
    Item criarItem(Item item);
    Item atualizarItem(Long id, Item item);
    void removerItem(Long id);
}

