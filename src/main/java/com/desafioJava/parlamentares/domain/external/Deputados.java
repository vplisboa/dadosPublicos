package com.desafioJava.parlamentares.domain.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deputados implements Serializable {

    public Long id;
    public String uri;
    public String nome;
    public String siglaPartido;
    public String uriPartido;
    public String siglaUf;
    public Long idLegislatura;
    public String urlFoto;
    public String email;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeputadosWrappre {

        @JsonProperty("dados")
        private List<Deputados> deputados;
    }
}
