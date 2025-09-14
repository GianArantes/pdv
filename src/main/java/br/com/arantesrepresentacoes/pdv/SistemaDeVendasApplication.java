package br.com.arantesrepresentacoes.pdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.arantesrepresentacoes")
public class SistemaDeVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeVendasApplication.class, args);
	}

}
