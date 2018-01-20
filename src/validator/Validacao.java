package  validator;

/**
 * @author wendel.lopes
 *
 */

public class Validacao {

    /**
     * Valida��o de CPF
     * @param cpf CFP para validar (apenas os numeros)
     * @return boolean true se o CPF for v�lido, caso contr�rio false
     */
    public static boolean validaCpf(String cpf) {

		if (cpf != null && !cpf.equals("") && cpf.length() == 14) {
			String temp = cpf.substring(0,3) + cpf.substring(4,7) + cpf.substring(8,11) + cpf.substring(12,14);
			cpf = temp;
		}
        
        if (cpf == null || cpf.length() != 11 || !cpf.matches("^[0-9]*$") || cpf.equals("00000000000") ||
            cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") ||
            cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") ) {
                return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i ++) {
            soma += Character.digit(cpf.charAt(i), Character.MAX_RADIX) * (10 - i);
        }
    
        int resto = 11 - (soma % 11);
    
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
    
        if (resto != Character.digit(cpf.charAt(9), Character.MAX_RADIX)) {
            return false;
        }
    
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.digit(cpf.charAt(i), Character.MAX_RADIX) * (11 - i);
        }
    
        resto = 11 - (soma % 11);
    
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
    
        if (resto != Character.digit(cpf.charAt(10), Character.MAX_RADIX)) {
            return false;
        }
        return true;
    }

    public static boolean validaCnpj(String cnpj) {

		if (cnpj != null && !cnpj.equals("") && cnpj.length() == 18) {
			String temp = cnpj.substring(0,2) + cnpj.substring(3,6) + cnpj.substring(7,10) + cnpj.substring(11,15) + cnpj.substring(16,18);
			cnpj = temp;
		}
        
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("^[0-9]*$") || cnpj.equals("00000000000000") ||
            cnpj.equals("11111111111111") || cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
            cnpj.equals("44444444444444") || cnpj.equals("55555555555555") || cnpj.equals("66666666666666") ||
            cnpj.equals("77777777777777") || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ) {
                return false;
        }

        int soma = 0;

        for (int i=0, j = 5; i < 12; i++) {
            soma += j-- * (cnpj.charAt(i) - '0');
            if (j < 2) j = 9;
        }

        soma = 11 - (soma % 11);

        if (soma > 9) soma = 0;

        if (soma == (cnpj.charAt(12) - '0')) {
            soma = 0;

            for (int i=0, j = 6; i < 13; i++) {
                soma += j-- * (cnpj.charAt(i) - '0');
                if (j < 2) j = 9;
            }

            soma = 11 - (soma % 11);

            if (soma > 9) soma = 0;

            if (soma == (cnpj.charAt(13) - '0')) {
                return true;
            }
        }
        return false;
    }

}
