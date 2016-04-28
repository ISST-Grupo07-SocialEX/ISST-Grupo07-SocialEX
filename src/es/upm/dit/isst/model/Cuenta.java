package es.upm.dit.isst.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
public class Cuenta implements Serializable {
	@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Id Long id;
	private String cliente = "";
	private String paisCuenta = "";
	private String divisaPredeterminada = "";
	private String tarjetaAsociada = "";
	private String titularTarjeta = "";
	private String caducidadTarjeta = "";
	private String fechaCreacion = "";
	@OneToOne(fetch=FetchType.EAGER) 
	private Map<String, Double> saldo = new HashMap<String, Double>();
	
	public Cuenta(String cliente, String paisCuenta,
			String divisaPredeterminada, String tarjetaAsociada, String titularTarjeta, String caducidadTarjeta) {
		super();
		this.cliente = cliente;
		this.paisCuenta = paisCuenta;
		this.divisaPredeterminada = divisaPredeterminada;
		this.tarjetaAsociada = tarjetaAsociada;
		//4 monedas iniciales
		this.saldo.put("EUR", 0.0);
		this.saldo.put("GBP", 0.0);
		this.saldo.put("USD", 0.0);
		this.saldo.put("JPY", 0.0);
					
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format1.format(hoy.getTime());
		this.fechaCreacion = formatted;
		
		this.caducidadTarjeta = caducidadTarjeta;
		this.titularTarjeta = titularTarjeta;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getCaducidadTarjeta() {
		return caducidadTarjeta;
	}

	public void setCaducidadTarjeta(String caducidadTarjeta) {
		this.caducidadTarjeta = caducidadTarjeta;
	}

	public long getNumeroCuenta() {
		return id;
	}

	/*public void setNumeroCuenta(long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}*/
	
	public String getCliente() {
		return cliente;
	}
	/*
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	*/

	public String getPaisCuenta() {
		return paisCuenta;
	}

	public void setPaisCuenta(String paisCuenta) {
		this.paisCuenta = paisCuenta;
	}

	public String getDivisaPredeterminada() {
		return divisaPredeterminada;
	}

	public void setDivisaPredeterminada(String divisaPredeterminada) {
		this.divisaPredeterminada = divisaPredeterminada;
	}

	public String getTarjetaAsociada() {
		return tarjetaAsociada;
	}

	public void setTarjetaAsociada(String tarjetaAsociada) {
		this.tarjetaAsociada = tarjetaAsociada;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Map<String, Double> getSaldo() {
		return saldo;
	}

	public void setSaldo(Map<String, Double> saldo) {
		this.saldo = saldo;
	}
	
	public Double getSaldo(String moneda) {
		return this.saldo.get(moneda);
	}

	public void setNewModeda(Map<String, Double> moneda) {
		this.saldo = saldo;
	}
	
	public void addSaldo(String moneda, Double cantidad) {
		Double oldSaldo = this.saldo.get(moneda);
		saldo.put(moneda, oldSaldo+cantidad);
	}
	
}