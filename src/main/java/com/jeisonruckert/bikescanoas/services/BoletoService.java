package com.jeisonruckert.bikescanoas.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jeisonruckert.bikescanoas.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPagamento) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPagamento);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
