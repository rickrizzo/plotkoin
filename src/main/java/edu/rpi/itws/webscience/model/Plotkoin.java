package edu.rpi.itws.webscience.model;

import java.io.UnsupportedEncodingException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import edu.rpi.itws.webscience.utilities.StringShuffle;

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
	@JsonProperty(access = Access.WRITE_ONLY)
	private String secret;
	
	protected Plotkoin() {
	}
	
	@JsonCreator
	public Plotkoin(@JsonProperty("sender") int sender, 
			        @JsonProperty("receiver") int receiver, 
			        @JsonProperty("amount") int amount, 
			        @JsonProperty("message") String message) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.message = message;
		this.secret = StringShuffle.shuffle(Integer.toString(receiver));
		
		Algorithm algorithm = null;
		try {
			algorithm = Algorithm.HMAC256(this.secret);
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
	
	public String getData() {
		return data;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public boolean validate(String guess) {
		if(guess.equals(secret)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Plotkoin Transaction [to=%d, from=%d, amount=%d, message=%s, data=%s]", 
				receiver, sender, amount, message, data);
	}

}
