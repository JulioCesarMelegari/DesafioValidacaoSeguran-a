package com.devsuperior.bds04.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable) {
		Page<Event> result = eventRepository.findAll(pageable);
		return result.map(x -> new EventDTO(x));
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();
		if (!cityRepository.existsById(dto.getCityId())) {
			throw new ResourceNotFoundException("Cidade não cadastrada");
		} else {
			BeanUtils.copyProperties(dto, entity);
			City city = cityRepository.findById(dto.getCityId()).get();
			entity.setCity(city);
			entity = eventRepository.save(entity);
			return new EventDTO(entity);
		}
	}

	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = eventRepository.getReferenceById(id);
			City city = cityRepository.findById(dto.getCityId())
					.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada"));
			entity.setCity(city);
			entity.setDate(dto.getDate());
			entity.setId(id);
			entity.setName(dto.getName());
			entity.setUrl(dto.getUrl());
			entity = eventRepository.save(entity);
			return new EventDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Evento não encontrado");
		}
	}

}
