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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Despesas implements Serializable {

    public Integer ano;
    public String cnpjCpfFornecedor;
    public Long codDocumento;
    public Long codLote;
    public Long codTipoDocumento;
    public String dataDocumento;
    public Integer mes;
    public String nomeFornecedor;
    public String numDocumento;
    public String numRessarcimento;
    public Integer parcela;
    public String tipoDespesa;
    public String tipoDocumento;
    public String urlDocumentopublic;
    public Long valorDocumento;
    public Long valorGlosa;
    public Long valorLiquido;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DespesasWrapper {

        @JsonProperty("dados")
        private List<Despesas> despesas;
    }
}
