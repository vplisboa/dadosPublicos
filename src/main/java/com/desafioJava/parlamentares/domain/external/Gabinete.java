package com.desafioJava.parlamentares.domain.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class Gabinete {

    public String andar;
    public String email;
    public String nome;
    public String predio;
    public String sala;
    public String telefone;
}
