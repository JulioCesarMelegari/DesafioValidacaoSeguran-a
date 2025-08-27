package com.devsuperior.bds04.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll(){
		List<City> result = repository.listAllOrderByName();
		return result.stream().map(x -> new CityDTO(x)).toList();
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		BeanUtils.copyProperties(dto, entity);
		entity = repository.save(entity);
		return new CityDTO(entity);
	}
	/*
    @Transactional
    public CityDTO update(Long id, CityDTO dto) {
        try {
        	City entity = repository.getReferenceById(id);
        	BeanUtils.copyProperties(dto, entity);
            entity = repository.save(entity);
            return new CityDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }
	*/
}
