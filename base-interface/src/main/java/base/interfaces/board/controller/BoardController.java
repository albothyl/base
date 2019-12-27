package base.interfaces.board.controller;

import base.domain.board.entity.Board;
import base.interfaces.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boards")
    public Page<Board> getBoards(@PageableDefault Pageable pageable) {

        log.info("pageNumber: "+pageable);
        Page<Board> boardList = boardService.getList(pageable);

        return boardList;
    }

    @PostMapping("/boards")
    public String insertBoard(@RequestBody Board board) {

        boardService.insertBoard(board);

        return "OK";
    }

    @GetMapping("/boards/{seq}")
    public Board getBoard(@PathVariable Long seq) {

        Board board = boardService.getBoard(seq);

        return board;
    }

    @PutMapping("/boards")
    public String updateBoard(@RequestBody Board board) {

        boardService.updateBoard(board);

        return "OK";
    }

    @DeleteMapping("/boards/{seq}")
    public String deleteBoard(@PathVariable Long seq) {

        boardService.deleteBoard(seq);

        return "OK";
    }
}
