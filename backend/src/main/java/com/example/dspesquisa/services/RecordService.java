package com.example.dspesquisa.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dspesquisa.dto.RecordDTO;
import com.example.dspesquisa.dto.RecordInsertDTO;
import com.example.dspesquisa.entities.Game;
import com.example.dspesquisa.entities.Record;
import com.example.dspesquisa.repositories.GameRepository;
import com.example.dspesquisa.repositories.RecordRepository;

@Service
public class RecordService {
	
	@Autowired	
	private RecordRepository repository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional
	public RecordDTO insert(RecordInsertDTO dto) {
		
		Record entity = new Record();
		
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now());
		
		Game game = gameRepository.getOne(dto.getGameId());
		entity.setGame(game);
		
		entity = repository.save(entity);
		return new RecordDTO(entity);
	}

}
