package nongsan.webmvc.dao.impl;

import java.util.List;

import nongsan.webmvc.dao.IBoardNewDAO;
import nongsan.webmvc.model.BoardNew;

public class BoardNewDAOImpl extends BaseDAO<BoardNew> implements IBoardNewDAO {
    public BoardNewDAOImpl() {
        super();
        setType(BoardNew.class);
    }

    @Override
    public Boolean createBoardNew(BoardNew boardnew) {
        return save(boardnew);
    }

    @Override
    public Boolean deleteBoardNew(Integer id) {
        return delete(id);
    }

    @Override
    public Boolean updateBoardNew(BoardNew boardnew) {
        return save(boardnew);
    }

    @Override
    public BoardNew findBoardNewById(Integer id) {
        return findById(id);
    }

    @Override
    public List<BoardNew> findAllBoardNew() {
        return findAll();
    }
}
