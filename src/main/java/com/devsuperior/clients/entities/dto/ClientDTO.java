package com.devsuperior.clients.entities.dto;

import java.time.LocalDate;

import com.devsuperior.clients.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class ClientDTO {
	
	private Long id;
	
	@Size(min = 3, max = 70, message = "Nome precisar ter de 3 a 70 caracteres")
    @NotBlank(message = "Campo requerido")
	private String name;
	
	private String cpf;
	
	private Double income;
	
	@PastOrPresent
	private LocalDate birthDate;
	
	private  Integer children;
	
	public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	 public ClientDTO(Client entity) {
	        id = entity.getId();
	        name = entity.getName();
	        cpf = entity.getCpf();
	        income = entity.getIncome();
	        birthDate = entity.getBirthDate();
	        children = entity.getChildren();
	        
	    }

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCpf() {
		return cpf;
	}
	public Double getIncome() {
		return income;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public Integer getChildren() {
		return children;
	}
	
	

}
