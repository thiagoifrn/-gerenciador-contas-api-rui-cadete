package br.com.developer.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.developer.gerenciador.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
