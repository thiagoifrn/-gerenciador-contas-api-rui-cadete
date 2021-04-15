package br.com.developer.gerenciador.service;

import java.util.List;

import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.developer.gerenciador.model.Conta;
import br.com.developer.gerenciador.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	public List<Conta> listar() {
		return contaRepository.findAll();
	}
	
	public Conta salvar(Conta conta) {
		try {
			contaRepository.save(conta);
			return conta;
		} catch (DataIntegrityViolationException e) {
			
			throw new IllegalAddException("Formato de data Inváldo");
		}
	}
	
//	public Conta atualizar(Long id) {
//		Conta conta = contaRepository.findById(id).get();
//		conta.setId(conta.getId());
//		conta.setDescricao(conta.getDescricao());
//		conta.setDataVencimento(conta.getDataVencimento());
//		conta.setValor(conta.getValor());
//		conta.setPago(conta.getPago());
//		try {
//			contaRepository.save(conta);
//			return conta;
//		} catch (DataIntegrityViolationException e) {
//			throw new IllegalAddException("Formato de data Inváldo");
//		}
//	}
	
	public void deletar(Long id) {
		try {
			if(contaRepository.findById(id).isPresent()) {
				contaRepository.deleteById(id);
			}
		} catch (Exception e) {
			throw new IllegalAccessError("Esta conta não existe!");
		}
	}
}
