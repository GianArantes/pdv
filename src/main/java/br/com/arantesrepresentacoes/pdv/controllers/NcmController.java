package br.com.arantesrepresentacoes.pdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.arantesrepresentacoes.pdv.entities.Ncm;
import br.com.arantesrepresentacoes.pdv.entities.NcmAliquotaEstado;
import br.com.arantesrepresentacoes.pdv.repositories.NcmAliquotaEstadoRepository;
import br.com.arantesrepresentacoes.pdv.repositories.NcmRepository;

@Controller
@RequestMapping("/ncms")
public class NcmController {

    @Autowired
    private NcmRepository ncmRepository;

    @Autowired
    private NcmAliquotaEstadoRepository ncmAliquotaEstadoRepository;

    @GetMapping("/listar")
    public String listar(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("ncms", ncmRepository.findAll());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "lista-ncms :: content";
        }
        return "lista-ncms";
    }

    @GetMapping("/novo")
    public String novo(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("ncm", new Ncm());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-ncm :: content";
        }
        return "form-ncm";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        Ncm ncm = ncmRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("NCM inválido:" + id));
        model.addAttribute("ncm", ncm);

        List<NcmAliquotaEstado> aliquotas = ncmAliquotaEstadoRepository.findByNcm(ncm);
        aliquotas.sort((a, b) -> a.getEstado().compareTo(b.getEstado()));
        model.addAttribute("aliquotas", aliquotas);
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-ncm :: content";
        }
        return "form-ncm";
    }

    @PostMapping("/salvar")
    public String salvar(Ncm ncm) {
        ncmRepository.save(ncm);
        return "redirect:/ncms/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id) {
        ncmRepository.deleteById(id);
        return "redirect:/ncms/listar";
    }
}
