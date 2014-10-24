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
import java.util.Enumeration;

import javax.swing.JPanel;

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
	
	public JDialogPrincipal() {
		super();
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		setSize(800, 600);
		setTitle("@note Alone Control Panel");
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane);
		
		PanelTexto = new JTextPane();
		PanelTexto.setEditable(false);
		SB=new StringBuffer();
		splitPane.setRightComponent(PanelTexto);
		
		JPanel PanelUsuarios = new JPanel();
		splitPane.setLeftComponent(PanelUsuarios);
		PanelUsuarios.setLayout(new GridLayout(1, 0, 0, 0));
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
				Process process;
				try {
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
