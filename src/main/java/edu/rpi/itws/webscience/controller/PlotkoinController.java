package edu.rpi.itws.webscience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.rpi.itws.webscience.model.Plotkoin;
import edu.rpi.itws.webscience.repository.PlotkoinRepository;

@RestController
public class PlotkoinController {
	
	@Autowired
	PlotkoinRepository repository;
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public String save() throws JsonProcessingException {
		return mapper.writeValueAsString(repository.findAll());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public String mine(@RequestBody int receiver) throws JsonProcessingException {
		if (repository.findAllByReceiver(receiver).size() == 0) {
			repository.save(new Plotkoin(0, receiver, 10, "init", Integer.toString(receiver)));
		}
		return mapper.writeValueAsString(repository.findAllByReceiver(receiver));
	}
	
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String deleteAll() {
		repository.deleteAll();
		return "The database was cleared";
	}

}
