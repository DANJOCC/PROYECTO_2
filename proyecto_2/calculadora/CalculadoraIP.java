//Clase para calcualar direcciones IPv4
package proyecto_2.calculadora;

public class CalculadoraIP{

	//Atributos de una IPv4
	private String IP;//Direccion IP
	private String Type;//Tipo: Privada o Publica
	private String Clase;//Clases: A-B-C-D-E
	private String APIPA;//Protocolo: Pertenece o no al protocolo APIPA
	private String Special;//Reservacion: Es o no reservada
	private String Cast="ninguna";//Difusion: unicast, multicast, broadcast
	private String Mask;//Mascara de red: se ingresa en la ip como /xx, se guarda como una direccion IP
	private String Red;//Direccion red
	private String BroadCast;//Direccion Broadcast
	private String Gateway;//Direccion Gateway
	private String Rank;//Rango de direcciones IP de la red
	private String LastIP;//Ultima direccion IP de la red
	
	private final int OCT=255;//valor maximo de un octeto

	//De izquierda a derecha

	private int oc1;//octeto 1
	private int oc2;//octeto 2
	private int oc3;//octeto 3
	private int oc4;//octeto 4
	private int net;//mascara de red en forma CDMI

	public CalculadoraIP(){

		this.IP="0.0.0.0";
		this.oc1=this.oc2=this.oc3=this.oc4=0;
	}

	public CalculadoraIP(int oc1,
			     int oc2,
			     int oc3,
			     int oc4){

		if(VerificarIP(oc1+"."+oc2+"."+oc3+"."+oc4)){
			this.oc1=oc1;
                	this.oc2=oc2;
                	this.oc3=oc3;
                	this.oc4=oc4;
		}
		else
			this.oc1=this.oc2=this.oc3=this.oc4=0;
	}

	public CalculadoraIP(String IP){

		if(VerificarIP(IP)){
			
			String[] token=IP.split("[.]");
			String[] token1=token[3].split("[/]");

			this.oc1=Integer.parseInt(token[0]);  
                        this.oc2=Integer.parseInt(token[1]);
                        this.oc3=Integer.parseInt(token[2]);

			if(token1.length==2){

				 this.IP=token[0]+"."+token[1]+"."+token[2]+"."+token1[0];
                       		 this.oc4=Integer.parseInt(token1[0]);
				 this.net=Integer.parseInt(token1[1]);

			}

			else{
				
				 this.IP=token[0]+"."+token[1]+"."+token[2]+"."+token[3];
				 this.oc4=Integer.parseInt(token[3]);
                                 this.net=1;

			}
		}
		else{
			this.IP="0.0.0.0/0";
			this.oc1=this.oc2=this.oc3=this.oc4=this.net=0; 
		}

		this.Type=getIpType();
		this.Clase=getIpClass();
		this.APIPA=getIpAPIPA();
		this.Special=getIpSpecial();
		this.Mask=getMask();
		this.Red=getRed();
		this.BroadCast=getBroadCast();
		this.Gateway=getGateway();
		this.Rank=getRank();
	}
	
	//Setter y Getters

	public void setIP(String IP){

		this.IP=IP;
	}

	public String getIP(){

		return this.IP;

	}
	public String getCast(){

		return this.Cast;

	}
	public void setOc1(int oc1){ 

                this.oc1=oc1;
        }

        public int getOc1(){ 

                return this.oc1;

        }

	public void setOc2(int oc2){ 

                this.oc2=oc2;
        }

        public int getOc2(){ 

                return this.oc2;

        }

	public void setOc3(int oc3){ 

                this.oc3=oc3;
        }

        public int getOc3(){ 

                return this.oc3;

        }
	public void setOc4(int oc4){ 

                this.oc4=oc4;
        }

        public int getOc4(){ 

                return this.oc4;

        }

	//Metodo VerificarIP para validar el ingreso de una IPv4

