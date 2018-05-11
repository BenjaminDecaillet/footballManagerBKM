package ch.hevs.footballmanager;

import java.util.List;

import javax.persistence.Query;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;

@Stateful
public class FootballBean implements Football{
	
	@PersistenceContext(name = "FootPU",type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public League getLeagueById(int id) {
		// TODO Auto-generated method stub
		Query query = (Query) em.createQuery("FROM League l WHERE l.id=:id").setParameter("id", id).getSingleResult();
		return (League) query.getSingleResult();
	}

	@Override
	public League getLeagueByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public League getLeagueByNationality(String nationality) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Club getClubById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Club getClubByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getTitularPlayersFromClub(Club club) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersonsByLastname(String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersonsByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersonsByNationality(String nationality) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trainer getTrainerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public President getPresidentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Club> getClubs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<League> getLeagues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trainer> getTrainers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
