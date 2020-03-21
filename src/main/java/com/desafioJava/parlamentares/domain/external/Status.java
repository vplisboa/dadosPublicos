package com.desafioJava.parlamentares.domain.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable {

    public String condicaoEleitoral;
    public String data;
    public String descricaoStatus;
    public String email;
    public Gabinete gabinete;
    public Long id;
    public Long idLegislatura;
    public String nome;
    public String nomeEleitoral;
    public String siglaPartido;
    public String siglaUf;
    public String situacao;
    public String uri;
    public String uriPartido;
    public String urlFoto;
}
