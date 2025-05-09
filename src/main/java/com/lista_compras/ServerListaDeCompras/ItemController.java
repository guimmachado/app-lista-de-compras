package com.lista_compras.ServerListaDeCompras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> listarTodos() {
        return itemService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        Item item = itemService.buscarPorId(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Item criarItem(@RequestBody Item novoItem) {
        return itemService.criarItem(novoItem);
    }

    @PutMapping("/{id}")
    public Item atualizarItem(@PathVariable Long id, @RequestBody Item atualizado) {
        return itemService.atualizarItem(id, atualizado);
    }

    @DeleteMapping("/{id}")
    public void removerItem(@PathVariable Long id) {
        itemService.removerItem(id);
    }

    // Novo endpoint para contar itens
    @GetMapping("/count")
    public int contarItens() {
        return itemService.size();
    }
}
