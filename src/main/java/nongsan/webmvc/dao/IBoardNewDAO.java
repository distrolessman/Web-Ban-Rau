package nongsan.webmvc.dao;

import java.util.List;

import nongsan.webmvc.model.BoardNew;

public interface IBoardNewDAO extends IBaseDAO<BoardNew> {
    Boolean createBoardNew(BoardNew boardnew);

    Boolean updateBoardNew(BoardNew boardnew);

    Boolean deleteBoardNew(Integer id);

    BoardNew findBoardNewById(Integer id);

    List<BoardNew> findAllBoardNew();
}
