package dao;

import java.lang.reflect.Array;
import java.util.ArrayList;

import mains.Ranking;

public class RankingController {
	public boolean cadastrarPodruto(Ranking ran){
		RankingDao rDao = new RankingDao();
		return rDao.cadastrar(ran);
	}
	
	public ArrayList<Ranking> listarPodrutos(){
		RankingDao rDao = new RankingDao();
		return rDao.listar();
	}
}
