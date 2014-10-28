package anote;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class JLabelP extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ASTERISCOS = "*******";
	private String Password;

	public JLabelP(String password) {
		super(ASTERISCOS);
		Password=password;
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setText(ASTERISCOS);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setText(Password);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

				
			}
		});
	}

}
