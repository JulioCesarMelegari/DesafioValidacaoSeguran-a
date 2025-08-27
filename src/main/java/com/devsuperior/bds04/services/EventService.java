package com.devsuperior.bds04.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional(readOnly = true)
	 public Page<EventDTO> findAll(Pageable pageable) {
		Page<Event> result = repository.findAll(pageable);
		return result.map(x -> new EventDTO(x));
	}
	
	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();
		//corrigir metodo : buscar o id da cidade no repository, se for nulo erro 422
		BeanUtils.copyProperties(dto, entity);
		entity = repository.save(entity);
		return new EventDTO(entity);
	}
	
}
