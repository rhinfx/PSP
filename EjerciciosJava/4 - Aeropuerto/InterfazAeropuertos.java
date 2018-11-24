import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

public class InterfazAeropuertos extends JFrame {
	
	/**
	 * Método para leer los aeropuertos
	 */
	
	public static ArrayList<Aeropuerto> listaAeropuertos = new ArrayList<Aeropuerto>();
	
	public static void leerAeropuertos() {
		BufferedReader br = null;
		FileReader fr = null;
		

		try {

			fr = new FileReader("airports.dat");
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] aeropuertoAcutal = sCurrentLine.trim().split(",");
				
				if(aeropuertoAcutal.length == 14) {
	 				listaAeropuertos.add(new Aeropuerto(Integer.parseInt(aeropuertoAcutal[0]),
	 						aeropuertoAcutal[1],
	 						aeropuertoAcutal[2],
	 						aeropuertoAcutal[3],
	 						aeropuertoAcutal[4],
	 						Double.parseDouble(aeropuertoAcutal[6]),
	 						Double.parseDouble(aeropuertoAcutal[7])));
				}
				
				else {
					listaAeropuertos.add(new Aeropuerto(Integer.parseInt(aeropuertoAcutal[0]),
	 						aeropuertoAcutal[1],
	 						aeropuertoAcutal[2],
	 						aeropuertoAcutal[3],
	 						aeropuertoAcutal[4],
	 						Double.parseDouble(aeropuertoAcutal[7]),
	 						Double.parseDouble(aeropuertoAcutal[8])));
				}
				

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

	private JPanel contentPane;
	private JTextField txtEntrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazAeropuertos frame = new InterfazAeropuertos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazAeropuertos() {
		
		
		setTitle("Aeropuertos\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/**Cargamos los aeropuertos en la lista de Aeropuertos **/
		leerAeropuertos();
		
		txtEntrada = new JTextField();
		txtEntrada.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtEntrada.setBounds(48, 72, 483, 62);
		contentPane.add(txtEntrada);
		txtEntrada.setColumns(10);
		
		JLabel lblEntrada = new JLabel("Introduce los datos del Aeropuerto (pais, ciudad, abreviatura)");
		lblEntrada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEntrada.setBounds(48, 44, 483, 20);
		contentPane.add(lblEntrada);
		
		/**
		 * Creo un Jlist dentro de un ScrollPane para mostrar los resultados obtenidos en la búsqueda
		 */
		
		DefaultListModel<Aeropuerto> listModel = new DefaultListModel<Aeropuerto>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 132, 483, 338);
		contentPane.add(scrollPane);
		JList<Aeropuerto> listResultados = new JList<Aeropuerto>(listModel);
		
		scrollPane.setViewportView(listResultados);
		scrollPane.setVisible(false);
		listResultados.setVisible(false);
		listResultados.setBounds(48, 133, 483, 293);
		
		/**
		 * Listener del JList
		 * 
		 * Cuando hagamos click en un resultado se abrirá el navegador con 
		 * el google maps del aeropuerto		
		 */
		listResultados.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
			   if(e != null) {
				   
					   if (!e.getValueIsAdjusting()) {
						   
						   if(!listResultados.isSelectionEmpty()) {
							   try {
								   Desktop desktop = java.awt.Desktop.getDesktop();
								   URI oURL = new URI("https://www.google.com/maps?q="+listResultados.getSelectedValue().getLatitude()+","+listResultados.getSelectedValue().getLongitude());
								   desktop.browse(oURL);
							   } catch (Exception e1) {
								   e1.printStackTrace();
							   }
						   }

					   }
			   }	

			}
		});
		
		/**
		 * Listener del text box.
		 * 
		 * A medida que vayamos introduciendo datos iremos haciendo distitnas
		 * comprobaciones según lo que introduzca y mostraremos en la Jlist
		 * los resultados posibles.
		 */
		
		txtEntrada.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String[] entrada = txtEntrada.getText().toLowerCase().trim().replace(" ", "").split(",");
				
				if (!entrada[0].isEmpty()) {
				
					listResultados.setVisible(true);
					scrollPane.setVisible(true);
					listModel.removeAllElements();
					
					switch (entrada.length) {
				
				
					case 1: 
						for (Aeropuerto a : listaAeropuertos) {
							if (   a.getCountry().toLowerCase().contains(entrada[0])
									|| a.getCity().toLowerCase().contains(entrada[0])
									|| a.getCode().toLowerCase().contains(entrada[0])) {
							
								listModel.addElement(a);
							}
						}
						break;
					case 2:
						for (Aeropuerto a : listaAeropuertos) {
							if (   (a.getCountry().toLowerCase().contains(entrada[0])
									|| a.getCity().toLowerCase().contains(entrada[0])
									|| a.getCode().toLowerCase().contains(entrada[0]))
									
									&& 
											(a.getCountry().toLowerCase().contains(entrada[1])
											|| a.getCity().toLowerCase().contains(entrada[1])
											|| a.getCode().toLowerCase().contains(entrada[1]))) {

								listModel.addElement(a);
							}
						}
						break;
					case 3:
						for (Aeropuerto a : listaAeropuertos) {
							if (   (a.getCountry().toLowerCase().contains(entrada[0])
									|| a.getCity().toLowerCase().contains(entrada[0])
									|| a.getCode().toLowerCase().contains(entrada[0]))
									
									&& (a.getCountry().toLowerCase().contains(entrada[1])
											|| a.getCity().toLowerCase().contains(entrada[1])
											|| a.getCode().toLowerCase().contains(entrada[1]))
									
											&& (a.getCity().toLowerCase().contains(entrada[2])
													|| a.getCountry().toLowerCase().contains(entrada[2])
													|| a.getCode().toLowerCase().contains(entrada[2]))) {

								listModel.addElement(a);
							}
						}
						break;

					default:
						for (Aeropuerto a : listaAeropuertos) {
							if (   a.getCountry().toLowerCase().contains(entrada[0])
									|| a.getCity().toLowerCase().contains(entrada[0])
									|| a.getCode().toLowerCase().contains(entrada[0])) {
							
								System.out.println(entrada.length);
								listModel.addElement(a);
							}
						}
						break;
					}
				}
				
				else {
					listResultados.setVisible(false);
					scrollPane.setVisible(false);
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				
			}
		});
		

		
	}
}
