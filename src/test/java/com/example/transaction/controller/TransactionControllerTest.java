package com.example.transaction.controller;

import com.example.transaction.dto.TransactionDTO;
import com.example.transaction.mapper.TransactionMapper;
import com.example.transaction.model.Transaction;
import com.example.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private TransactionMapper transactionMapper;

    @Test
    public void testGetTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountFrom(111111111L);

        when(transactionMapper.toDto(transaction)).thenReturn(transactionDTO);
        when(transactionService.exceedingLimits(111111111L)).thenReturn(transactionDTO);

        mockMvc.perform(get("/api/transactions/limitExceeded/111111111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountFrom").value(111111111));
    }
}