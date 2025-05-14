package com.leandersonandre.restclient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://localhost:8080/")
public interface UsersClient {

    @Path("superusers")
    @GET
    Response superUsers();

    @Path("top-countries")
    @GET
    Response topCountries();

    @Path("team-insights")
    @GET
    Response teamInsights();

    @Path("active-users-per-day")
    @GET
    Response activeUsersPerDay();

}
