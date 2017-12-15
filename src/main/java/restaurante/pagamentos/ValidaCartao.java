package restaurante.pagamentos;

public class ValidaCartao {

	public static boolean Validar(String ccNumber) {
		int sum = 0;
		boolean alternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
	}

	public static String badeira(String ccNumber) {
		Integer primeiro = new Integer(ccNumber.charAt(0));
		if (primeiro == 5 || primeiro == 6) {
			return "MasterCard";
		} else {
			return "Visa";
		}
	}

}
