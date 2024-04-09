package com.devsuperior.clients.entities.servicesexceptios;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

}
