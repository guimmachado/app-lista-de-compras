package com.lista_compras.ServerListaDeCompras;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final List<Item> itens = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Item> listarTodos() {
        return itens;
    }

    @Override
    public Item buscarPorId(Long id) {
        for (Item item : itens) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Item criarItem(Item novoItem) {
        novoItem.setId(nextId++);
        itens.add(novoItem);
        return novoItem;
    }

    @Override
    public Item atualizarItem(Long id, Item atualizado) {
        Item existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(atualizado.getNome());
            existente.setQuantidade(atualizado.getQuantidade());
        }
        return existente;
    }

    @Override
    public void removerItem(Long id) {
        for (Iterator<Item> it = itens.iterator(); it.hasNext(); ) {
            if (it.next().getId().equals(id)) {
                it.remove();
                break;
            }
        }
    }
}

