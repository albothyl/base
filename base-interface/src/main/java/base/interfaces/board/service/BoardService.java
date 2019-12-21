package base.interfaces.board.service;

import base.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    public Page<Board> getList(Pageable pageable);

    public void insertBoard(Board board);

    public Board getBoard(Long seq);

    public void updateBoard(Board board);

    public void deleteBoard(Long seq);

}
