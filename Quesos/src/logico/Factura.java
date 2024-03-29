package logico;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private Date fecha;
	private Cliente cliente;
	private ArrayList<Queso> quesos;
	public static int codigo = 1;
	
	public Factura(String id, Cliente cliente, ArrayList<Queso> quesos) {
		super();
		this.id = id;
		this.fecha = new Date();
		this.cliente = cliente;
		this.quesos = quesos;
		Factura.codigo++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Queso> getQuesos() {
		return quesos;
	}

	public void setQuesos(ArrayList<Queso> quesos) {
		this.quesos = quesos;
	}
	
	public float precioTotal() {
		float precio = 0;
		for (Queso q : quesos) {
			precio += q.precio();
		}
		return precio;
	}
	
	public String toText() {
		String text = new String();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");  
		String strFecha = dateFormat.format(fecha);  
		
		text = "ID: " + id + "\n";
		text += "Fecha: " + strFecha + "\n";
		text += "Cliente: " + cliente.getNombre() + "\n";
		text += "Quesos: \n";
		
		text += "\tID Queso    Volumen      Precio Unitario\n";
		for (Queso queso : quesos) {
			text += String.format("\t %-8s    %-10.4f    %-10.4f\n", queso.id, queso.volumen(), queso.precioUnitario);
		}
		
		text += "Total: RD$" + precioTotal();
		
		return text;
	}
}
