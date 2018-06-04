package ch.hevs.exception;

import javax.ejb.ApplicationException;

/*https://stackoverflow.com/a/32859824 */
@ApplicationException(rollback=true)
public class TransferException extends RuntimeException {
	private String message;
	
	public TransferException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}	
}