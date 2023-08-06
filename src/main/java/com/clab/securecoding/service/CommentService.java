package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.mapper.CommentMapper;
import com.clab.securecoding.repository.CommentRepository;
import com.clab.securecoding.type.dto.CommentRequestDto;
import com.clab.securecoding.type.dto.CommentResponseDto;
import com.clab.securecoding.type.dto.CommentUpdateRequestDto;
import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.Comment;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final BoardService boardService;

    private final UserService userService;

    public void createComment(CommentRequestDto commentRequestDto) {
        User writer = userService.getCurrentUser();
        Comment comment = commentMapper.mapDtoToEntity(commentRequestDto);
        comment.setWriter(writer);
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getCommentsByBoardId(Long boardId) {
        Board board = boardService.getBoardByIdOrThrow(boardId);
        List<Comment> comments = commentRepository.findByBoardOrderByCreatedAtDesc(board);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = commentMapper.mapEntityToDto(comment);
            commentResponseDtos.add(commentResponseDto);
        }
        return commentResponseDtos;
    }

    public void updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        Comment comment = getCommentByIdOrThrow(commentId);
        if (writer != comment.getWriter())
            throw new PermissionDeniedException();
        comment.setContent(commentUpdateRequestDto.getContent());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        Comment comment = getCommentByIdOrThrow(commentId);
        if (writer == comment.getWriter() || userService.checkUserAdminRole())
            commentRepository.delete(comment);
        else
            throw new PermissionDeniedException();
    }

    private Comment getCommentByIdOrThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글이 없습니다."));
    }

}
