package com.leandersonandre.controller;

import com.leandersonandre.dto.UserDTO;
import com.leandersonandre.restclient.UsersClient;
import com.leandersonandre.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @RestClient
    UsersClient usersClient;

    @POST
    @Path("/users")
    public Map<String,Object> users(List<UserDTO> users){
        var start = System.currentTimeMillis();
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", ZonedDateTime.now());
        response.put("message","Arquivo recebido com sucesso");
        response.put("user_count",users.size());
        userService.save(users);
        var end = System.currentTimeMillis();
        response.put("execution_time_ms",end - start);
        return response;
    }

    @GET
    @Path("/superusers")
    public Map<String,Object> superUsers(){
        var start = System.currentTimeMillis();
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", ZonedDateTime.now());
        var list = userService.getSuperUsers();
        var end = System.currentTimeMillis();
        response.put("data",list);
        response.put("execution_time_ms",end - start);
        return response;
    }

    @GET
    @Path("/top-countries")
    public Map<String,Object> topCountries(){
        var start = System.currentTimeMillis();
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", ZonedDateTime.now());
        var list = userService.getTopCountries();
        var end = System.currentTimeMillis();
        response.put("countries",list);
        response.put("execution_time_ms",end - start);
        return response;
    }

    @GET
    @Path("/team-insights")
    public Map<String,Object> teamInsights(){
        var start = System.currentTimeMillis();
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", ZonedDateTime.now());
        var list = userService.getTeamInsights();
        var end = System.currentTimeMillis();
        response.put("countries",list);
        response.put("execution_time_ms",end - start);
        return response;
    }

    @GET
    @Path("/active-users-per-day")
    public Map<String,Object> activeUsersPerDay(@QueryParam("min") Long min){
        var start = System.currentTimeMillis();
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", ZonedDateTime.now());
        var list = userService.getActiveUsersPerDay(min);
        var end = System.currentTimeMillis();
        response.put("logins",list);
        response.put("execution_time_ms",end - start);
        return response;
    }

    @GET
    @Path("/evaluation")
    public Map<String,Object> evaluation(){
        Map<String,Object> response = new HashMap<>();
        Map<String,Object> testedEndpoints = new HashMap<>();
        Map<String,Object> superUserResponse = new HashMap<>();
        Map<String,Object> rankingPaises = new HashMap<>();
        Map<String,Object> analiseEquipes = new HashMap<>();
        Map<String,Object> usuariosAtivosPorDia = new HashMap<>();
        try(var r = usersClient.superUsers()){
            superUserResponse.put("status",r.getStatus());
            var m = r.readEntity(Map.class);
            superUserResponse.put("time_ms",m.get("execution_time_ms"));
            superUserResponse.put("valid_response",true);
        }catch (Exception e){
            superUserResponse.put("valid_response",false);
        }
        try(var r = usersClient.topCountries()){
            var m = r.readEntity(Map.class);
            rankingPaises.put("status",r.getStatus());
            rankingPaises.put("time_ms",m.get("execution_time_ms"));
            rankingPaises.put("valid_response",true);
        }catch (Exception e){
            rankingPaises.put("valid_response",false);
        }
        try(var r = usersClient.teamInsights()){
            var m = r.readEntity(Map.class);
            analiseEquipes.put("status",r.getStatus());
            analiseEquipes.put("time_ms",m.get("execution_time_ms"));
            analiseEquipes.put("valid_response",true);
        }catch (Exception e){
            analiseEquipes.put("valid_response",false);
        }
        try(var r = usersClient.activeUsersPerDay()){
            var m = r.readEntity(Map.class);
            usuariosAtivosPorDia.put("status",r.getStatus());
            usuariosAtivosPorDia.put("time_ms",m.get("execution_time_ms"));
            usuariosAtivosPorDia.put("valid_response",true);
        }catch (Exception e){
            usuariosAtivosPorDia.put("valid_response",false);
        }
        testedEndpoints.put("superusuarios",superUserResponse);
        testedEndpoints.put("ranking-paises",rankingPaises);
        testedEndpoints.put("analise-equipes",analiseEquipes);
        testedEndpoints.put("usuarios-ativos-por-dia",usuariosAtivosPorDia);
        response.put("tested_endpoints",testedEndpoints);
        return response;
    }

}
