package dao;

import entity.ZakupType1;
import service.representation.DefaultDictionaryRepresntation;

import java.util.HashSet;
import java.util.List;

public interface ZakupType1DAO
{
	public List<DefaultDictionaryRepresntation> getZakupType1Dictionary();
	public ZakupType1 getZakupType1(int idZakupType1);
	public HashSet<Integer> getZakupType1Ids();
}
