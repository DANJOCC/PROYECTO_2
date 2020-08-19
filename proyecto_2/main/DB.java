package proyecto_2.main;

import java.sql.*;
import java.util.ArrayList;
public class DB{
        private static DB db=new DB();
        private Connection conn;
        private Statement stmt;
        private PreparedStatement pstmt;
        private ResultSet rs;
        private String driverDB="org.postgresql.Driver";
        private String userDB="danjocc2";
        private String urlDB="jdbc:postgresql://localhost:5432/danjocc2";
        private String passDB="djcc27998876";
        
        private DB(){
                try{
                        Class.forName(driverDB);
                        this.conn=DriverManager.getConnection(urlDB,userDB,passDB);
                        System.out.println("Conexion establecida");
                }catch(ClassNotFoundException | SQLException e){
                        e.printStackTrace();
                }
        }

        public static DB getInstances(){
                return db;
        }

        public ArrayList<String> dbStatement(String query,boolean ip){
		ArrayList<String> show=new ArrayList<String>();
		Object[] obj=new Object[8];
		String row="";
                try{
                        this.stmt=this.conn.createStatement();
                        this.rs=this.stmt.executeQuery(query);
                        while(rs.next()){
				if(ip){
                                	obj[0]=rs.getString("ip");
					obj[1]=rs.getString("tipo");
					obj[2]=rs.getString("clase");
					obj[3]=rs.getString("difusion");
					obj[4]=rs.getString("mascara");
					obj[5]=rs.getString("red");
					obj[6]=rs.getString("broadcast");
					obj[7]=rs.getString("gateway");

					row="IP: "+obj[0]+"\n"+"\n"+
					    "Mascara de red: "+obj[4]+"\n"+"\n"+
					    "Direccion de red: "+obj[5]+"\n"+"\n"+
					    "BroadCast: "+obj[6]+"\n"+"\n"+
                                            "Gateway: "+obj[7]+"\n"+"\n"+
					    "Tipo: "+obj[1]+"\n"+"\n"+
                                            "Clase: "+obj[2]+"\n"+"\n"+
					    "Difusion: "+obj[3];

					show.add(row);

				}
				else{
					obj[0]=rs.getString("ip");
                                        obj[1]=rs.getString("clase");

					show.add(row);

				}
                        }
                }catch(SQLException e){
                        e.printStackTrace();
                }finally{
                        try{
                                this.stmt.close();
                                this.rs.close();
                        }catch(SQLException e){
                                e.printStackTrace();
                        }
                }
                return show;
        }
        public void dbPrepareStatement(String query, Object[] obj, boolean ip){
                try{
                	this.pstmt=this.conn.prepareStatement(query);
			if(ip){
                        	this.pstmt.setString(1,(String) obj[0]);
                        	this.pstmt.setString(2,(String) obj[1]);
                        	this.pstmt.setString(3,(String) obj[2]);
				this.pstmt.setString(4,(String) obj[3]);
				this.pstmt.setString(5,(String) obj[4]);
				this.pstmt.setString(6,(String) obj[5]);
				this.pstmt.setString(7,(String) obj[6]);
				this.pstmt.setString(8,(String) obj[7]);
                        	this.pstmt.executeUpdate();
			}
			else{
				this.pstmt.setString(1,(String) obj[0]);
                                this.pstmt.setString(2,(String) obj[1]);
			}
                }catch(SQLException e){
                        e.printStackTrace();
                }finally{
                        try{
                                this.pstmt.close();
                        }catch(SQLException e){
                                e.printStackTrace();
                        }
                }
        }

        public void dbClose(){
                try{
                        this.conn.close();
                        System.out.println("Conexion cerrada");
                }catch(SQLException e){
                        e.printStackTrace();
                }
        }
}

