package com.uce.edu.matriculacion.repository;

import com.uce.edu.matriculacion.repository.modelo.Vehiculo;

public interface IVehiculoRepository {
	
	public Vehiculo seleccionar(String placa);
	public void insertar(Vehiculo vehiculo);
	public void actualizar(Vehiculo vehiculo);
	public void eliminar(String placa);

}
