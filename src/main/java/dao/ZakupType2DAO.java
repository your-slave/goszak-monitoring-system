package dao;

import entity.ZakupType2;
import service.representation.DefaultDictionaryRepresntation;

import java.util.HashSet;
import java.util.List;


public interface ZakupType2DAO
{
	public List<DefaultDictionaryRepresntation> getZakupType2Dictionary();
	public ZakupType2 getZakupType2(int idZakupType2);
	public HashSet<Integer> getZakupType2Ids();
}
