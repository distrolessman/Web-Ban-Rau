package nongsan.webmvc.service;

import java.util.List;

import nongsan.webmvc.model.BoardNew;

public interface IBoardNewService {
	Boolean createBoardNew(String title, String content, String imageLink, String author, String created);

	Boolean updateBoardNew(String id, String title, String content, String imageLink, String author, String created);

	Boolean deleteBoardNew(String id);

	BoardNew findBoardNewById(String id);

	List<BoardNew> findAllBoardNew();
}
