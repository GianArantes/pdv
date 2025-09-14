package br.com.arantesrepresentacoes.pdv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arantesrepresentacoes.pdv.dto.NcmDTO;
import br.com.arantesrepresentacoes.pdv.entities.Ncm;
import br.com.arantesrepresentacoes.pdv.repositories.NcmRepository;

@RestController
@RequestMapping("/produtos/ncm")
public class NcmController {

    @Autowired
    NcmRepository ncmRepository;

    public ResponseEntity<Ncm> cadastrarNcm(@RequestBody NcmDTO ncmDTO) {
        Ncm novoNcm = new Ncm(ncmDTO.codigo());
        ncmRepository.save(novoNcm);
        return ResponseEntity.ok(novoNcm);
    }

    public ResponseEntity<Ncm> localizaPeloCodigo(String ncmCodigo) {
        Ncm ncm = ncmRepository.findNcmByCodigo(ncmCodigo);
        return ResponseEntity.ok(ncm);

    }

    public ResponseEntity<List<NcmDTO>> listarNcms() {
        List<NcmDTO> listaNcms = ncmRepository.findAll().stream().map(ncm -> new NcmDTO(ncm.getCodigo())).toList();


        return ResponseEntity.ok(listaNcms);
    }


}
