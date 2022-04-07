package com.homefinder.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.homefinder.model.Announcement;
import com.homefinder.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        announcementService.add(announcement);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(announcement.getUid())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.POST,path = "/many", produces = "application/json;charset=utf-8")
    public ResponseEntity<?> createManyAnnouncement(@RequestBody List<Announcement> announcement) {
        announcementService.add(announcement);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PATCH,path = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<?> patchAnnouncement(@PathVariable String id, @RequestBody Announcement announcement) {
        announcementService.patch(id,announcement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<?> updateAnnouncement(@PathVariable String id, @RequestBody Announcement announcement) {
        if(announcementService.update(id,announcement) == 201){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public DeferredResult<ResponseEntity<String>> all(){
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        this.announcementService.getAll().whenComplete((serviceResult, throwable) ->
                result.setResult(ResponseEntity.ok(serviceResult)));
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json;charset=utf-8")
    public DeferredResult<ResponseEntity<?>> getOne(@PathVariable String id) {
        DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
        this.announcementService.getOne(id).whenComplete((serviceResult, throwable) ->{
            if(serviceResult == null){
                result.setResult(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
            }
            result.setResult(ResponseEntity.ok(serviceResult));
        });
        return result;
    }


    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable String id) throws FirebaseAuthException {
        if(announcementService.deleteById(id) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
