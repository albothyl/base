package base.interfaces.board.service;

import base.domain.board.entity.Board;
import java.util.List;

public interface BoardService {
    public List<Board> getList();

    public void insertBoard(Board board);

    public Board getBoard(Long seq);

    public void updateBoard(Board board);

    public void deleteBoard(Long seq);

}
