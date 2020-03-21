package com.desafioJava.parlamentares.application.controller;

import com.desafioJava.parlamentares.domain.external.Deputado;
import com.desafioJava.parlamentares.domain.external.Deputados;
import com.desafioJava.parlamentares.domain.external.Despesas;
import com.desafioJava.parlamentares.domain.external.dto.DespesaDTO;
import com.desafioJava.parlamentares.domain.infrastructure.api.DadosPublicosAPI;
import com.desafioJava.parlamentares.domain.infrastructure.utils.CacheManager;
import com.desafioJava.parlamentares.domain.infrastructure.utils.DespesasDTOFactory;
import com.desafioJava.parlamentares.domain.service.DadosPublicosService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Slf4j
@Controller
public class ParlamentaresController {

    private DadosPublicosAPI dadosPublicosAPI;

    @Autowired
    private DadosPublicosService dadosPublicosService;

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping("/")
    public String listarParlamentares(@RequestParam("page") Optional<Integer> page, Model model) {
        int currentPage = page.orElse(1);
        List<Deputados> deputados = dadosPublicosAPI.deputados().getDeputados();
        populateCache(deputados);
        Page<Deputados> deputadosPage = dadosPublicosService.findDeputadosPaginated(deputados, PageRequest.of(currentPage -1 ,5));
        model.addAttribute("deputadospage", deputadosPage);
        int totalPages = deputadosPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("cache",cacheManager.cache);

        return "parlamentaresList";

    }

    @RequestMapping("/parlamentar")
    public String informacoesParlamentar(@RequestParam("id") final Long id,@RequestParam("partido") final String partido,
                                         @RequestParam("foto") final String foto, Model model) {

        Deputado deputado = dadosPublicosAPI.deputado(id.toString());
        List<Despesas> despesas;
        Long numeroVizualizacoes = cacheManager.cache.getIfPresent(id);


        int mes = LocalDate.now().getMonthValue();
        if(mes == 1) {
            despesas = dadosPublicosAPI.despesas(id.toString(), String.valueOf(mes)).getDespesas();
        } else if (mes == 2) {
            despesas = dadosPublicosAPI.despesas(id.toString(), String.valueOf(mes),String.valueOf(mes-1)).getDespesas();
        } else {
            despesas = dadosPublicosAPI.despesas(id.toString(), String.valueOf(mes),String.valueOf(mes-1),String.valueOf(mes-2)).getDespesas();
        }

        cacheManager.populateCache(id,numeroVizualizacoes+1);
        List<DespesaDTO> teste = DespesasDTOFactory.popularDespesasDTO(despesas);

        model.addAttribute("deputado",deputado.dados);
        model.addAttribute("partido",partido);
        model.addAttribute("despesasDeputado",teste);
        model.addAttribute("foto",foto);

        return "parlamentar";
    }

    @RequestMapping("/despesa")
    public String informacoesDespesa(@RequestParam("mes") final String mes, @RequestParam("id") final Long id, Model model,
                                     @RequestParam("foto") final String foto,@RequestParam("partido") final String partido) {
        List<Despesas> despesas = dadosPublicosAPI.despesasMes(mes,"DESC","dataDocumento",id.toString()).getDespesas();

        model.addAttribute("despesaMes",despesas);
        model.addAttribute("partido",partido);
        model.addAttribute("foto",foto);
        model.addAttribute("id",id);
        return "despesas";
    }

    public void populateCache(List<Deputados> deputados) {
        for (Deputados deputado: deputados) {
            if(cacheManager.cache.getIfPresent(deputado.id) == null)
                cacheManager.populateCache(deputado.id,0L);
        }
    }
}
