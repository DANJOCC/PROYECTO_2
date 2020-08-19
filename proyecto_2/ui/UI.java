package proyecto_2.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.WindowConstants;
import java.awt.event.*;
import proyecto_2.calculadora.*;
import proyecto_2.main.DB;
import java.util.ArrayList;

public class UI extends JFrame implements ActionListener{
	private ArrayList <JButton> botones=new ArrayList<JButton>();
	private ArrayList<String> row;
	private JTextArea pantalla;
	private JTextField pantalla1;
	private JScrollPane scroll;
	private DB notebook=DB.getInstances();
	private String texto;
	private boolean host=false;
	private boolean ipv4=false;
	private boolean ipv6=false;
	private static String[][] sticker={ {"7","8","9","AC"},
					    {"4","5","6","IPv4"},
					    {"1","2","3","IPv6"},
					    {".","0","/","ENTER"},
					    {"A","B","C","D",},
					    {"E","F",":","0000"},
					    {"nH","DB","DEL",""}};
	public UI(){

		super("C.IP: Calculadora de IP");
		Container container=getContentPane();
                container.setLayout(null);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		pantalla1=new JTextField();
		pantalla1.setBounds(300,0,250,50);
		pantalla1.setEnabled(false);
		pantalla1.setDisabledTextColor(Color.BLACK);
		container.add(pantalla1);
		pantalla=new JTextArea();
		pantalla.setEditable(false);
		scroll=new JScrollPane(pantalla);
		scroll.setBounds(0,0,300,300);
		container.add(scroll);


		for(int i=0;i<7;i++){
			for(int j=0;j<4;j++){
				botones.add(new JButton(sticker[i][j]));
			}
		}

		for(int i=0;i<4;i++){

			JButton a=botones.get(24+i);

			if(i<2)
				a.setBounds(300+63*i,50,63,50);
			else
				a.setBounds(302+62*i,50,62,50);
			container.add(a);
		}
		int k=0;
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				JButton b=botones.get(k);
				if(j<3)
					b.setBounds((6+j)*50,(i+2)*50,50,50);
				else
					b.setBounds((6+j)*50,(i+2)*50,100,50);
				container.add(b);
				k++;
			}
		k=0;
		for(int i=0;i<7;i++){
                        for(int j=0;j<4;j++){
                                botones.get(k).addActionListener(this);
				k++;
                        }
		}

