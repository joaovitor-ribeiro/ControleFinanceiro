package controlefinanceiroservicos.utils;

import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ValidUtils {
	
	public void validaObject(Object object, List<String> campos, int menor, int maior) throws Exception {
		Class<?> classe = object.getClass();
		List<String> camposSemAcentos = removeAcentosCampos(campos);
		int i = 0;
		
		for (Field atributo : classe.getDeclaredFields()) {
			atributo.setAccessible(true);
			
			if (camposSemAcentos.contains(atributo.getName())) {
				if (atributo.getType().getName().equals("java.lang.String")) {
					if (atributo.get(object) == null) {
						throw new RuntimeException("O campo " + campos.get(i) + " é de preenchimento obrigatório!");
					} else {
						validStringModel(String.valueOf(atributo.get(object)), campos.get(i), menor, maior);
					}
				} else if (isNumber(atributo.getType().getName())) {
					if (atributo.get(object) == null) {
						throw new RuntimeException("O campo " + campos.get(i) + " é de preenchimento obrigatório!");
					} else if (Double.parseDouble(atributo.get(object).toString()) <= 0) {
						throw new RuntimeException("O campo " + campos.get(i) + " não pode ser menor ou igual a zero!");		
					}
				} else if ( atributo.getType().getName().equals("java.util.Date") ) {
					if (atributo.get(object) == null || atributo.get(object).toString().isEmpty()) {
						throw new RuntimeException("A data é de preenchimento obrigatório!");
					} 
				}
				i++;
			} 
		}
		
	}
	
	public void validStringModel(String campo, String descricao, int menor, int maior) {
		if (validStringEmpty(campo)) {
			throw new RuntimeException("O campo " + descricao + " é de preenchimento obrigatório!");
		} else if (campo.length() < menor) {
			throw new RuntimeException("O campo " + descricao + " não pode ter menos do que " + menor + " caracteres!");
		} else if (campo.length() > maior) {
			throw new RuntimeException("O campo " + descricao + " não pode ter mais do que " + maior + " caracteres!");
		}
	}
	
	public boolean validStringEmpty(String campo) {
		if (campo == null || campo.isEmpty()) {
			return true;
		} 
		return false;
	}
	
	private boolean isNumber(String type) {
		switch (type) {
			case "java.lang.Integer":
			case "java.lang.Double":
			case "java.lang.Float":
			case "java.lang.Long":
			case "java.math.BigDecimal":
			case "int":
			case "double":
			case "float":
			case "long":
				return true;
			default:
				return false;
		}
	}
	
	private List<String> removeAcentosCampos(List<String> campos) {
		List<String> camposSemAcentos = new ArrayList<String>();
		campos.forEach(campo -> {
			camposSemAcentos.add(removerAcentos(campo));
		});
		return camposSemAcentos;
	}
	
	private String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

}
