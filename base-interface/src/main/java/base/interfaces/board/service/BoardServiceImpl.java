package base.interfaces.board.service;

import base.domain.board.BoardRepository;
import base.domain.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> getList() {
        return (List<Board>) boardRepository.findAll();
    }

    @Override
    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long seq) {

        return boardRepository.findById(seq).get();
    }

    @Override
    public void updateBoard(Board board) {
        Board orgBoard = boardRepository.findById(board.getSeq()).get();

        orgBoard.setTitle(board.getTitle());
        orgBoard.setContent(board.getContent());
        boardRepository.save(orgBoard);
    }

    @Override
    public void deleteBoard(Long seq) {
        boardRepository.deleteById(seq);
    }

}
