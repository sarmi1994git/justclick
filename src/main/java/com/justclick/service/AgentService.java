package com.justclick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justclick.entity.Agent;
import com.justclick.repository.AgentRepository;

@Service
public class AgentService {
	@Autowired
	private AgentRepository repository;
	
	public void save(Agent agent) {
		repository.save(agent);
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}

}
