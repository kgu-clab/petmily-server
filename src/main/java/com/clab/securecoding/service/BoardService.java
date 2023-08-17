package com.clab.securecoding.service;

import com.clab.securecoding.exception.NotFoundException;
import com.clab.securecoding.exception.PermissionDeniedException;
import com.clab.securecoding.exception.SearchResultNotExistException;
import com.clab.securecoding.mapper.BoardMapper;
import com.clab.securecoding.repository.BoardRepository;
import com.clab.securecoding.type.dto.BoardRequestDto;
import com.clab.securecoding.type.dto.BoardResponseDto;
import com.clab.securecoding.type.entity.Board;
import com.clab.securecoding.type.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    private final UserService userService;

    public void createBoard(BoardRequestDto boardRequestDto) {
        User writer = userService.getCurrentUser();
        Board board = boardMapper.mapDtoToEntity(boardRequestDto);
        board.setWriter(writer);
        boardRepository.save(board);
    }

    public List<BoardResponseDto> getBoards() {
        List<Board> boards = boardRepository.findAll();
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
        for (Board board : boards) {
            BoardResponseDto boardResponseDto = boardMapper.mapEntityToDto(board);
            boardResponseDtos.add(boardResponseDto);
        }
        return boardResponseDtos;
    }

    public List<BoardResponseDto> searchBoards(Long boardId, String title) {
        List<Board> boards = new ArrayList<>();
        if (boardId != null) {
            Board board = getBoardByIdOrThrow(boardId);
            boards.add(board);
        } else if (title != null) {
            boards = boardRepository.findByTitleContainingIgnoreCase(title);
        } else {
            throw new IllegalArgumentException("적어도 boardId 또는 title 중 하나를 제공해야 합니다.");
        }

        if (boards.isEmpty()) {
            throw new SearchResultNotExistException();
        }
        else {
        }

        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
        for (Board board : boards) {
            BoardResponseDto boardResponseDto = boardMapper.mapEntityToDto(board);
            boardResponseDtos.add(boardResponseDto);
        }
        return boardResponseDtos;
    }

    public void updateBoard(Long boardId, BoardRequestDto boardRequestDto) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        Board board = getBoardByIdOrThrow(boardId);
        if (writer != board.getWriter()) {
            throw new PermissionDeniedException();
        }
        else {
        }
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) throws PermissionDeniedException {
        User writer = userService.getCurrentUser();
        Board board = getBoardByIdOrThrow(boardId);
        if (writer == board.getWriter() || userService.checkUserAdminRole()) {
            boardRepository.delete(board);
        }
        else {
            throw new PermissionDeniedException();
        }
    }

    public Board getBoardByIdOrThrow(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("해당 게시글이 없습니다."));
    }

}
