package com.desafioJava.parlamentares.domain.external.dto;

import com.desafioJava.parlamentares.domain.external.Despesas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DespesaDTO {

    public Integer mes;
    public Long total = 0L;
    public List<Despesas> despesas;
}