	public static boolean VerificarIP(String IP){

		boolean flag=true;
		boolean flag1=false;

		if((IP.length()<=18)&&(IP.length()>=7)){

			for(int i=0;i<IP.length();i++){

				if((IP.charAt(i)!='0')&&
				   (IP.charAt(i)!='1')&&
				   (IP.charAt(i)!='2')&&
				   (IP.charAt(i)!='3')&&
				   (IP.charAt(i)!='4')&&
				   (IP.charAt(i)!='5')&&
				   (IP.charAt(i)!='6')&&
				   (IP.charAt(i)!='7')&&
				   (IP.charAt(i)!='8')&&
				   (IP.charAt(i)!='9')&&
				   (IP.charAt(i)!='.')&&
				   (IP.charAt(i)!='/')){
				
					flag=false;
					break;
				}
			}
		
			if(flag){

				String[] token=IP.split("[.]");

				if(token.length==4){

					String[] token1=token[3].split("[/]");

					if(token1.length==2){

						int one=Integer.parseInt(token[0]);
                				int two=Integer.parseInt(token[1]);
                				int three=Integer.parseInt(token[2]);
                				int four=Integer.parseInt(token1[0]);
						int mnet=Integer.parseInt(token1[1]);

						if((one<=255) && (one>=0) &&
			  			   (two<=255) && (two>=0) &&
			  			   (three<=255) && (three>=0) &&
			  			   (four<=255) && (four>=0) &&
						   (mnet<=32) && (mnet>=0)){

							flag1=true;

							return flag1;
						}
						else
							return flag1;
					}
					else{
						int one=Integer.parseInt(token[0]);
                                                int two=Integer.parseInt(token[1]);
                                                int three=Integer.parseInt(token[2]);
						int four;
						if(!token[3].endsWith("/"))
                                                	four=Integer.parseInt(token[3]);
						else
							return flag1;

						if((one<=255) && (one>=0) &&
                                                   (two<=255) && (two>=0) &&
                                                   (three<=255) && (three>=0) &&
                                                   (four<=255) && (four>=0)){

                                                        flag1=true;

                                                        return flag1;
						}
                                                else
                                                        return flag1;

					}
				}
				else
					return flag1;
			}
			else
				return flag1;
		}
		else
			return flag1;

	}

	//Metodo IpType que retorna el tipo de IPv4

	public String getIpType(){
		String type;

		switch(this.oc1){
			case 10:
				type="privada";
				break;
			case 172:
				if((this.oc2<=31)&&(this.oc2>=16))
					type="privada";
				else
					type="publica";
				break;
			case 192:
				if(this.oc2==168)
					type="privada";
				else
					type="publica";
				break;
			default:
				type="publica";
				break;
		}

		return type;
	}

	//Metodo IpClass que retorna la clase de IPv4

	public String getIpClass(){
		String clase="";

		if(this.oc1<=126){
			clase="Clase A";
		}
		else
			if((this.oc1>=128)&&(this.oc1<=191)){
				clase="Clase B";
			}
			else
				if(this.oc1<=223){
					clase="Clase C";
				}
				else
					if(this.oc1<=239){
						clase="Clase D";
						this.Cast="Multicast";
					}
					else
						clase="Clase E";

		return clase;
	}

	//Metodo IpAPIPA que retorna la pertenencia al PROTOCOLO APIPA

	public String getIpAPIPA(){
		if((this.oc1==169)&&
		   (this.oc2==254)&&
		   (this.oc4<=254)&&
		   (this.oc4>=1)){

			return "Protocolo APIPA";
		}

		else
			return "No pertenece";
	}

	//Metodo IpSpecial que retorna el tipo de reservacion de una IPv4

	public String getIpSpecial(){
		String spl="";
		switch(this.oc1){
			case 0:
				spl="Reservada: Direcciones de Origen";
				break;
			case 127:
				spl="Reservada:Direccion Loopback";
			case 169:
				if(this.oc2==254){
					spl="Reservada: Direccion link-local";
					this.Cast="Unicast";
				}
				else
					spl="No Reservada";
				break;
			case 192:
				if((this.oc2==0)&&(this.oc3==2))
					spl="Reservada: Direcciones TEST-NET";
				else
					spl="No Reservada";
				break;
			default:
				spl="No Reservada";
		}

		return spl;
	}

	//Metodo getMask que retorna la mascara de red en un formato de 4 octectos

