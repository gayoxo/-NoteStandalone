/**
 * 
 */
package anote;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import java.awt.Desktop;

import javax.swing.JTextPane;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JButton;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class JDialogPrincipal extends JFrame {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8631059775063764639L;
	static final String CommanDW="glassfish3/bin/asadmin.bat";
	 static final String CommanDU="glassfish3/bin/asadmin";
	private JTextPane PanelTexto;
	private StringBuffer SB;
	private boolean Iniciado;
	private JButton BotonReDeply;
	
	public JDialogPrincipal() {
		super();
		setSize(800, 600);
		setTitle("@note Alone Control Panel");
		getContentPane().setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane);
		
		PanelTexto = new JTextPane();
		PanelTexto.setEditable(false);
		SB=new StringBuffer();
		splitPane.setRightComponent(PanelTexto);
		
		JPanel PanelUsuarios = new JPanel();
		splitPane.setLeftComponent(PanelUsuarios);
		PanelUsuarios.setLayout(new GridLayout(1, 0, 0, 0));
		
		BotonReDeply = new JButton("Re-Deploy");
		BotonReDeply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BotonReDeply.setEnabled(false);
				ReStart();
			}
		});
		getContentPane().add(BotonReDeply, BorderLayout.SOUTH);
		Iniciado=false;
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				String Version= System.getProperty("java.version");
				System.out.println("PC Java Version:"+Version);
				SB.append("PC Java Version:"+Version+" \n");
				PanelTexto.setText(SB.toString());
				if (versionCorrecta(Version))
				StartC();
				else 
					System.out.println("Java Version incorrect:"+Version +" Minimun version requiered 1.6<");
				SB.append("Java Version incorrect:"+Version +" Minimun version requiered 1.6< \n");
				PanelTexto.setText(SB.toString());
			}
			
			private boolean versionCorrecta(String version) {
				if (version.startsWith("0"))
					return false;
				if (version.startsWith("1.0"))
					return false;
				if (version.startsWith("1.1"))
					return false;
				if (version.startsWith("1.2"))
					return false;
				if (version.startsWith("1.3"))
					return false;
				if (version.startsWith("1.4"))
					return false;
				if (version.startsWith("1.5"))
					return false;
//				if (version.startsWith("1.6"))
//					return false;
//				if (version.startsWith("1.7"))
//					return false;
//				if (version.startsWith("1.8"))
//					return false;
				
				return true;
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				if (Iniciado)
				{
				Thread TW=StopC();
				while (TW.isAlive())
					{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					}
				}
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	protected void ReStart() {
		Thread T=new Thread(new Runnable() {
			
			@Override
			public void run() {
				String CommanD;
				if (System.getProperty("os.name").startsWith("Windows"))
					CommanD=CommanDW;
				else
					CommanD=CommanDU;
				String [] cmdW = {CommanD,"deploy","GlassAtNote.war","-v"};
				String [] cmdNW = {CommanD,"undeploy","GlassAtNote.war","-v"};
				Process process;
				Process processW;
				try {	
					System.out.println("Undeploy @note...");
					SB.append("Undeploy @note... \n");
					PanelTexto.setText(SB.toString());
					process = Runtime.getRuntime().exec(cmdNW);
					InputStream inputstream = process.getInputStream();
					BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
					int c;
					while ((c = bufferedinputstream.read()) != -1)
						{
						System.out.print((char) c);
						SB.append((char) c);
						PanelTexto.setText(SB.toString());
						}
				
					Iniciado=true;

					System.out.println("@note UnDeployed");
					
					System.out.println("Deploy @note...");
					SB.append("Deploy @note... \n");
					PanelTexto.setText(SB.toString());
					processW = Runtime.getRuntime().exec(cmdW);
					InputStream inputstreamW = processW.getInputStream();
					BufferedInputStream bufferedinputstreamW = new BufferedInputStream(inputstreamW);
					int cW;
					while ((cW = bufferedinputstreamW.read()) != -1)
						{
						System.out.print((char) cW);
						}
					
					System.out.println("@note Deployed");
					SB.append("@note Deployed \n");
					PanelTexto.setText(SB.toString());
					
					
					SB.append("Direcciones Disponibles: \n");
					PanelTexto.setText(SB.toString());
					Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
					while(e.hasMoreElements())
					{
					    NetworkInterface n = (NetworkInterface) e.nextElement();
					    Enumeration<InetAddress> ee = n.getInetAddresses();
					    while (ee.hasMoreElements())
					    {
					        InetAddress i = (InetAddress) ee.nextElement();
					        if (!i.getHostAddress().startsWith("127")&&!i.getHostAddress().startsWith("0:")&&(!i.getHostAddress().startsWith("fe80:")))
					        {
						        SB.append(" - " + i.getHostAddress()+":8080/GlassAtNote \n");
								PanelTexto.setText(SB.toString());
						        System.out.println(i.getHostAddress()+":8080/GlassAtNote");
					        }
					    }
					}
					
					
				BotonReDeply.setEnabled(true);
				ProcessUsers();	
				} catch (IOException e) {
					System.out.println("Start Error");
					SB.append("Start Error \n");
					PanelTexto.setText(SB.toString());
					e.printStackTrace();
					Iniciado=false;
				}
				
				
			}
		});
		T.start();
		
	}


	private void StartC() {
		
			Thread T=new Thread(new Runnable() {
				
				@Override
				public void run() {
					String CommanD;
					if (System.getProperty("os.name").startsWith("Windows"))
						CommanD=CommanDW;
					else
						CommanD=CommanDU;
					String [] cmd = {CommanD,"start-domain","domain1"};
					String [] cmdW = {CommanD,"deploy","GlassAtNote.war","-v"};
					String [] cmdNW = {CommanD,"undeploy","GlassAtNote.war","-v"};
					Process process;
					Process processW;
					try {	
						System.out.println("Start System...");
						SB.append("Start System... \n");
						PanelTexto.setText(SB.toString());
						process = Runtime.getRuntime().exec(cmd);
						InputStream inputstream = process.getInputStream();
						BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
						int c;
						while ((c = bufferedinputstream.read()) != -1)
							{
							System.out.print((char) c);
							SB.append((char) c);
							PanelTexto.setText(SB.toString());
							}
						
						System.out.println("System Started");
						SB.append("System Started \n");
						PanelTexto.setText(SB.toString());
						Iniciado=true;
						
						try {
							Runtime.getRuntime().exec(cmdNW);
						} catch (Exception e) {

						}
						
						System.out.println("Deploy @note...");
						SB.append("Deploy @note... \n");
						PanelTexto.setText(SB.toString());
						processW = Runtime.getRuntime().exec(cmdW);
						InputStream inputstreamW = processW.getInputStream();
						BufferedInputStream bufferedinputstreamW = new BufferedInputStream(inputstreamW);
						int cW;
						while ((cW = bufferedinputstreamW.read()) != -1)
							{
							System.out.print((char) cW);
							}
						
						System.out.println("@note Deployed");
						SB.append("@note Deployed \n");
						PanelTexto.setText(SB.toString());
						
						
						SB.append("Direcciones Disponibles: \n");
						PanelTexto.setText(SB.toString());
						Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
						while(e.hasMoreElements())
						{
						    NetworkInterface n = (NetworkInterface) e.nextElement();
						    Enumeration<InetAddress> ee = n.getInetAddresses();
						    while (ee.hasMoreElements())
						    {
						        InetAddress i = (InetAddress) ee.nextElement();
						        if (!i.getHostAddress().startsWith("127")&&!i.getHostAddress().startsWith("0:")&&(!i.getHostAddress().startsWith("fe80:")))
						        {
							        SB.append(" - " + i.getHostAddress()+":8080/GlassAtNote \n");
									PanelTexto.setText(SB.toString());
							        System.out.println(i.getHostAddress()+":8080/GlassAtNote");
						        }
						    }
						}
						
						ProcessUsers();
						Desktop.getDesktop().browse(new URI("http://localhost:8080/GlassAtNote"));
						
					} catch (IOException | URISyntaxException e) {
						System.out.println("Start Error");
						SB.append("Start Error \n");
						PanelTexto.setText(SB.toString());
						e.printStackTrace();
						Iniciado=false;
					}
					
					
				}
			});
			T.start();
			


		}
	
	protected void ProcessUsers() {
		try {
			String s = "http://localhost:8080/GlassAtNote/rest/atnote/users";
			URL url = new URL(s);

			// read from the URL
			Scanner scan = new Scanner(url.openStream());
			String str = new String();
			while (scan.hasNext())
				str += scan.nextLine();
			scan.close();

			JsonParser parser = new JsonParser();
			JsonElement datos = parser.parse(str);
			
			ArrayList<UserJSon> Lista=new ArrayList<UserJSon>();
			
			dumpJSONElementBase(datos,Lista);
			
			System.out.println(Lista.size()+"");
		} catch (Exception e) {
			System.err.println("Error Usuarios");
			e.printStackTrace();
		}
		
				
	}


	private void dumpJSONElementBase(JsonElement elemento, ArrayList<UserJSon> lista) {
		if (elemento.isJsonArray()) {
	        JsonArray array = elemento.getAsJsonArray();
	        System.out.println("Es array. Numero de elementos: " + array.size());
	        java.util.Iterator<JsonElement> iter = array.iterator();
	        while (iter.hasNext()) {
	            JsonElement entrada = iter.next();
	            dumpJSONElementUser(entrada,lista);
	        }
	    }
		
	}

	private void dumpJSONElementUser(JsonElement elemento, ArrayList<UserJSon> lista) {
		if (elemento.isJsonObject()) {
	        System.out.println("Es objeto");
	        JsonObject obj = elemento.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        UserJSon nuevo=new UserJSon("", "");
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
	            System.out.println("Clave: " + entrada.getKey());
	            System.out.println("Valor:");
	            String valor =dumpJSONElementValue(entrada.getValue());
	            if (entrada.getKey().equals("User"))
	            	nuevo.setUser(valor);
	            else
	            	if (entrada.getKey().equals("Password"))
	            		nuevo.setPassword(valor);
	        }
	        lista.add(nuevo);
	 
	    } 	
	}
	
	private String dumpJSONElementValue(JsonElement elemento) {
		if (elemento.isJsonPrimitive()) {
	        System.out.println("Es primitiva");
	        JsonPrimitive valor = elemento.getAsJsonPrimitive();
	        if (valor.isBoolean()) {
	            System.out.println("Es booleano: " + valor.getAsBoolean());
	            return valor.getAsBoolean()+"";
	        } else if (valor.isNumber()) {
	            System.out.println("Es numero: " + valor.getAsNumber());
	            return valor.getAsNumber()+"";
	        } else if (valor.isString()) {
	            System.out.println("Es texto: " + valor.getAsString());
	            return valor.getAsString();
	        }
		}
	    
		return "";
	}
	
	
	private Thread StopC() {

		Thread T=new Thread(new Runnable() {
			
			@Override
			public void run() {
				String CommanD;
				if (System.getProperty("os.name").startsWith("Windows"))
					CommanD=CommanDW;
				else
					CommanD=CommanDU;
				System.out.println("Stop System...");
				SB.append("Stop System... \n");
				PanelTexto.setText(SB.toString());
				String [] cmd = {CommanD,"stop-domain","domain1"};
				String [] cmdNW = {CommanD,"undeploy","GlassAtNote.war","-v"};
				Process process;
				try {
					
					try {
						Runtime.getRuntime().exec(cmdNW);
					} catch (Exception e) {

					}
					
					process = Runtime.getRuntime().exec(cmd);
					InputStream inputstream = process.getInputStream();
					BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
					int c;
					while ((c = bufferedinputstream.read()) != -1)
							{
							System.out.print((char) c);
							SB.append((char) c);
							PanelTexto.setText(SB.toString());
							}
				} catch (IOException e) {
					System.err.println("Stop Error");
					SB.append("Stop Error \n");
					PanelTexto.setText(SB.toString());
					e.printStackTrace();
				}
				 
			}
		});
		T.start();
		return T;	

		}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JFrame Principal=new JDialogPrincipal();
			Principal.setVisible(true);
	    }
	    catch(UnsupportedClassVersionError error_version) {
	        System.err.println("Requeded Java Version 1.6< to depoy this aplication " + error_version.getMessage());
	    }
		
	

}
}
