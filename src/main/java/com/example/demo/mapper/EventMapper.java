package com.example.demo.mapper;

import com.example.demo.dto.CreateEventRequest;
import com.example.demo.dto.EventResponse;
import com.example.demo.entity.Event;

public class EventMapper {

    // this function maps the event to event response dto
    public static Event toEntity(CreateEventRequest req) {
        Event event = new Event();
        event.setTitle(req.getTitle());
        event.setEventDate(req.getEventDate());
        event.setCity(req.getCity());
        event.setBrand(req.getBrand());
        event.setType(req.getType());
        event.setDescription(req.getDescription());
        return event;
    }

    // this method converts Event entity to EventResponse DTOs
    public static EventResponse toResponse(Event event){
        EventResponse res = new EventResponse();    // creating an empty event response
        res.setId(event.getId());
        res.setTitle(event.getTitle());
        res.setEventDate(event.getEventDate());
        res.setCity(event.getCity());
        res.setBrand(event.getBrand());
        res.setType(event.getType());
        res.setDescription(event.getDescription());
        res.setCreatedAt(event.getCreatedAt());
        return res;
    }

}
