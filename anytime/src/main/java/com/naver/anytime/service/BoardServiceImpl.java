package com.naver.anytime.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.anytime.domain.Board;
import com.naver.anytime.mybatis.mapper.BoardAuthMapper;
import com.naver.anytime.mybatis.mapper.BoardMapper;
import com.naver.anytime.mybatis.mapper.MemberMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper dao;
	private MemberMapper memberDao;
	private BoardAuthMapper boardAuthDao;

	@Autowired
	public BoardServiceImpl(BoardMapper boardDao, MemberMapper memberDao, BoardAuthMapper boardAuthDao) {
		this.dao = boardDao;
		this.memberDao = memberDao;
		this.boardAuthDao = boardAuthDao;
	}

	@Override
	public List<Board> getBoardName(int board_id) {
		return dao.getBoardName(board_id);
	}

	@Override
	public List<Board> getBoardList(int school_id) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("school_id", school_id);
		return dao.getBoardList(map);
	}

	@Override
	public Board getBoardDetail(int board_id) {
		return dao.getBoardDetail(board_id);
	}

	// 보드 익명 체크
	public int getBoardAnonymous(int board_id) {
		return dao.getBoardAnonymous(board_id);
	}
	
	

	// ********************************= 윤희 =********************************
	@Override
	public int[] getBoardIds(String id) {
		return dao.getBoardIds(id);
	}

	@Override
	public int[] getBoardIdsByDomain(String schoolDomain) {
		return dao.getBoardIdsByDomain(schoolDomain);
	}
	
	@Transactional
	public int insertBoard(Board board) {
		int result = dao.insertBoard(board);
		memberDao.updateBoardAdmin(board.getUSER_ID());
		boardAuthDao.insertBoardAuth(board.getNEW_BOARD_ID(), board.getUSER_ID());
		return result;
	}
	// ********************************= 윤희 =********************************	

	@Override
	public List<Board> getBoardContent(int board_id) {
		return dao.getBoardContent(board_id);
	}

	@Override
	public int updateBoardContent(int board_id, String content) {
		return dao.updateBoardContent(board_id, content);
	}

	@Override
	public int getBoardManager(int board_id, int user_id) {
		Integer result = dao.getBoardManager(board_id, user_id);
		return result;
	}

	@Override
	public int deleteBoard(String board_name, int user_id) {
		return dao.deleteBoard(board_name, user_id);
	}
	
	//권한
	@Override
	public int deleteBoardAuth(int board_id) {
		return boardAuthDao.deleteBoardAuth(board_id);
	}
	
	@Override
	public int getBoardName2(String board_name, int board_id) {
		Integer result = dao.getBoardName2(board_name, board_id);
		return result;
	}

	@Override
	public int updateBoardUserId(int am_user_id_num, int tf_user_id_num, int board_id) {
		return dao.updateBoardUserId(am_user_id_num, tf_user_id_num, board_id);
	}

	@Override
	public int updateBoardAuth(int am_user_id_num, int tf_user_id_num, int board_id) {
		return boardAuthDao.updateBoardAuth(am_user_id_num, tf_user_id_num, board_id);
	}


}