package br.com.andre.dsmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andre.dsmeta.entities.Sale;
import br.com.andre.dsmeta.repositories.SaleRepositories;

@Service
public class SaleService {

	@Autowired
	private SaleRepositories saleRepository;
	
	public List<Sale> findAll() {
		return saleRepository.findAll();
	}
	
}
