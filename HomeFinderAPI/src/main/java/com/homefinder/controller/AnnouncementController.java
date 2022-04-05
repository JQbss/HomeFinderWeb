package com.homefinder.controller;

import com.homefinder.model.Announcement;
import com.homefinder.service.AnnouncementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        announcementService.add(announcement);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(announcement.getUid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public DeferredResult<ResponseEntity<String>> all(){
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        this.announcementService.getAll().whenComplete((serviceResult, throwable) ->
                result.setResult(ResponseEntity.ok(serviceResult)));
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json;charset=utf-8")
    public DeferredResult<ResponseEntity<String>> getOne(@PathVariable String id){
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        this.announcementService.getOne(id).whenComplete((serviceResult, throwable) ->
                result.setResult(ResponseEntity.ok(serviceResult)));
        return result;
    }

}
