package com.desafioJava.parlamentares.domain.service.impl;

import com.desafioJava.parlamentares.domain.external.Deputados;
import com.desafioJava.parlamentares.domain.infrastructure.api.DadosPublicosAPI;
import com.desafioJava.parlamentares.domain.service.DadosPublicosService;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@NoArgsConstructor
public class DadosPublicosServiceImpl implements DadosPublicosService {

    @Override
    public Page<Deputados> findDeputadosPaginated(List<Deputados> deputados,Pageable pageable) {

        int pageSize = 5;
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Deputados> list;

        if (deputados.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, deputados.size());
            list = deputados.subList(startItem, toIndex);
        }

        Page<Deputados> deputadosPage = new PageImpl<Deputados>(list, PageRequest.of(currentPage, pageSize), deputados.size());

        return deputadosPage;
    }
}
