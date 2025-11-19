package com.example.demo.mapper;

import com.example.demo.entity.Blog;
import com.example.demo.dto.CreateBlogRequest;
import com.example.demo.dto.BlogResponse;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;


public class BlogMapper {

    // this method maps Blog entity to BlogResponse DTO so that it can be sent as a response
    public static Blog toResponse(CreateBlogRequest req, Event event, User user) {
        Blog blog = new Blog();
        blog.setContent(req.getContent());
        blog.setEvent(event);
        blog.setUser(user);
        blog.setContent(req.getContent());
        blog.setStatus("PENDING");
        return blog;
    }

    // this method converts the Blog entity from the database to BlogResponse DTO
    public static BlogResponse toResponse(Blog blog) {
        BlogResponse response = new BlogResponse(); // creating an empty blog response
        response.setId(blog.getId());
        response.setUserId(blog.getUser().getId());
        response.setEventId(blog.getEvent().getId());
        response.setContent(blog.getContent());
        response.setStatus(blog.getStatus());
        response.setCreatedAt(blog.getCreatedAt());
        return response;
    }
}
