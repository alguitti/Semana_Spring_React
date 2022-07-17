package br.com.andre.dsmeta.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.andre.dsmeta.entities.Sale;
import br.com.andre.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public ResponseEntity<Page<Sale>> getAll(
			@RequestParam(value = "minDate", defaultValue = "")String minDate, 
			@RequestParam(value = "maxDate", defaultValue = "")String maxDate, 
			Pageable page) {
		return ResponseEntity.ok().body(saleService.findSalesByPeriod(minDate, maxDate, page));
	}
	
}
