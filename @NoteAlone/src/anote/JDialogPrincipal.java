/**
 * 
 */
package anote;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import java.awt.Desktop;
import java.awt.Image;

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
import java.awt.Label;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.FlowLayout;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class JDialogPrincipal extends JFrame {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8631059775063764639L;
	static final String CommanDW="glassfish3\\bin\\asadmin.bat";
	 static final String CommanDU="glassfish3/bin/asadmin";
	private JTextPane PanelTexto;
	private StringBuffer SB;
	private boolean Iniciado;
	private JButton BotonReDeply;
	private JPanel panelIcon;
	private JPanel PanelUsuarios;
	private JPanel panel;
	private Label label;
	private Label label_1;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JPanel PanelUsuariosR;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	
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
		
		JScrollPane scrollPaneTexto = new JScrollPane(); 
		splitPane.setRightComponent(scrollPaneTexto);
		scrollPaneTexto.add(PanelTexto);
		
		PanelUsuarios = new JPanel();
		PanelUsuarios.setBorder(new LineBorder(new Color(0, 0, 0)));
		splitPane.setLeftComponent(PanelUsuarios);
		PanelUsuarios.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelUsuarios.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel_3);
		
		label = new Label("User");
		panel_3.add(label);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel_4);
		
		label_1 = new Label("Password");
		panel_4.add(label_1);
		
		scrollPane = new JScrollPane();
		PanelUsuarios.add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		PanelUsuariosR = new JPanel();
		panel_1.add(PanelUsuariosR);
		PanelUsuariosR.setLayout(new GridLayout(1, 2, 0, 0));
		
		BotonReDeply = new JButton("Re-Deploy");
		BotonReDeply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BotonReDeply.setEnabled(false);
				ReStart();
			}
		});
		getContentPane().add(BotonReDeply, BorderLayout.SOUTH);
		
		
		ImageIcon imagenFondo=new ImageIcon();
		try {
//			BufferedImage myPicture = ImageIO.read(new File("Logo.jpg"));
//			imagenFondo = new ImageIcon(myPicture);
			imagenFondo = new ImageIcon(JDialogPrincipal.class.getResource("/anote/Logo.jpg"));
		} catch (Exception e) {
			
		}
		
		Image Image = imagenFondo.getImage().getScaledInstance(140, 70,  java.awt.Image.SCALE_SMOOTH);  
		JLabel picLabel = new JLabel( new ImageIcon(Image));
		panelIcon = new JPanel();
		getContentPane().add(panelIcon, BorderLayout.NORTH);	
		panelIcon.add(picLabel);
		
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
			
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
				
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
				String [] cmdW = {CommanD,"deploy","GlassAtNote.war"};
				String [] cmdNW = {CommanD,"undeploy","GlassAtNote"};
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
				
					InputStream inputstreamWEU = process.getErrorStream();
					BufferedInputStream bufferedinputstreamWEU = new BufferedInputStream(inputstreamWEU);
					int cWEU;
					while ((cWEU = bufferedinputstreamWEU.read()) != -1)
						{
						System.out.print((char) cWEU);
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
					
					
					InputStream inputstreamWE = processW.getErrorStream();
					BufferedInputStream bufferedinputstreamWE = new BufferedInputStream(inputstreamWE);
					int cWE;
					while ((cWE = bufferedinputstreamWE.read()) != -1)
						{
						System.out.print((char) cWE);
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
					        if (!i.getHostAddress().startsWith("127")&&(!i.getHostAddress().contains(":")))
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
					String [] cmdW = {CommanD,"deploy","GlassAtNote.war"};
					String [] cmdNW = {CommanD,"undeploy","GlassAtNote"};
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
							Process processU = Runtime.getRuntime().exec(cmdNW);
							
							InputStream inputstreamU = processU.getInputStream();
							BufferedInputStream bufferedinputstreamU = new BufferedInputStream(inputstreamU);
							int cU;
							while ((cU = bufferedinputstreamU.read()) != -1)
								{
								System.out.print((char) cU);
								}
							
							InputStream inputstreamWEU = processU.getErrorStream();
							BufferedInputStream bufferedinputstreamWEU = new BufferedInputStream(inputstreamWEU);
							int cWEU;
							while ((cWEU = bufferedinputstreamWEU.read()) != -1)
								{
								System.out.print((char) cWEU);
								}
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
						
						InputStream inputstreamWE = processW.getErrorStream();
						BufferedInputStream bufferedinputstreamWE = new BufferedInputStream(inputstreamWE);
						int cWE;
						while ((cWE = bufferedinputstreamWE.read()) != -1)
							{
							System.out.print((char) cWE);
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
			
			PanelUsuariosR.removeAll();
			
			if (Lista.size()>1)
			{
				PanelUsuariosR.setLayout(new GridLayout(Lista.size(),2));
			for (int i = 0; i < Lista.size(); i++) {
				UserJSon UJ=Lista.get(i);
				JPanel panel_U = new JPanel();
				panel_U.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_U.add(new JLabel(UJ.getUser()+"  "));
				PanelUsuariosR.add(panel_U);
				
				JLabel Pass=new JLabelP(UJ.getPassword());
				
				JPanel panel_P = new JPanel();
				panel_P.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_P.add(Pass);
				PanelUsuariosR.add(panel_P);
			}
			}
			PanelUsuariosR.repaint();
			this.repaint();
			PanelUsuarios.repaint();
			SB.append("User Loaded \n");
			PanelTexto.setText(SB.toString());
			System.out.println("User Loaded");
		} catch (Exception e) {
			System.err.println("Error Usuarios, please Redeploy @note");
			SB.append("Error Usuarios, please Redeploy @note \n");
			PanelTexto.setText(SB.toString());
			e.printStackTrace();
		}
		
				
	}


	private void dumpJSONElementBase(JsonElement elemento, ArrayList<UserJSon> lista) {
		if (elemento.isJsonArray()) {
	        JsonArray array = elemento.getAsJsonArray();
	       // System.out.println("Es array. Numero de elementos: " + array.size());
	        java.util.Iterator<JsonElement> iter = array.iterator();
	        while (iter.hasNext()) {
	            JsonElement entrada = iter.next();
	            dumpJSONElementUser(entrada,lista);
	        }
	    }
		
	}

	private void dumpJSONElementUser(JsonElement elemento, ArrayList<UserJSon> lista) {
		if (elemento.isJsonObject()) {
	       // System.out.println("Es objeto");
	        JsonObject obj = elemento.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        UserJSon nuevo=new UserJSon("", "");
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
	         //   System.out.println("Clave: " + entrada.getKey());
	         //   System.out.println("Valor:");
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
	       // System.out.println("Es primitiva");
	        JsonPrimitive valor = elemento.getAsJsonPrimitive();
	        if (valor.isBoolean()) {
	       //     System.out.println("Es booleano: " + valor.getAsBoolean());
	            return valor.getAsBoolean()+"";
	        } else if (valor.isNumber()) {
	       //     System.out.println("Es numero: " + valor.getAsNumber());
	            return valor.getAsNumber()+"";
	        } else if (valor.isString()) {
	        //    System.out.println("Es texto: " + valor.getAsString());
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
				String [] cmdNW = {CommanD,"undeploy","GlassAtNote"};
				Process process;
				try {
					
					try {
						Process processU = Runtime.getRuntime().exec(cmdNW);
						
						InputStream inputstreamU = processU.getInputStream();
						BufferedInputStream bufferedinputstreamU = new BufferedInputStream(inputstreamU);
						int cU;
						while ((cU = bufferedinputstreamU.read()) != -1)
							{
							System.out.print((char) cU);
							}
						
						InputStream inputstreamWEU = processU.getErrorStream();
						BufferedInputStream bufferedinputstreamWEU = new BufferedInputStream(inputstreamWEU);
						int cWEU;
						while ((cWEU = bufferedinputstreamWEU.read()) != -1)
							{
							System.out.print((char) cWEU);
							}
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
