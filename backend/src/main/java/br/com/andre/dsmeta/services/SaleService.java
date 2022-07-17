package br.com.andre.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.andre.dsmeta.entities.Sale;
import br.com.andre.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	public List<Sale> findAll() {
		return saleRepository.findAll();
	}

	public Page<Sale> findAll(Pageable page) {
		return saleRepository.findAll(page);
	}

	/**
	 * Makes a query within the provided period sorting by the object amount
	 * 
	 * @param min  The first date for the period
	 * @param max  The last date for the period
	 * @param page The amount of items per page
	 * @return Sales pages by period
	 */

	public Page<Sale> findSalesByPeriod(String minDate, String maxDate, Pageable page) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min, max;

		if (maxDate.equals("")) {
			max = today;
		} else {
			max = LocalDate.parse(maxDate);
		}
		if (minDate.equals("")) {
			min = today.minusDays(365);
		} else {
			min = LocalDate.parse(minDate);
		}

		return saleRepository.findSalesByPeriod(min, max, page);
	}

}
