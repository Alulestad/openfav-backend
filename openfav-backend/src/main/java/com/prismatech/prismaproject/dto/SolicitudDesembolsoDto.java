package com.prismatech.prismaproject.dto;

import lombok.Data;

@Data
public class SolicitudDesembolsoDto {
    private Integer idDescm;
    private Integer idPres;
    private String documentoDescm;
    private String estadoDescm;
}
