package com.itacademy.controller;

import com.itacademy.model.post_model.PublicationCommentaryModelGet;
import com.itacademy.model.post_model.PublicationCommintaryPost;
import com.itacademy.model.post_model.PublicationModelGet;
import com.itacademy.model.post_model.PublicationModelPost;
import com.itacademy.service.PublicationCommentaryService;
import com.itacademy.service.PublicationService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private PublicationCommentaryService commentaryService;

    @GetMapping("/getAll")
    public List<PublicationModelGet> getAll() {
        return publicationService.convertEntityToModelList(publicationService.getAll());
    }

    @GetMapping("/getAllPostUser")
    public List<PublicationModelGet> getAllPostUser() {
        return publicationService.convertEntityToModelList(
                publicationService.getPostUserList(usersService.getCurrentUser()));
    }

    @PostMapping("/newPost")
    public PublicationModelGet newPost(@RequestBody PublicationModelPost post) {
        return publicationService.convertEntityToModel(publicationService.newPost(post));
    }

    @GetMapping("/getPost/{id}")
    public PublicationModelGet getById(@PathVariable Long id){
        return publicationService.convertEntityToModel(publicationService.getById(id));
    }

    @PostMapping("/update")
    public PublicationModelGet update (@RequestBody PublicationModelGet get){
      return publicationService.convertEntityToModel( publicationService.updatePost(get));
    }

    @GetMapping("/getPost/{id}/commit/getAll")
    public List<PublicationCommentaryModelGet> displayingAllCommentsFromPost(@PathVariable Long id){
       PublicationModelGet publicationModelGet = publicationService.convertEntityToModel(publicationService.getById(id));
       return  publicationModelGet.getPublicationCommentaryModelGetList();
    }

    @PostMapping("/getPost/{id}/commit/newCommit")
    public PublicationCommentaryModelGet newCommit(@RequestBody PublicationCommintaryPost  post, @PathVariable Long id){
        return commentaryService.convertEntityToModel(commentaryService.newCommentary(post, id));
    }

    @DeleteMapping("/delete/{id}")
    public PublicationModelGet deletePost(@PathVariable Long id){

        return publicationService.convertEntityToModel(publicationService.deletePost(id));
    }
    @GetMapping
    public List<PublicationModelGet> publicationForUser(){
        return publicationService.convertEntityToModelList(publicationService.newsFeed());
    }

}
