package com.justclick.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.justclick.entity.Agent;

public interface AgentRepository extends ElasticsearchRepository<Agent, String>{

}
