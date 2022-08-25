package controlefinanceiroservicos.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlefinanceiroservicos.bean.PainelBean;
import controlefinanceiroservicos.repository.DespesaCustomRepository;
import controlefinanceiroservicos.repository.GanhoCustomRepository;

@Service
public class PainelService {

	@Autowired
	private GanhoCustomRepository ganhoCustomRepository;
	
	@Autowired
	private DespesaCustomRepository despesaCustomRepository;
	
	public PainelBean painelControleFinanceiro(String data) {
		if (data == null || data.isEmpty()) {
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			String mes = (localDate.getMonthValue() < 10) ? "0" + localDate.getMonthValue() : localDate.getMonthValue() + "";
			data = localDate.getYear() + "-" + mes;
		}
		
		double totalGanho = ganhoCustomRepository.totalGanho(data);
		double totalDespesa = despesaCustomRepository.totalDepesa(data);
		double saldo = totalGanho - totalDespesa;
		
		return new PainelBean(totalGanho, totalDespesa, saldo);
	}

}
