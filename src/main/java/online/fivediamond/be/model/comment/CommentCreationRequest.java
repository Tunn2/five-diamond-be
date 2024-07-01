package online.fivediamond.be.model.comment;

import lombok.Data;

@Data
public class CommentCreationRequest {
    String content;
    long accountId;
    long productLineId;
    boolean isDeleted;
}
