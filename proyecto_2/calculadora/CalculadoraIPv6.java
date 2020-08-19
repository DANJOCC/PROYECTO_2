package proyecto_2.calculadora;

public class CalculadoraIPv6{
	private String IP;//IPv6
	private String Clase;//Clase de la IPv6
	private int[] hex=new int[8];//Array para guardar los hexadecimales
	private int net;//mascara de la ip
	private int binario; //Variable para guardar en binario
	public CalculadoraIPv6(){}

	public CalculadoraIPv6(String IP){

		if(VerificarIP(IP)){// Se verifica el si la IP ingresada es valida

			this.IP=IP;

			//Banderas

			boolean flag=false;
			boolean flag1=false;
			boolean ipv4=false;

			/*Para que no se produscan fallos al ingresar un ipv6 como ::ffff/96 se corta el
			primer caracter de la cadena*/

			if(IP.startsWith(":") && (IP.charAt(1)==':')) 
                        	IP=IP.substring(1,IP.length());

			String[] token=IP.split("[:]");
			String[] tokens=token[token.length-1].split("[/]");

                      	int num=8-token.length; // la variable num guarda el valor de la cantidad de bloques suprimidos

			if(tokens.length==2){
                                        token[(token.length)-1]=tokens[0];
                                        this.net=Integer.parseInt(tokens[1]);
                        }
                        else
                                this.net=0;


			for(int n=0;n<token[token.length-1].length();n++)// se verifa si es una IPv4-traducida/mapeada.
                	        if(token[token.length-1].charAt(n)==46)
        	                        ipv4=true;

                       if(ipv4)
	                       if(CalculadoraIP.VerificarIP(tokens[0])){

				/* Si la IPv4 es valida se transforma los dos bloques faltantes a formato IPv6,
				   sino se convierten a 0.
				*/

				       flag1=true;

	                               CalculadoraIP IPv4=new CalculadoraIP(tokens[0]);

                                       Binario b0=new Binario(IPv4.getOc1());
                                       Binario b1=new Binario(IPv4.getOc2());
                                       Binario b2=new Binario(IPv4.getOc3());
                                       Binario b3=new Binario(IPv4.getOc4());

                                       this.hex[7]=Binario.getSum_to_Dec(b2.getBinario()+b3.getBinario());
                                       this.hex[6]=Binario.getSum_to_Dec(b0.getBinario()+b1.getBinario());			               this.net=Integer.parseInt(tokens[1]);

                               }
                               else{
                                       token[(token.length)-1]=Integer.toHexString(0);
                               }

                        for(int k=0;k<token.length;k++){

                                        /*Dentro de esta sentencia for se obtendran las posiciones de los 
                                        bloques haxadecimales :0000: y se les asignara el valor de 0
                                        en el array hex */

                       		if((token[k].equals("")) || (token[k].equals(" "))){

                               		flag=true;

                                }
                              	else
                              		 if(flag1)
                                         	if (flag && (token[0].equals("") || token[0].equals(" ")) && ((k+num-1)<6))
                        	                        this.hex[k+num-1]=Integer.parseInt(token[k],16);
                       	                        else if(flag && ((k+num) < 6))
                                                        this.hex[k+num]=Integer.parseInt(token[k],16);
               	                                else if((k+num)>=6)
                                                        if(!(CalculadoraIP.VerificarIP(token[k])))
                                   	                     this.hex[k+num-1]=Integer.parseInt(token[k],16);
                                                        else
                                                             break;
                                                else
                                                        this.hex[k]=Integer.parseInt(token[k],16);
					 else if(flag && (token[0].equals("") || token[0].equals(" ")))
						this.hex[k+num]=Integer.parseInt(token[k],16);
                                         else if(flag)
                          	                this.hex[k+num]=Integer.parseInt(token[k],16);
                                         else
          	                       	        this.hex[k]=Integer.parseInt(token[k],16);
			}
		}

		this.Clase=claseIpv6();

	}

	public void setIP(String IP){
		this.IP=IP;
	}
	public String getIP(){
		return this.IP;
	}

