package com.desafioJava.parlamentares.domain.infrastructure.utils;

import com.desafioJava.parlamentares.domain.external.Despesas;
import com.desafioJava.parlamentares.domain.external.dto.DespesaDTO;

import java.util.ArrayList;
import java.util.List;

public class DespesasDTOFactory {

    public static List<DespesaDTO> popularDespesasDTO(List<Despesas> despesas) {

        List<DespesaDTO> listDespesaDTO = new ArrayList<>();
        List<Despesas> despesasTemp = new ArrayList<>();
        int mesAtual;
        int ultimoMes = 0;
        DespesaDTO despesaDTO = new DespesaDTO();

        for (Despesas despesa: despesas) {
            mesAtual = despesa.mes;
            if(mesAtual != ultimoMes && ultimoMes > 0) {
                despesaDTO.despesas = despesasTemp;
                listDespesaDTO.add(despesaDTO);
                despesaDTO = new DespesaDTO();
                despesasTemp = new ArrayList<>();
            }
            else {
                despesasTemp.add(despesa);
                despesaDTO.mes = despesa.mes;
                despesaDTO.total += despesa.valorDocumento;
            }
            ultimoMes = mesAtual;
        }
        despesaDTO.despesas = despesasTemp;
        listDespesaDTO.add(despesaDTO);

        return listDespesaDTO;
    }
}
