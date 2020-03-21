package com.desafioJava.parlamentares.domain.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Deputado implements Serializable {

    public Link[] links;
    public Dados dados;

}
