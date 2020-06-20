package com.csust.community.service;

import com.csust.community.exception.CustomizeErrorCode;
import com.csust.community.exception.CustomizeException;
import com.csust.community.model.Comment;
import org.springframework.stereotype.Service;

/**
 * @Author XieHaiBin
 * @Date 2020/6/20 16:54
 * @Version 1.0
 */
@Service
public class CommentService {
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) { //判断评论是否存在
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
