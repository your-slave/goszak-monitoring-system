package dao;

import entity.Establishment;
import service.representation.DefaultDictionaryRepresntation;

import java.util.HashSet;
import java.util.List;


public interface EstablishmentDAO
{
	public List<DefaultDictionaryRepresntation> getEstablishmentsDictionary();
	public Establishment getEstablishment(int idEstablishment);
	public HashSet<Integer> getEstablishmentIds();
}
