package base.interfaces.board.controller;

import base.domain.board.entity.Board;
import base.interfaces.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public List<Board> getList(Model model) {

        List<Board> boardList = boardService.getList();

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