		setSize(555,335);
                setVisible(true);

	}
	public JPanel vista(){
		JPanel vista=new JPanel();
		vista.setLayout(null);
		vista.add(scroll);
		pantalla1.setBounds(300,0,250,50);
                vista.add(pantalla1);

		for(int i=0;i<4;i++){
			JButton a=botones.get(24+i);
                        if(i<2)
                                a.setBounds(300+63*i,50,63,50);
                        else
                                a.setBounds(302+62*i,50,62,50);

                        vista.add(a);
                }
		int k=0;
                for(int i=0;i<4;i++)
                        for(int j=0;j<4;j++){
                                JButton b=botones.get(k);
                                if(j<3)
                                        b.setBounds((6+j)*50,(i+2)*50,50,50);
                                else
                                        b.setBounds((6+j)*50,(i+2)*50,100,50);
                                vista.add(b);
                                k++;
                        }

		return vista;


	}
	public JPanel vista1(){
		JPanel vista=new JPanel();
		vista.setLayout(null);
		pantalla1.setBounds(300,0,350,50);
		vista.add(scroll);
		vista.add(pantalla1);

		int p=24;

		for(int i=0;i<4;i++){

			if(i==3)
				p=20;

			JButton a=botones.get(p+i);

			if(i<2)
                        	a.setBounds(300+88*i,50,88,50);
			else
				a.setBounds(302+87*i,50,87,50);
                        vista.add(a);
                }
		int k=0;
                for(int i=0;i<6;i++)
                        for(int j=0;j<4;j++){

				if((i==5) && (j==3))
					k=27;

				JButton b=botones.get(k);

                                if((j<3) && (i<4))
                                        b.setBounds((6+j)*50,(i+2)*50,50,50);
                                else if((j==3) && (i<4))
                                        b.setBounds((6+j)*50,(i+2)*50,100,50);
				else if((i==4))
					b.setBounds(550,(j+2)*50,50,50);
				else
					b.setBounds(600,(j+2)*50,50,50);

                                vista.add(b);
                                k++;
			}

		return vista;
	}

	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==botones.get(0)){
			texto=pantalla1.getText();
			texto+="7";
			pantalla1.setText(texto);
		}
		if(e.getSource()==botones.get(1)){ 
			texto=pantalla1.getText();
                        texto+="8";
                        pantalla1.setText(texto);
                }
		if(e.getSource()==botones.get(2)){ 
			texto=pantalla1.getText();
                        texto+="9";
                        pantalla1.setText(texto);
                }

		if(e.getSource()==botones.get(4)){
                 	texto=pantalla1.getText();
                        texto+="4";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(5)){ 
			texto=pantalla1.getText();
                        texto+="5";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(6)){ 
			texto=pantalla1.getText();
                        texto+="6";
                        pantalla1.setText(texto);
                }
		if(e.getSource()==botones.get(8)){
                 	texto=pantalla1.getText();
                       	texto+="1";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(9)){ 
			texto=pantalla1.getText();
                        texto+="2";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(10)){ 
			texto=pantalla1.getText();
                        texto+="3";
                        pantalla1.setText(texto);
                }
		if(e.getSource()==botones.get(12)){
                 	texto=pantalla1.getText();
                        texto+=".";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(13)){ 
			texto=pantalla1.getText();
                        texto+="0";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(14)){ 
			texto=pantalla1.getText();
                        texto+="/";
                        pantalla1.setText(texto);
                }
		if(e.getSource()==botones.get(16)){
                 	texto=pantalla1.getText();
                        texto+="A";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(17)){ 
			texto=pantalla1.getText();
                        texto+="B";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(18)){ 
			texto+="C";
                        pantalla1.setText(texto);
                }
		if(e.getSource()==botones.get(19)){
                 	texto=pantalla1.getText();
         		texto+="D";
                        pantalla1.setText(texto);
                }
                if(e.getSource()==botones.get(20)){ 
			texto=pantalla1.getText();
                        texto+="E";
                        pantalla1.setText(texto);
                }
		if(e.getSource()==botones.get(21)){
			texto=pantalla1.getText();
                        texto+="F";
                        pantalla1.setText(texto);
		}
		 if(e.getSource()==botones.get(22)){
                        texto=pantalla1.getText();
                        texto+=":";
                        pantalla1.setText(texto);
                }
		if(e.getSource()==botones.get(23)){
                        texto=pantalla1.getText();
                        texto+="0000";
                        pantalla1.setText(texto);
                }

		if(e.getSource()==botones.get(7)){
			this.ipv4=true;
			this.ipv6=false;
			setSize(555,335);
			setContentPane(vista());
			invalidate();
			validate();
		}
		if(e.getSource()==botones.get(11)){
			this.ipv6=true;
			this.ipv4=false;
			setSize(655,335);
			setContentPane(vista1());
			invalidate();
			validate();
		}
		if(e.getSource()==botones.get(3)){
			texto="";
			pantalla1.setText("");
		}
		if(e.getSource()==botones.get(15)){
			
			texto="";
			if(!this.host)
				if((!ipv4 && !ipv6) || (ipv4 && !ipv6))
					if((CalculadoraIP.VerificarIP( pantalla1.getText() )) && (!pantalla1.getText().equals(""))){
						CalculadoraIP IPv4=new CalculadoraIP(pantalla1.getText());
						pantalla.setText(IPv4.toString());
						Object[] obj={IPv4.getIP(),IPv4.getIpType(),IPv4.getIpClass(),IPv4.getCast(),
								IPv4.getMask(),IPv4.getRed(),IPv4.getBroadCast(),IPv4.getGateway()};

						notebook.dbPrepareStatement("insert into ipv4 (ip,tipo,clase,difusion,mascara,red,broadcast,gateway) values (?,?,?,?,?,?,?,?)",obj,true);
					}
					else
						pantalla.setText("ERROR");
				else
					if((CalculadoraIPv6.VerificarIP( pantalla1.getText() )) && (!pantalla1.getText().equals(""))){
                                        	CalculadoraIPv6 IPv6=new CalculadoraIPv6(pantalla1.getText());
                                        	pantalla.setText(IPv6.toString());
						Object[] obj={IPv6.getIP(),IPv6.claseIpv6()};

                                                notebook.dbPrepareStatement("insert into ipv6 (ip,clase) values (?,?)",obj,false);
                                	}
                                	else
                                        	pantalla.setText("ERROR");
			else{
				pantalla.setText( CalculadoraIP.CalcularIpHost(Integer.parseInt( pantalla1.getText() ) ) );

			}

		}
		if(e.getSource()==botones.get(24)){
			if(this.host)
				this.host=false;
			else
				this.host=true;

			this.ipv4=true;
                        this.ipv6=false;
                        setSize(555,335);
                        setContentPane(vista());
                        invalidate();
                        validate();

			if(this.host){

				botones.get(11).setEnabled(false);
				botones.get(12).setEnabled(false);
				botones.get(14).setEnabled(false);
				botones.get(25).setEnabled(false);
			}
			else{
				botones.get(11).setEnabled(true);
				botones.get(12).setEnabled(true);
                                botones.get(14).setEnabled(true);
                                botones.get(25).setEnabled(true);

			}
		}
		if(e.getSource()==botones.get(25)){
			String db="";
			String query="";
			if(!this.host)
				if((ipv4 || !ipv4) && !ipv6){
					query="select*from ipv4";
					this.row=notebook.dbStatement(query,true);
					for(int i=0;i<this.row.size();i++){
						db+=this.row.get(i)+"\n\n";
					}
					pantalla.setText(db);
				}
				else{
					query="select*from ipv6";
					this.row=notebook.dbStatement(query,false);
                                        for(int i=0;i<this.row.size();i++){
                                                db+=this.row.get(i)+"\n\n";
                                        }
                                        pantalla.setText(db);

				}
			else
				pantalla.setText("ERROR");
		}
	 	if(e.getSource()==botones.get(26)){

                	texto=pantalla1.getText();
			pantalla1.setText("");

			if(!texto.equals("")){
                        	texto=texto.substring(0,texto.length()-1);
				pantalla1.setText(texto);
			}
                }

	}


}
