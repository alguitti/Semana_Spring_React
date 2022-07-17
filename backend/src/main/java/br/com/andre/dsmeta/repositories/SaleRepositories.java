package br.com.andre.dsmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andre.dsmeta.entities.Sale;

@Repository
public interface SaleRepositories extends JpaRepository<Sale, Long>{

}
