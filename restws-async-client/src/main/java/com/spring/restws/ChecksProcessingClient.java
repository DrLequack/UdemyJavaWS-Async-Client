package com.spring.restws;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.spring.restws.model.ChecksList;

public class ChecksProcessingClient {

	public static void main(String[] args) 
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/restasync/services/checksprocessor/checks");
		AsyncInvoker invoker = target.request().async();
		
		Future<Boolean> future = invoker.post(Entity.entity(new ChecksList(), MediaType.APPLICATION_XML), Boolean.class); 
		
		try {
			System.out.println("Response:" + future.get());
		} catch (InterruptedException | ExecutionException e) {
			if(e.getCause() instanceof BadRequestException)
			{
				System.out.println("nasty :)");
			}
		}
		
		
	}

}
