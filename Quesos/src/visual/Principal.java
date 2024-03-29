package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Empresa;
import logico.Servidor;

import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	
	private static Socket sfd = null;
	private static DataInputStream EntradaSocket;
	private static DataOutputStream SalidaSocket;

	public static DataInputStream getEntradaSocket() {
		return EntradaSocket;
	}

	public static DataOutputStream getSalidaSocket() {
		return SalidaSocket;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				FileInputStream empresaInput;
				FileOutputStream empresaOutput;
				ObjectInputStream empresaReader;
				ObjectOutputStream empresaWriter;
					
				try {
					
					empresaInput = new FileInputStream ("empresa.dat");
					empresaReader = new ObjectInputStream(empresaInput);
					Empresa temp = (Empresa) empresaReader.readObject();
					Empresa.setTienda(temp);
					
					empresaInput.close();
					empresaReader.close();
					
				} catch (FileNotFoundException e) {
					
					try {
						
						empresaOutput = new  FileOutputStream("empresa.dat");
						empresaWriter = new ObjectOutputStream(empresaOutput);

						empresaWriter.writeObject(Empresa.getInstance());
						
						empresaOutput.close();
						empresaWriter.close();
						
					} catch (FileNotFoundException e1) {
						e.printStackTrace();
					} catch (IOException e1) {
						e.printStackTrace();
					}
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				

				try
			    {
			      sfd = new Socket("127.0.0.1",8000);
			      EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			      SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
			    }
			    catch (UnknownHostException uhe)
			    {
				  JOptionPane.showMessageDialog(null, "No se puede acceder al servidor.", "Error", JOptionPane.ERROR_MESSAGE);
			      System.exit(1);
			    }
			    catch (IOException ioe)
			    {
				  JOptionPane.showMessageDialog(null, "Comunicación rechazada.", "Error", JOptionPane.ERROR_MESSAGE);

			      System.exit(1);
			    } 
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream empresaOutput;
				ObjectOutputStream empresaWrite;
				
				if (sfd != null) {
					try {
					  sfd.close();
					} catch (IOException ioe) {
					  System.out.println("Error: "+ioe);
					}
				}
				
				try {
					empresaOutput = new  FileOutputStream("empresa.dat");
					empresaWrite = new ObjectOutputStream(empresaOutput);
					empresaWrite.writeObject(Empresa.getInstance());
					
					empresaOutput.close();
					empresaWrite.close();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
		
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setTitle("Registro de Quesos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 398);
		
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-35);
		
		setLocationRelativeTo(null);
		
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCliente listc = new ListCliente();
				listc.setModal(true);
				listc.setVisible(true);
			}
		});
		mnClientes.add(mntmNewMenuItem);
		
		JMenu mnQuesos = new JMenu("Quesos");
		menuBar.add(mnQuesos);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegQueso regQ = new RegQueso(null);
				regQ.setModal(true);
				regQ.setVisible(true);
			}
		});
		mnQuesos.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListQueso listQ = new ListQueso(null);
				listQ.setModal(true);
				listQ.setVisible(true);
			}
		});
		mnQuesos.add(mntmNewMenuItem_3);
		
		JMenu mnPedidos = new JMenu("Pedidos");
		menuBar.add(mnPedidos);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Hacer pedido");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HacerPedido pedido = new HacerPedido();
				pedido.setModal(true);
				pedido.setVisible(true);
			}
		});
		mnPedidos.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Listado de Facturas");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFactura fact = new ListFactura(null);
				fact.setModal(true);
				fact.setVisible(true);
			}
		});
		mnPedidos.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(10);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1, BorderLayout.SOUTH);
		
	}

}
