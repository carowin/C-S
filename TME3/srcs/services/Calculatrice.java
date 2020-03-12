package services;

public interface Calculatrice {
	
	public Integer add(Integer a, Integer b);
	
	public Integer sous(Integer a, Integer b);
	
	public Integer mult(Integer a, Integer b);
	
	public ResDiv div(Integer a, Integer b);
	
	
	public static class ResDiv{
		Integer quotient;
		Integer reste;
		
		public ResDiv(Integer quotient,Integer reste) {
			this.quotient = quotient;
			this.reste = reste;
		}
		
		public int getQuotient() {
			return quotient;
		}
		
		public int getReste() {
			return reste;
		}
	}
}
