package br.com.arantesrepresentacoes.pdv.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record NcmEstadoDTO(UUID id, UUID ncmId, String estado, BigDecimal aliquota) {

}
