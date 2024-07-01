package online.fivediamond.be.service;

import online.fivediamond.be.entity.Account;
import online.fivediamond.be.entity.Comment;
import online.fivediamond.be.entity.ProductLine;
import online.fivediamond.be.model.comment.CommentCreationRequest;
import online.fivediamond.be.repository.AuthenticationRepository;
import online.fivediamond.be.repository.CommentRepository;
import online.fivediamond.be.repository.ProductLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    ProductLineRepository productLineRepository;

    public Comment create(CommentCreationRequest request) {
        Comment comment = new Comment();
        Account account = authenticationRepository.findById(request.getAccountId()).orElseThrow(() -> new RuntimeException("Not found"));
        ProductLine productLine = productLineRepository.findById(request.getProductLineId()).orElseThrow(() -> new RuntimeException("Not found"));
        comment.setAccount(account);
        comment.setProductLine(productLine);
        comment.setContent(request.getContent());
        comment.setCreateAt(new Date().toString());
        comment.setDeleted(false);
        return commentRepository.save(comment);
    }

    public Comment delete(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        comment.setDeleted(true);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByProductLine(long id) {
        return commentRepository.findByProductLineIdAndIsDeletedFalse(id);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
