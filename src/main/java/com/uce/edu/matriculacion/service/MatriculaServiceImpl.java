package com.uce.edu.matriculacion.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.matriculacion.repository.IMatriculaRepository;
import com.uce.edu.matriculacion.repository.IPropietarioRepository;
import com.uce.edu.matriculacion.repository.IVehiculoRepository;
import com.uce.edu.matriculacion.repository.modelo.Matricula;
import com.uce.edu.matriculacion.repository.modelo.Propietario;
import com.uce.edu.matriculacion.repository.modelo.Vehiculo;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

	@Autowired
	private IMatriculaRepository iMatriculaRepository;
	
	@Override
	public Matricula buscar(String cedula, String placa) {
		// TODO Auto-generated method stub
		return this.iMatriculaRepository.seleccionar(cedula, placa);

	}

	@Override
	public void guardar(Matricula matricula) {
		// TODO Auto-generated method stub
		this.iMatriculaRepository.insertar(matricula);

	}

	@Override
	public void actualizar(Matricula matricula) {
		// TODO Auto-generated method
		this.iMatriculaRepository.actualizar(matricula);

	}

	@Override
	public void borrar(String cedula, String placa) {
		// TODO Auto-generated method stub
		this.iMatriculaRepository.eliminar(cedula, placa);

	}

	@Override
	public void generarMatricula(Propietario propietario, Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		if (this.buscar(propietario.getCedula(), vehiculo.getPlaca())==null) {
			// calculo del valor de matricula
			BigDecimal valorFinal=null;
			if (vehiculo.getTipo().toLowerCase().equals("pesado")) {
				BigDecimal valorPesado = new BigDecimal(0.15);
				System.out.println("El valor pesado es: " + valorPesado);
				valorFinal = vehiculo.getPrecio().multiply(valorPesado);
				System.out.println("El valor final es: " + valorFinal);
			} else if (vehiculo.getTipo().toLowerCase().equals("liviano")) {
				BigDecimal valorLiviano = new BigDecimal(0.10);
				System.out.println("El valor pesado es: " + valorLiviano);
				valorFinal = vehiculo.getPrecio().multiply(valorLiviano);
				System.out.println("El valor final es: " + valorFinal);
			}else {
				System.out.println("No entra en ningun tipo de vehiculo");
			}
			BigDecimal valorMaximo = new BigDecimal(2000);
			if(valorFinal.compareTo(valorMaximo)==1) {
				BigDecimal descuento = valorFinal.multiply(new BigDecimal(0.07));
				System.out.println("El descuento es: "+ descuento);
				valorFinal = valorFinal.subtract(descuento);
				
			}
			// crea la matricula
			Matricula tmp = new Matricula();
			tmp.setFechaMatricula(LocalDateTime.now());
			tmp.setPropietario(propietario);
			tmp.setValorMatricula(valorFinal);
			tmp.setVehiculo(vehiculo);
			
			this.guardar(tmp);
			System.out.println("Se guardo:"+tmp);

		} else {
			System.out.println("Este vehiculo ya esta registrado el sistema");
		}
		
	}

}
