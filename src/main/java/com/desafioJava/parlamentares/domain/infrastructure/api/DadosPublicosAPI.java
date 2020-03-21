package com.desafioJava.parlamentares.domain.infrastructure.api;

import com.desafioJava.parlamentares.domain.external.Deputado;
import com.desafioJava.parlamentares.domain.external.Deputados;
import com.desafioJava.parlamentares.domain.external.Despesas;
import com.desafioJava.parlamentares.domain.infrastructure.exception.DadosPublicException;
import com.desafioJava.parlamentares.domain.infrastructure.exception.IntegrationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "dadosPublicos-api", url = "https://dadosabertos.camara.leg.br/api/v2", configuration = DadosPublicosAPI.DadosPublicosErrorDecoder.class)
public interface DadosPublicosAPI {

    @RequestMapping(method = GET, value = "deputados")
    Deputados.DeputadosWrappre deputados();

    @RequestMapping(method = GET, value = "deputados/{id}")
    Deputado deputado(@PathVariable("id") final String id);

    @RequestMapping(method = GET, value = "deputados/{id}/despesas?ordem=DESC&ordenarPor=mes")
    Despesas.DespesasWrapper despesas(@PathVariable("id") final String id);

    @RequestMapping(method = GET, value = "deputados/{id}/despesas?ordem=DESC&ordenarPor=mes")
    Despesas.DespesasWrapper despesas(@PathVariable("id") final String id, @RequestParam("mes") final String mes);

    @RequestMapping(method = GET, value = "deputados/{id}/despesas?ordem=DESC&ordenarPor=mes")
    Despesas.DespesasWrapper despesas(@PathVariable("id") final String id, @RequestParam("mes") final String mes,@RequestParam("mes") final String mes2);

    @RequestMapping(method = GET, value = "deputados/{id}/despesas?ordem=DESC&ordenarPor=mes")
    Despesas.DespesasWrapper despesas(@PathVariable("id") final String id, @RequestParam("mes") final String mes,@RequestParam("mes") final String mes2,@RequestParam("mes") final String mes3);

    @RequestMapping(method = GET, value = "deputados/{id}/despesas")
    Despesas.DespesasWrapper despesasMes(@RequestParam("mes") String mes, @RequestParam("ordem") String ordem, @RequestParam("ordenarPor") String ordenarPor,@PathVariable("id") final String id);

    class DadosPublicosErrorDecoder implements ErrorDecoder {

        @Override
        public IntegrationException decode(String methodKey, Response response) {

            final HttpStatus statusCode = HttpStatus.valueOf(response.status());

            final String message = String.format("Falha de integracao com API de dados Publicos: %s, httpStatus: %d",
                    methodKey, statusCode.value());

            throw new DadosPublicException(message, statusCode);
        }
    }
}
