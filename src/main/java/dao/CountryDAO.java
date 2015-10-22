package dao;

import entity.Country;
import service.representation.CountryDictionaryRepresentation;

import java.util.ArrayList;
import java.util.HashSet;

public interface CountryDAO
{
	public ArrayList<CountryDictionaryRepresentation> getCountriesDictionary();
	public Country getCountry(int idCountry);
	public Country getCountryByName(String countryName);
	public String getCountryAcronymById(Integer idCountry);
	public HashSet<Integer> getCountryIds();
}
