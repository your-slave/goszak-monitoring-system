package dao;

import entity.Criteria;
import service.representation.CriteriaRepresentation;

import java.util.List;

public interface CriteriaDAO
{
	public Integer addCritera(String userEmail, CriteriaRepresentation criteriaRepresentation);
	public Integer updateCriteria(String userEmail, CriteriaRepresentation criteriaRepresentation);
	public boolean deleteCreteria(String userEmail, int idCriteria);

	public Criteria getCriteriaById(int idCriteria);
	public CriteriaRepresentation getCriteriaRepresentation(String userEmail, int idCriteria);
	public List<CriteriaRepresentation> getCriteriasRepresintationsForStart();
	public List<CriteriaRepresentation> getUsersCriteriasRepresintations(String userEmail);
	public String getDestination(Integer idCriteria);
	public boolean activateCriteria(String userEmail, int idCriteria);
	public boolean suspendCriteria(String userEmail, int idCriteria);
}
