package com.example.algamoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PedidoFilter {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacaoDe;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacaoAte;

	public LocalDate getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(LocalDate dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public LocalDate getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(LocalDate dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

}
