package br.com.andre.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.andre.dsmeta.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

	/**
	 * Makes a query within the provided period sorting by the object amount
	 * @param min The first date for the period
	 * @param max The last date for the period
	 * @param page The amount of items per page
	 * @return Sales pages
	 */
	
	@Query("SELECT obj FROM Sale obj WHERE obj.date BETWEEN :min AND :max ORDER BY obj.amount DESC")
	public Page<Sale> findSalesByPeriod(LocalDate min, LocalDate max, Pageable page);
	
}
