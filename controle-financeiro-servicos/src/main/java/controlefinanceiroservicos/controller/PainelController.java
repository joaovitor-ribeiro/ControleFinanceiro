package controlefinanceiroservicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import controlefinanceiroservicos.bean.PainelBean;
import controlefinanceiroservicos.service.PainelService;

@RestController
@RequestMapping("painel")
@CrossOrigin
public class PainelController {
	
	@Autowired
	private PainelService painelService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public PainelBean painelControleFinanceiro(@RequestParam(required = false) String data) {
		return painelService.painelControleFinanceiro(data);	
	}
}
