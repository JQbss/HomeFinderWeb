package com.homefinder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.auth.FirebaseAuthException;
import com.homefinder.model.Announcement;
import com.homefinder.service.AnnouncementService;
import com.homefinder.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    final AnnouncementService announcementService;
    final AuthService authService;

    public AnnouncementController(AnnouncementService announcementService, AuthService authService) {
        this.announcementService = announcementService;
        this.authService = authService;
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
    public ResponseEntity<?> patchAnnouncement(@PathVariable String id, @RequestBody Announcement announcement) throws ExecutionException, InterruptedException {
        announcementService.patch(id,announcement);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<?> updateAnnouncement(@PathVariable String id, @RequestBody Announcement announcement) {
        if(announcementService.update(id,announcement) == 201) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseEntity<String> all(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "25") int limit,
                                                      @RequestParam(value = "orderBy", defaultValue = "uid") String orderBy,
                                                      @RequestParam Map<String, Object> filter) throws ExecutionException, InterruptedException, JsonProcessingException {
        String uid = null;
        if(authService.getUser()!=null) {
            uid = authService.getUser().getUid();
        }
        filter.remove("page");
        filter.remove("limit");
        filter.remove("orderBy");

        String res = this.announcementService.getAll(page,limit,orderBy,filter,uid);
        if(res!=null){
            return ResponseEntity.ok(res);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json;charset=utf-8")
    public ResponseEntity<?> getOne(@PathVariable String id) throws ExecutionException, InterruptedException {
        String res = announcementService.getOne(id);
        if(res!=null){
            return ResponseEntity.ok(res);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable String id) throws FirebaseAuthException {
        if(announcementService.deleteById(id) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/mine")
    ResponseEntity<String> getMine() throws ExecutionException, InterruptedException, JsonProcessingException {
        Map<String, Object> fid = new HashMap<>();
        fid.put("sellerUid", authService.getUser().getUid());
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<>();
        String res = this.announcementService.getAll(0,25,"uid",fid,null);
        if(res!=null){
            return ResponseEntity.ok(res);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
