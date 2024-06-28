package online.fivediamond.be.api;

import online.fivediamond.be.model.comment.CommentCreationRequest;
import online.fivediamond.be.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentAPI {
    @Autowired
    CommentService commentService;

    @PostMapping
    public ResponseEntity create(CommentCreationRequest request) {
        return ResponseEntity.ok(commentService.create(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(commentService.delete(id));
    }

    @GetMapping("{id}")
    public ResponseEntity getCommentsByProductLine(@PathVariable long id) {
        return ResponseEntity.ok(commentService.getCommentsByProductLine(id));
    }

    @GetMapping
    public ResponseEntity getComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }
}
