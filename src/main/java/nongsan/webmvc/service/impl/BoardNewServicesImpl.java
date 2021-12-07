package nongsan.webmvc.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nongsan.webmvc.dao.IBoardNewDAO;
import nongsan.webmvc.dao.impl.BoardNewDAOImpl;
import nongsan.webmvc.model.BoardNew;
import nongsan.webmvc.service.IBoardNewService;

import javax.inject.Inject;

public class BoardNewServicesImpl implements IBoardNewService {
	@Inject
	IBoardNewDAO boardNewDao;

	@Override
	public Boolean createBoardNew(String title, String content, String imageLink, String author, String created) {
		try {
			BoardNew boardnew = new BoardNew();
			boardnew.setTitle(title);
			boardnew.setContent(content);
			boardnew.setImage_link(imageLink);
			boardnew.setAuthor(author);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			boardnew.setCreated(formatter.parse(created));
			return boardNewDao.createBoardNew(boardnew);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateBoardNew(String id, String title, String content, String imageLink, String author, String created) {
		try {
			BoardNew newBoardNew = boardNewDao.findBoardNewById(Integer.parseInt(id));
			newBoardNew.setTitle(title);
			newBoardNew.setContent(content);
			newBoardNew.setImage_link(imageLink);
			newBoardNew.setAuthor(author);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			newBoardNew.setCreated(formatter.parse(created));
			return boardNewDao.updateBoardNew(newBoardNew);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Boolean deleteBoardNew(String id) {
		try {
			return boardNewDao.deleteBoardNew(Integer.parseInt(id));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BoardNew findBoardNewById(String id) {
		try {
			return boardNewDao.findBoardNewById(Integer.parseInt(id));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BoardNew> findAllBoardNew() {
		return boardNewDao.findAllBoardNew();
	}
}
