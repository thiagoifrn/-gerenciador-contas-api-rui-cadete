package br.com.developer.gerenciador.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.developer.gerenciador.model.Conta;
import br.com.developer.gerenciador.repository.ContaRepository;
import br.com.developer.gerenciador.service.ContaService;
@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public List<Conta> listar(){
		return contaService.listar();
	}
	
	@GetMapping("/{contaId}")
	public ResponseEntity <Conta> buscar(@PathVariable Long contaId) {
		Optional <Conta> conta = contaRepository.findById(contaId);
		//tratando status do metodo
		if (conta.isPresent()) {			
			return ResponseEntity.ok(conta.get());
		}
		
		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Conta adicionar(@Valid @RequestBody Conta conta) {
		return  contaService.salvar(conta);
	}
	
	@PutMapping("/{contaId}")
	public ResponseEntity<Conta> atualizar(@Valid @PathVariable Long contaId,
			@RequestBody Conta conta){
		if(!contaRepository.existsById(contaId)) {
			return ResponseEntity.notFound().build();
		}
		conta.setId(contaId);
		conta = contaService.salvar(conta);
		
		return ResponseEntity.ok(conta);
	}
	
	@DeleteMapping("/{contaId}")
	public ResponseEntity<Void> remover(@PathVariable Long contaId){
		if(!contaRepository.existsById(contaId)) {
			ResponseEntity.notFound().build();
		}
		contaService.deletar(contaId);
		return ResponseEntity.noContent().build();
	}
}
