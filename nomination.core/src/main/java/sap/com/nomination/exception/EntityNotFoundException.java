package sap.com.nomination.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

	private UUID id;

	private String entityName;

	public EntityNotFoundException(UUID id, String entityName, Throwable cause) {
		super(cause);
		this.id = id;
		this.entityName = entityName;
	}

	public EntityNotFoundException(UUID id, String entityName) {
		this.id = id;
		this.entityName = entityName;
	}

	public UUID getId() {
		return id;
	}

	public String getEntityType() {
		return entityName;
	}

	public String getMessage(){
		return entityName + ":" + id + " not found" ;
	}
}
