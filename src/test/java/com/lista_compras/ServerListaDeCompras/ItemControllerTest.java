package com.lista_compras.ServerListaDeCompras;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    void listarTodosTest() throws Exception {
        List<Item> itens = Arrays.asList(new Item(1L, "Arroz", 2), new Item(2L, "Feijão", 3));
        when(itemService.listarTodos()).thenReturn(itens);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/itens"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Arroz"));
    }

    @Test
    void buscarPorIdTest() throws Exception {
        Item item = new Item(1L, "Arroz", 2);
        when(itemService.buscarPorId(1L)).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/itens/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Arroz"));
    }

    @Test
    void criarItemTest() throws Exception {
        Item item = new Item(1L, "Arroz", 2);
        when(itemService.criarItem(any(Item.class))).thenReturn(item);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/itens")
                        .contentType("application/json")
                        .content("{\"nome\":\"Arroz\",\"quantidade\":2}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Arroz"));
    }

    @Test
    void atualizarItemTest() throws Exception {
        Item atualizado = new Item(1L, "Arroz Atualizado", 5);
        when(itemService.atualizarItem(eq(1L), any(Item.class))).thenReturn(atualizado);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/itens/1")
                        .contentType("application/json")
                        .content("{\"nome\":\"Arroz Atualizado\",\"quantidade\":5}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Arroz Atualizado"));
    }

    @Test
    void removerItemTest() throws Exception {
        doNothing().when(itemService).removerItem(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/itens/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
