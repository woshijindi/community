package com.example.shequ.service;


import com.example.shequ.enums.CommentTypeEnum;
import com.example.shequ.exception.CustomizeErrorCode;
import com.example.shequ.exception.CustomizeException;
import com.example.shequ.mapper.CommentMapper;
import com.example.shequ.mapper.QuestionExtMapper;
import com.example.shequ.mapper.QuestionMapper;
import com.example.shequ.model.Comment;
import com.example.shequ.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;


    @Transactional
    public void insert(Comment comment) {


        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbcomment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
            }
        } else {
            //回复问题
            Question dbquestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (dbquestion == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
                dbquestion.setCommentCount(1);
                questionExtMapper.incCommentCount(dbquestion);
            }
        }


    }
}
