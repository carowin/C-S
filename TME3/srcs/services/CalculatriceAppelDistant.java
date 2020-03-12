package services;

import services.Calculatrice.ResDiv;

public class CalculatriceAppelDistant {
	
	public Integer add(Integer a, Integer b) {
		return a+b;
	}

	public Integer sous(Integer a, Integer b) {
		return a-b;
	}

	public Integer mult(Integer a, Integer b) {
		return a*b;
	}

	public ResDiv div(Integer a, Integer b) {
		return new ResDiv(a/b,a%b);
	}

}
