package edu.rpi.itws.webscience.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.rpi.itws.webscience.model.Plotkoin;

public interface PlotkoinRepository extends CrudRepository<Plotkoin, Long>{
	List<Plotkoin> findAllBySender(int sender);
	List<Plotkoin> findAllByReceiver(int receiver);
}
