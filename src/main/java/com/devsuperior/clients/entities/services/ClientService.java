package com.devsuperior.clients.entities.services;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.clients.entities.Client;
import com.devsuperior.clients.entities.dto.ClientDTO;
import com.devsuperior.clients.entities.repositories.ClienteRepository;
import com.devsuperior.clients.entities.servicesexceptios.DatabaseException;
import com.devsuperior.clients.entities.servicesexceptios.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ClientService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(product);
    }
	
	@Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }
	@Transactional(readOnly = true)
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		DtoToEntityMapper(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
		
	}
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
	    try {
	        Client entity = repository.getReferenceById(id);
	        DtoToEntityMapper(dto, entity); 
	        entity = repository.save(entity);
	        return new ClientDTO(entity);
	    } catch (EntityNotFoundException e) {
	        throw new ResourceNotFoundException("Recurso não encontrado");
	    }
	}
	 @Transactional(propagation = Propagation.SUPPORTS)
	    public void delete(Long id) {
	    	if (!repository.existsById(id)) {
	    		throw new ResourceNotFoundException("Recurso não encontrado");
	    	}
	    	try {
	            repository.deleteById(id);    		
	    	}
	        catch (DataIntegrityViolationException e) {
	            throw new DatabaseException("Falha de integridade referencial");
	        }
	    }

	
	private void NotFoundException(String string) {
		// TODO Auto-generated method stub
		
	}

	private void DtoToEntityMapper(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
	
	

	

}
