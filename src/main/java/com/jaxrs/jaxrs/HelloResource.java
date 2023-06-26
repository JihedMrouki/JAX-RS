package com.jaxrs.jaxrs;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.List;

@Path("/events")
public class HelloResource {
    static List<Event> events = new ArrayList<Event>();
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public String ajouterEvent(Event event) {
        if (events.add(event))
            return "Evenement ajoute";
        else
            return " Evenement non ajoute";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)//"application/json"
    public List<Event> getAllEvents()
    {

        return events;
    }

    @DELETE
    @Path("events/idEvent/{id}")
    public String deleteEvent(@PathParam("id") String idEvent) {
        int index = this.getIndexOfEventById(idEvent);

        if ((index != -1) && (events.remove(index) != null))
            return "Evenement supprime";
        return "Evenement non supprime";

    }

    @GET
    @Path("{idEvent}")
    @Produces("application/xml")
    public Event chercherEmploye(@PathParam("idEvent") String idEvent) {
        int index = this.getIndexOfEventById(idEvent);
        if (index != -1) {
            return events.get(index);
        }
        return null;
    }

    public int getIndexOfEventById(String idEvent) {
        for (Event evt : events) {
            if (evt.getId().equals(idEvent)) {
                return events.indexOf(evt);
            }
        }
        return -1;
    }

}