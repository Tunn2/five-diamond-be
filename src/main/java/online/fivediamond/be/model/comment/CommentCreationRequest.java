package online.fivediamond.be.model.comment;

import lombok.Data;

@Data
public class CommentCreationRequest {
    long accountId;
    long productLineId;
    String content;
    boolean isDeleted;
}
