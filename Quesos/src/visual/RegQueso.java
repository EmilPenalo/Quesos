package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cilindro;
import logico.CilindroHueco;
import logico.Empresa;
import logico.Esfera;
import logico.Queso;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;

public class RegQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Queso selected = null;
	private JTextField txtCodigo;
	private JPanel panelHueco;
	private JPanel panelEsfera;
	private JPanel panelCilindro;
	private JRadioButton rdbtnEsfera;
	private JRadioButton rdbtnCilindro;
	private JRadioButton rdbtnHueco;
	private JSpinner spnPrecioBase;
	private JSpinner spnUnitario;
	private JSpinner spnRadioEsfera;
	private JSpinner spnRadioHueco;
	private JSpinner spnLongitudHueco;
	private JSpinner spnRadioInterno;
	private JSpinner spnLongitudCilindro;
	private JSpinner spnRadioCilindro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegQueso dialog = new RegQueso(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegQueso(Queso queso) {
		selected = queso;
		
		if (selected == null) {
			setTitle("Registrar Queso");

		} else {
			setTitle("Modificar Queso");
		}
		
		setBounds(100, 100, 450, 445);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panelGeneral = new JPanel();
			panelGeneral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion general", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelGeneral.setBounds(15, 16, 388, 146);
			panel.add(panelGeneral);
			panelGeneral.setLayout(null);
			
			JLabel label = new JLabel("Codigo:");
			label.setBounds(15, 33, 69, 20);
			panelGeneral.add(label);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(105, 28, 146, 30);
			if (selected == null) {
				txtCodigo.setText("Q-" + Queso.codigo);
			} else {
				txtCodigo.setText(selected.getId());
			}
			panelGeneral.add(txtCodigo);
			{
				JLabel lblBase = new JLabel("Precio base:");
				lblBase.setBounds(15, 69, 110, 20);
				panelGeneral.add(lblBase);
			}
			
			spnPrecioBase = new JSpinner();
			spnPrecioBase.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnPrecioBase.setBounds(105, 64, 146, 30);
			panelGeneral.add(spnPrecioBase);
			
			JLabel lblUnitario = new JLabel("Precio unitario:");
			lblUnitario.setBounds(15, 105, 110, 20);
			panelGeneral.add(lblUnitario);
			
			spnUnitario = new JSpinner();
			spnUnitario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnUnitario.setBounds(105, 100, 146, 30);
			panelGeneral.add(spnUnitario);
			
			JPanel panelBtns = new JPanel();
			panelBtns.setBorder(new TitledBorder(null, "Tipo de Queso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelBtns.setBounds(15, 178, 388, 60);
			panel.add(panelBtns);
			panelBtns.setLayout(null);
			
			rdbtnEsfera = new JRadioButton("Esfera");
			rdbtnEsfera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelEsfera.setVisible(true);
					panelCilindro.setVisible(false);
					panelHueco.setVisible(false);
					
					rdbtnEsfera.setSelected(true);
					rdbtnCilindro.setSelected(false);
					rdbtnHueco.setSelected(false);
				}
			});
			rdbtnEsfera.setSelected(true);
			rdbtnEsfera.setBounds(24, 19, 75, 29);
			panelBtns.add(rdbtnEsfera);
			
			rdbtnCilindro = new JRadioButton("Cilindro");
			rdbtnCilindro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelEsfera.setVisible(false);
					panelCilindro.setVisible(true);
					panelHueco.setVisible(false);
					
					rdbtnEsfera.setSelected(false);
					rdbtnCilindro.setSelected(true);
					rdbtnHueco.setSelected(false);
				}
			});
			rdbtnCilindro.setBounds(123, 19, 87, 29);
			panelBtns.add(rdbtnCilindro);
			
			rdbtnHueco = new JRadioButton("Cilindro hueco");
			rdbtnHueco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panelEsfera.setVisible(false);
					panelCilindro.setVisible(false);
					panelHueco.setVisible(true);
					
					rdbtnEsfera.setSelected(false);
					rdbtnCilindro.setSelected(false);
					rdbtnHueco.setSelected(true);
				}
			});
			rdbtnHueco.setBounds(234, 19, 130, 29);
			panelBtns.add(rdbtnHueco);
			
			panelEsfera = new JPanel();
			panelEsfera.setBounds(15, 254, 388, 85);
			panel.add(panelEsfera);
			panelEsfera.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEsfera.setLayout(null);
			
			JLabel lblRadioEsfera = new JLabel("Radio:");
			lblRadioEsfera.setBounds(15, 16, 69, 20);
			panelEsfera.add(lblRadioEsfera);
			
			spnRadioEsfera = new JSpinner();
			spnRadioEsfera.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnRadioEsfera.setBounds(70, 11, 100, 30);
			panelEsfera.add(spnRadioEsfera);
			
			panelHueco = new JPanel();
			panelHueco.setVisible(false);
			panelHueco.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelHueco.setBounds(15, 254, 388, 85);
			panel.add(panelHueco);
			panelHueco.setLayout(null);
			
			JLabel lblRadioHueco = new JLabel("Radio:");
			lblRadioHueco.setBounds(15, 16, 69, 20);
			panelHueco.add(lblRadioHueco);
			
			spnRadioHueco = new JSpinner();
			spnRadioHueco.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnRadioHueco.setBounds(70, 11, 100, 30);
			panelHueco.add(spnRadioHueco);
			
			JLabel lblLongitudHueco = new JLabel("Longitud:");
			lblLongitudHueco.setBounds(15, 52, 69, 20);
			panelHueco.add(lblLongitudHueco);
			
			spnLongitudHueco = new JSpinner();
			spnLongitudHueco.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnLongitudHueco.setBounds(70, 47, 100, 30);
			panelHueco.add(spnLongitudHueco);
			
			JLabel lblRadioInterno = new JLabel("Radio interno:");
			lblRadioInterno.setBounds(185, 16, 108, 20);
			panelHueco.add(lblRadioInterno);
			
			spnRadioInterno = new JSpinner();
			spnRadioInterno.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnRadioInterno.setBounds(273, 11, 100, 30);
			panelHueco.add(spnRadioInterno);
			
			panelCilindro = new JPanel();
			panelCilindro.setVisible(false);
			panelCilindro.setBounds(15, 254, 388, 85);
			panel.add(panelCilindro);
			panelCilindro.setLayout(null);
			panelCilindro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			JLabel lblLongitudCilindro = new JLabel("Longitud:");
			lblLongitudCilindro.setBounds(15, 52, 69, 20);
			panelCilindro.add(lblLongitudCilindro);
			
			spnLongitudCilindro = new JSpinner();
			spnLongitudCilindro.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnLongitudCilindro.setBounds(70, 47, 100, 30);
			panelCilindro.add(spnLongitudCilindro);
			
			JLabel lblRadioCilindro = new JLabel("Radio:");
			lblRadioCilindro.setBounds(15, 16, 46, 20);
			panelCilindro.add(lblRadioCilindro);
			
			spnRadioCilindro = new JSpinner();
			spnRadioCilindro.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnRadioCilindro.setBounds(70, 11, 100, 30);
			panelCilindro.add(spnRadioCilindro);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				if (selected == null) {
					okButton.setText("Registrar");
				} else {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected == null) {
							boolean valido = false;
							
							Queso aux = null;
							String id = txtCodigo.getText();
							float precioBase = new Float(spnPrecioBase.getValue().toString());
							float precioUnitario = new Float(spnUnitario.getValue().toString());
							
							if (rdbtnEsfera.isSelected()) {
								int radio = new Integer(spnRadioEsfera.getValue().toString());
								aux = new Esfera(id, precioBase, precioUnitario, radio);
								valido = true;
							}
							
							if (rdbtnCilindro.isSelected()) {
								int radio = new Integer(spnRadioCilindro.getValue().toString());
								int longitud = new Integer(spnLongitudCilindro.getValue().toString());
								aux = new Cilindro(id, precioBase, precioUnitario, radio, longitud);
								valido = true;
							}
							
							if (rdbtnHueco.isSelected()) {
								int radio = new Integer(spnRadioHueco.getValue().toString());
								int longitud = new Integer(spnLongitudHueco.getValue().toString());
								int radioInterno = new Integer(spnRadioInterno.getValue().toString());
								
								if (radioInterno < radio) {
									aux = new CilindroHueco(id, precioBase, precioUnitario, radio, longitud, radioInterno);
									valido = true;
								} else {
									JOptionPane.showMessageDialog(null, "El radio externo debe ser mayor que el radio interno del queso", "Error de Registro", JOptionPane.WARNING_MESSAGE);
								}
							}
							if (valido) {
								Empresa.getInstance().insettarQueso(aux);
								JOptionPane.showMessageDialog(null, "Registrado satisfactoriamente", "Registro de queso", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
						} else {
							selected.setPrecioBase(new Float(spnPrecioBase.getValue().toString()));
							selected.setPrecioUnitario(new Float(spnUnitario.getValue().toString()));
							
							if (selected instanceof Esfera) {
								((Esfera) selected).setRadio(new Integer(spnRadioEsfera.getValue().toString()));
							}
							
							if (selected instanceof Cilindro) {
								((Cilindro) selected).setRadio(new Integer(spnRadioCilindro.getValue().toString()));
								((Cilindro) selected).setLongitud(new Integer(spnLongitudCilindro.getValue().toString()));
							}
							
							if (selected instanceof CilindroHueco) {
								((CilindroHueco) selected).setRadio(new Integer(spnRadioHueco.getValue().toString()));
								((CilindroHueco) selected).setLongitud(new Integer(spnLongitudHueco.getValue().toString()));
								((CilindroHueco) selected).setRadioInterno(new Integer(spnRadioInterno.getValue().toString()));
							}
							ListQueso.loadTable(0);
							dispose();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			loadQueso();
		}
	}

	private void loadQueso() {
		if (selected != null) {
			txtCodigo.setText(selected.getId());
			spnPrecioBase.setValue(selected.getPrecioBase());
			spnUnitario.setValue(selected.getPrecioUnitario());
			
			if (selected instanceof Esfera) {
				rdbtnEsfera.setSelected(true);
				rdbtnCilindro.setSelected(false);
				rdbtnHueco.setSelected(false);
				panelEsfera.setVisible(true);
				panelCilindro.setVisible(false);
				panelHueco.setVisible(false);
				
				rdbtnCilindro.setEnabled(false);
				rdbtnHueco.setEnabled(false);
				
				spnRadioEsfera.setValue(((Esfera) selected).getRadio());
			}
			
			if (selected instanceof Cilindro) {
				rdbtnEsfera.setSelected(false);
				rdbtnCilindro.setSelected(true);
				rdbtnHueco.setSelected(false);
				panelEsfera.setVisible(false);
				panelCilindro.setVisible(true);
				panelHueco.setVisible(false);
				
				rdbtnEsfera.setEnabled(false);
				rdbtnHueco.setEnabled(false);
				
				spnRadioCilindro.setValue(((Cilindro) selected).getRadio());
				spnLongitudCilindro.setValue(((Cilindro) selected).getLongitud());
			}
			
			if (selected instanceof CilindroHueco) {
				rdbtnEsfera.setSelected(false);
				rdbtnCilindro.setSelected(false);
				rdbtnHueco.setSelected(true);
				panelEsfera.setVisible(false);
				panelCilindro.setVisible(false);
				panelHueco.setVisible(true);
				
				rdbtnCilindro.setEnabled(false);
				rdbtnEsfera.setEnabled(false);
				
				spnRadioHueco.setValue(((CilindroHueco) selected).getRadio());
				spnLongitudHueco.setValue(((CilindroHueco) selected).getLongitud());
				spnRadioInterno.setValue(((CilindroHueco) selected).getRadioInterno());
			}
		}
	}

	private void clean() {
		txtCodigo.setText("Q-" + Queso.codigo);
		spnPrecioBase.setValue(0);
		spnUnitario.setValue(0);
		spnRadioEsfera.setValue(0);
		spnRadioCilindro.setValue(0);
		spnRadioHueco.setValue(0);
		spnRadioInterno.setValue(0);
		spnLongitudCilindro.setValue(0);
		spnLongitudHueco.setValue(0);
	}
}
