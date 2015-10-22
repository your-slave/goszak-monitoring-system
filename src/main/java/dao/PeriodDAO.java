package dao;

import entity.Period;
import service.representation.DefaultDictionaryRepresntation;

import java.util.HashSet;
import java.util.List;

public interface PeriodDAO
{
	public List<DefaultDictionaryRepresntation> getPeriodDictionary();
	public Period getPeriod(int idPeriod);
	public HashSet<Integer> getPeriodIds();
}
