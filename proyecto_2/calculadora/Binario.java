package proyecto_2.calculadora;
public class Binario{
	private final int SIZE=8;
	private String Binario;
	public Binario(){}

	public Binario(int decimal){

		String binary=Integer.toBinaryString(decimal);
                this.Binario="";
                if(binary.length()<SIZE){
                        for(int p=0;p<SIZE-binary.length();p++){
                                this.Binario+="0";

                        }
                        this.Binario+=binary;
                }
                else
                        this.Binario=binary;

	}

	public String getBinario(){
		return this.Binario;
	}
	
	public static int getSum_to_Dec(String binario){
		int sum=0;

		for(int i=0;i<binario.length();i++)
			if(binario.charAt(binario.length()-1-i)=='1')
				sum+=Math.pow(2,i);

		return sum;

	}

	@Override
	public String toString(){
		return this.Binario+getSum_to_Dec(this.Binario);
	}

}
