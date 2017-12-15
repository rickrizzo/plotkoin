package edu.rpi.itws.webscience.model;

import java.io.UnsupportedEncodingException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.rpi.itws.webscience.utilities.StringShuffle;

//@Table(name="transaction")
@Entity
public class Plotkoin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int sender;
	private int receiver;
	private int amount;
	private String message;
	private String data;
	
	protected Plotkoin() {
	}
	
	public Plotkoin(int sender, int receiver, int amount, String message, String secret) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.message = message;
		
		Algorithm algorithm = null;
		try {
			algorithm = Algorithm.HMAC256(StringShuffle.shuffle(secret));
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		this.data = JWT.create()
					  .withIssuer("plotkoin")
					  .withClaim("sender", this.sender)
					  .withClaim("receiver", this.receiver)
					  .withClaim("amount", this.amount)
					  .withClaim("message", this.message)
					  .sign(algorithm);
	}
	
	public int getSender() {
		return sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public int getAmount() {
		return amount;
	}
	public String getMessage() {
		return message;
	}
	
	public String getJwt() {
		return data;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Plotkoin Transaction [to=%d, from=%d, amount=%d, message=%s, jwt=%s]", 
				receiver, sender, amount, message, data);
	}

}
