package com.developervisits.oauth2.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@RequiredArgsConstructor
@Getter
public class PersistentLoginToken {
		
	@Id
	private String id;
	private final String username;
	private final String series;
	private final String tokenValue;
	private final Date date;
	
	@PersistenceConstructor
	public PersistentLoginToken(String id, String username, String series, String tokenValue, Date date) {
		this.id = id;
		this.username=username;
		this.series = series;
		this.tokenValue = tokenValue;
		this.date = date;
	}
	
}