	public String getMask(){
		int mask=0;
		String masknet="";

		if(this.net<8){
			for(int i=0;i<8-this.net;i++)
				mask+=Math.pow(2,i);

			masknet=(OCT-mask)+".0.0.0";
		}
		else{
			if(this.net<16){
				for(int i=0;i<16-this.net;i++)
					mask+=Math.pow(2,i);

				masknet="255."+(OCT-mask)+".0.0";
			}
			else{
				if(this.net<24){
					for(int i=0;i<24-this.net;i++)
						mask+=Math.pow(2,i);

					masknet="255.255."+(OCT-mask)+".0";
				}
				else{
					for(int i=0;i<32-this.net;i++)
                                                mask+=Math.pow(2,i);

                                        masknet="255.255.255."+(OCT-mask);
				}
			}
		}

		return masknet;
	}

	//Metodo getRed que retorna la direccion de red  de la IP

	public String getRed(){
		String[] token=this.Mask.split("[.]");

		int octo1=this.oc1 & (Integer.parseInt(token[0]));
		int octo2=this.oc2 & (Integer.parseInt(token[1]));
		int octo3=this.oc3 & (Integer.parseInt(token[2]));
		int octo4=this.oc4 & (Integer.parseInt(token[3]));

		String red=octo1+"."+octo2+"."+octo3+"."+octo4;

		return red;


	}

	/*Metodo getBroadCast que retorna la  direccion de Broadcast de la IP
	, evalua si la IP es broadcast y la ultima direccion de la red disponible
	*/

	public String getBroadCast(){
                String[] token=this.Mask.split("[.]");

                int octo1=this.oc1 | (256 + ( ~ (Integer.parseInt(token[0])) ) );
                int octo2=this.oc2 | (256 + ( ~ (Integer.parseInt(token[1])) ) );
                int octo3=this.oc3 | (256 + ( ~ (Integer.parseInt(token[2])) ) );
                int octo4=this.oc4 | (256 + ( ~ (Integer.parseInt(token[3])) ) );

                String broad=octo1+"."+octo2+"."+octo3+"."+octo4;
		this.LastIP=octo1+"."+octo2+"."+octo3+"."+(octo4-1);

		if((octo1==this.oc1)&&(octo2==this.oc2)&&
		   (octo3==this.oc3)&&(octo4==this.oc4)){

			this.Cast="BroadCast";

		}

                return broad;
	}

	//Metodo getBroadCast que retorna eñ Gateway de la IP  

	public String getGateway(){
                String[] token=this.Mask.split("[.]");

                int octo1=this.oc1 & (Integer.parseInt(token[0]));
                int octo2=this.oc2 & (Integer.parseInt(token[1]));
                int octo3=this.oc3 & (Integer.parseInt(token[2]));
                int octo4=(this.oc4 & (Integer.parseInt(token[3]))) + 1;
                String gateway=octo1+"."+octo2+"."+octo3+"."+octo4;

                return gateway;

        }

	//Metodo getRank retorna el rango de la red

	public String getRank(){
		return this.Gateway+"-"+this.LastIP;
	}

	//Metodo CalcularIpHost para calcular una direccion IP con una mascara de red especifica en formato cdmi
	public static String CalcularIpHost(int host){
		String A="10.0.0.0/";
		String B="172.16.0.0/";
		String C="192.168.1.0/";
		int nhost=0;
		int j=0;
		for(int i=0;i<=32; i++){

			nhost= (int) Math.pow(2,i);

			if(nhost>host){
				j=i;
				break;
			}

		}

		if(j==0)
			return "0.0.0.0/0";
		else if(j<9)
                        return C+(32-j);
                else if(j<17)
                        return B+(32-j);
                else if(j<25)
                        return A+(32-j);
		else
			return "0.0.0.0/"+(32-j);

	}
	@Override
	public String toString(){
		return this.IP+"\n"+"\n"+
		       "Mascara de red: "+this.Mask+"\n"+"\n"+
		       "Direccion de red: "+this.Red+"\n"+"\n"+
		       "Direccion de Broadcast: "+this.BroadCast+"\n"+"\n"+
		       "Gateway: "+this.Gateway+"\n"+"\n"+
		       "Rango: "+this.Rank+"\n"+"\n"+
		       "Tipo: "+this.Type+"\n"+"\n"+
		       "Clase: "+this.Clase+"\n"+"\n"+
		       "Protocolo APIPA ?: "+this.APIPA+"\n"+
		       "Reservada ?: "+this.Special+"\n"+"\n"+
		       "Difusion:"+this.Cast;
	}
}
