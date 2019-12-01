package base.interfaces.board.controller;

import base.domain.board.entity.Board;
import base.interfaces.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/list")
    public List<Board> getList(Model model) {

        List<Board> boardList = boardService.getList();

        return boardList;
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/insertBoard")
    public String insertBoard(@RequestBody Board board) {

        boardService.insertBoard(board);

        return "OK";
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/getBoard/{seq}")
    public Board getBoard(@PathVariable Long seq) {

        Board board = boardService.getBoard(seq);

        return board;
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/updateBoard")
    public String updateBoard(@RequestBody Board board) {

        boardService.updateBoard(board);

        return "OK";
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/deleteBoard/{seq}")
    public String deleteBoard(@PathVariable Long seq) {

        boardService.deleteBoard(seq);

        return "OK";
    }
}
