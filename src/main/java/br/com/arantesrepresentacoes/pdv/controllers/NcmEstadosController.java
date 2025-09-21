package br.com.arantesrepresentacoes.pdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.arantesrepresentacoes.pdv.dto.NcmEstadoDTO;
import br.com.arantesrepresentacoes.pdv.entities.Ncm;
import br.com.arantesrepresentacoes.pdv.entities.NcmAliquotaEstado;
import br.com.arantesrepresentacoes.pdv.repositories.NcmAliquotaEstadoRepository;
import br.com.arantesrepresentacoes.pdv.repositories.NcmRepository;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/ncms-estados")
public class NcmEstadosController {

    @Autowired
    private NcmRepository ncmRepository;
    @Autowired
    private NcmAliquotaEstadoRepository ncmAliquotaEstadoRepository;

    @GetMapping("/novo/{ncmId}")
    public String novo(@PathVariable UUID ncmId, Model model,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        Ncm ncm = ncmRepository.findById(ncmId)
                .orElseThrow(() -> new IllegalArgumentException("NCM inválido:" + ncmId));
        NcmAliquotaEstado aliquota = new NcmAliquotaEstado();
        aliquota.setNcm(ncm);
        model.addAttribute("aliquota", aliquota);
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-ncm-aliquota-estado :: content";
        }
        return "form-ncm-aliquota-estado";
    }

    @PostMapping("/salvar")
    public String salvar(NcmEstadoDTO ncmEstadoDTO) {
        Ncm ncm = ncmRepository.findById(ncmEstadoDTO.ncmId())
                .orElseThrow(() -> new IllegalArgumentException("NCM inválido:" + ncmEstadoDTO.ncmId()));
        if (ncmEstadoDTO.id() == null) {

            NcmAliquotaEstado aliquota = new NcmAliquotaEstado();
            aliquota.setNcm(ncm);
            aliquota.setEstado(ncmEstadoDTO.estado());
            aliquota.setAliquota(ncmEstadoDTO.aliquota());
            ncmAliquotaEstadoRepository.save(aliquota);
            return "redirect:/ncms/editar/" + ncm.getId();
        } else {
            Optional<NcmAliquotaEstado> aliquota = ncmAliquotaEstadoRepository.findById(ncmEstadoDTO.id());
            if (aliquota.isPresent()) {
                NcmAliquotaEstado aliquotaAtualizada = aliquota.get();
                aliquotaAtualizada.setEstado(ncmEstadoDTO.estado());
                aliquotaAtualizada.setAliquota(ncmEstadoDTO.aliquota());
                ncmAliquotaEstadoRepository.save(aliquotaAtualizada);
            }
            return "redirect:/ncms/editar/" + ncm.getId();
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        NcmAliquotaEstado aliquota = ncmAliquotaEstadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alíquota inválida:" + id));
        model.addAttribute("aliquota", aliquota);
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-ncm-aliquota-estado :: content";
        }
        return "form-ncm-aliquota-estado";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id) {
        NcmAliquotaEstado aliquota = ncmAliquotaEstadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alíquota inválida:" + id));
        ncmAliquotaEstadoRepository.delete(aliquota);
        return "redirect:/ncms/editar/" + aliquota.getNcm().getId();
    }
}