	public void setHexadecimal(String hexa,int j){
		if((j>-1)&&(j<9))
			this.hex[j-1]=Integer.parseInt(hexa,16);
	}
	public String getHexadecimal(int j){
		if((j>-1)&&(j<9))
			return Integer.toHexString(this.hex[j-1]);
		else
			return null;

	}
	public static boolean VerificarIP(String IP){

		int cont=0;
		int a;
		int b;
		int [] hexa=new int[8];
		boolean flag=true;//necesario
		boolean flag1=false;
		boolean flag2=true;
		boolean flag3=false;

		/*Para que no se produscan fallos al ingresar un ipv6 como ::ffff/96 se corta el
                 primer caracter de la cadena*/

		if(IP.startsWith(":") && (IP.charAt(1)==':'))
			IP=IP.substring(1,IP.length());
		
		/* si se ingresa por error una IPv4 por error se le agrega el bloque :ffff: para transformala
		en una IPv4 traducida/mapeada*/

		if(CalculadoraIP.VerificarIP(IP))
			IP=":ffff:"+IP;

		if((IP.length()<=39)&&(IP.length()>=3))//Verifica que el tamaño de la notacion IP este en el rango correcto
			for(int i=0;i<IP.length();i++)//Verifica que los caracteres correspondan a una notacion de una IPv6
				if(   ( !(IP.charAt(i)>45) && (IP.charAt(i)<59) ) &&
				      ( !(IP.charAt(i)>64) && (IP.charAt(i)<71) ) &&
				      ( !(IP.charAt(i)>96) && (IP.charAt(i)<103))){
					 flag=false;
					 break;
				}
		if(flag){
			String[] token=IP.split("[:]");
			
			if(token.length<=0)//si la cantidad de tokens en cero o menor retorna falso
				return false;

			for(int j=0;j<token.length;j++)//Verifica si se simplifico la IP
				if((token[j].equals("")) || (token[j].equals(" ")))
					cont++;

			if(cont>1)//verifica si la IP cuenta solo con una simplificacion de bloques :0000:
				return false;
			else{

				int num=8-token.length;// la variable num guarda el valor de la cantidad de bloques suprimidos

				boolean ipv4=false;
				boolean Net=false;

				String hex8=token[(token.length)-1];

				String[] token1=hex8.split("[/]");

				if(token1.length==2){
                                        
                                        if((Integer.parseInt(token1[1])<=128) && (Integer.parseInt(token1[1])>=0)){
                                                Net=true;
						token[(token.length)-1]=token1[0];
					}
					else
						return false;
                                }
				
				for(int n=0;n<token[token.length-1].length();n++)// se verifa si es una IPv4-traducida/mapeada.
                                	if(token[token.length-1].charAt(n)==46)
                                        	ipv4=true;

				if(ipv4)
					if(CalculadoraIP.VerificarIP(token1[0])){//si hex8 es una Ipv4 correcta se realiza el proceso 

						flag3=true;

						CalculadoraIP IPv4=new CalculadoraIP(token[0]);

						Binario b0=new Binario(IPv4.getOc1());
						Binario b1=new Binario(IPv4.getOc2());
						Binario b2=new Binario(IPv4.getOc3());
						Binario b3=new Binario(IPv4.getOc4());

						 hexa[7]=Binario.getSum_to_Dec(b2.getBinario()+b3.getBinario());
						 hexa[6]=Binario.getSum_to_Dec(b0.getBinario()+b1.getBinario());
					}
					else{//si no es correcta el ultimo token se convierte a cero
						token[(token.length)-1]="0";

					}

				for(int k=0;k<token.length;k++){

					/*Dentro de esta sentencia for se obtendran las posiciones de los 
					bloques haxadecimales :0000: y se les asignara el valor de 0
					en el array hexa */
                                        if(token[k].equals("") || token[k].equals(" ")){	
						flag1=true;

					}
					else
						if(flag3)
							if(flag1 && (token[0].equals("") || token[0].equals(" ")) && ((k+num-1)<6))
								hexa[k+num-1]=Integer.parseInt(token[k],16);
							else if(flag1 && ((k+num) < 6))
								hexa[k+num]=Integer.parseInt(token[k],16);
							else if ((k+num)>=6)
								if((!CalculadoraIP.VerificarIP(token[k])))
									hexa[k+num-1]=Integer.parseInt(token[k],16);
								else
									break;
							else
								hexa[k]=Integer.parseInt(token[k],16);
						else if(flag1 && (token[0].equals("") || token[0].equals(" ")))
							hexa[k+num]=Integer.parseInt(token[k],16);
						else if(flag1)
							hexa[k+num]=Integer.parseInt(token[k],16);
						else
	   				        	hexa[k]=Integer.parseInt(token[k],16);
							
				}

				for(int m=0;m<8;m++){
					if((hexa[m]<0) || (hexa[m]>65535)){
						flag2=false;
						break;
					}
				}

				if(flag2)
					return flag2;
				else
					return flag2;

			}
		}
		else
			return flag;
	}

	//Metodo para claseIpv6 que retorna la clasificacion de la IPv6

	public String claseIpv6(){
		String clase;

			if((hex[0]>=8192) && (hex[0]<=16383) && (this.net==3))
				clase="Unicast global/Anycast";
			else if((hex[0]>=64512) && (hex[0]<=65023) && (this.net==7))
				clase="Unicast Local Unica";
			else if((hex[0]>=65152) && (hex[0]<=65215) && (this.net==10))
				clase="Unicast Enlace Local";
			else if((hex[0]==0) && (hex[1]==0) && (hex[2]==0) && (hex[3]==0) &&
				 (hex[4]==0) && (hex[5]==65535) && (hex[6]<=65535) && (hex[7]<=65535) && (this.net==96))
				clase="Unicast IPv6 traducida a IPv4";
			else if((hex[0]>=65280) && (this.net==8))
				clase="Multicast";
			else if((hex[0]==0) && (hex[1]==0) && (hex[2]==0) && (hex[3]==0) &&
                                 (hex[4]==0) && (hex[5]==0) && (hex[6]==0) && ((hex[7]==0) || (hex[7]==1)) && (this.net==128))
				if(hex[7]==1)
					clase="LOOPBACK";
				else
					clase="UNSPECIFIC ADDRESS";
			else
				clase="Clasificacion de la IPv6 desconocida ";

		return clase;

	}

	@Override
	public String toString(){
		return this.IP+"\n"+
		       "Mascara: "+this.net+"\n"+
		       "Clase: "+this.Clase+"\n"+
		       "Hexadecimales en base decimal: "+"\n"+
		       "primer hexadecimal: "+this.hex[0]+"\n"+
		       "segundo hexadecimal: "+this.hex[1]+"\n"+
		       "tercer hexadecimal: "+this.hex[2]+"\n"+
		       "cuarto hexadecimal: "+this.hex[3]+"\n"+
                       "quinto hexadecimal: "+this.hex[4]+"\n"+
		       "sexto hexadecimal: "+this.hex[5]+"\n"+
                       "septimo hexadecimal: "+this.hex[6]+"\n"+
                       "octavo hexadecimal: "+this.hex[7]+"\n";
	}

}
