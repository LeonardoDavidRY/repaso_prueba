package com.uce.edu.matriculacion.repository;

import com.uce.edu.matriculacion.repository.modelo.Propietario;

public interface IPropietarioRepository {
	
	public Propietario seleccionar(String cedula);
	public void insertar(Propietario propietario);
	public void actualizar(Propietario propietario);
	public void eliminar(String cedula);

}
