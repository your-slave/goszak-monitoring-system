package dao;

import entity.Region;
import service.representation.DefaultDictionaryRepresntation;

import java.util.HashSet;
import java.util.List;

public interface RegionDAO
{
	public List<DefaultDictionaryRepresntation> getRegionDictionary();
	public Region getRegion(int idRegion);
	public HashSet<Integer> getRegionIds();
}
