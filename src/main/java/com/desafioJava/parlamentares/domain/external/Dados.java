package com.desafioJava.parlamentares.domain.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dados implements Serializable {

    public String cpf;
    public String dataFalecimento;
    public String dataNascimento;
    public String escolaridade;
    public Long id;
    public String municipioNascimento;
    public String nomeCivil;
    public String[] redeSocial;
    public String sexo;
    public String ufNascimento;
    public Status ultimoStatus;
    public String uri;
    public String urlWebsite;
}
