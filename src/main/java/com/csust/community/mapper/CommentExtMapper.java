package com.csust.community.mapper;

import com.csust.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}