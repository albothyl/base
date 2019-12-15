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
@CrossOrigin(origins = "http://localhost:8081")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList")
    public Page<Board> getBoardList(@PageableDefault Pageable pageable) {

        log.info("pageNumber: "+pageable);
        Page<Board> boardList = boardService.getList(pageable);

        return boardList;
    }

    @PostMapping("/insertBoard")
    public String insertBoard(@RequestBody Board board) {

        boardService.insertBoard(board);

        return "OK";
    }

    @GetMapping("/getBoard/{seq}")
    public Board getBoard(@PathVariable Long seq) {

        Board board = boardService.getBoard(seq);

        return board;
    }

    @PostMapping("/updateBoard")
    public String updateBoard(@RequestBody Board board) {

        boardService.updateBoard(board);

        return "OK";
    }

    @GetMapping("/deleteBoard/{seq}")
    public String deleteBoard(@PathVariable Long seq) {

        boardService.deleteBoard(seq);

        return "OK";
    }
}
