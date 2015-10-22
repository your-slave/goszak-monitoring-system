package dao;

import entity.Industry;
import service.representation.IndustryDictionaryRepresentation;

import java.util.HashSet;
import java.util.List;


public interface IndustryDAO
{
	public List<IndustryDictionaryRepresentation> getIndustryDictionary();
	public Industry getIndustry(int idIndustry);
	public HashSet<Integer> getIndustryIds();
}
