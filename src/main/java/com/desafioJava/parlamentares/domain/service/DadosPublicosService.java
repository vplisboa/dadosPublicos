package com.desafioJava.parlamentares.domain.service;

import com.desafioJava.parlamentares.domain.external.Deputados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DadosPublicosService {

    public Page<Deputados> findDeputadosPaginated(List<Deputados> deputados,Pageable pageable);
}
