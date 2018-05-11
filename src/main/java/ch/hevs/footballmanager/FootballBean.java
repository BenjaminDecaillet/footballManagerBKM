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
		return (League) em.createQuery("FROM Ligue l WHERE l.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public League getLeagueByName(String name) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM Ligue l WHERE l.nom=:name").setParameter("name", name).getSingleResult();
	}

	@Override
	public League getLeagueByNationality(String nationality) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM Ligue l WHERE l.nationalité=:nationality").setParameter("nationality", nationality).getSingleResult();
	}

	@Override
	public Account getAccountById(int id) {
		// TODO Auto-generated method stub
		return (Account) em.createQuery("FROM Compte c WHERE c.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Club getClubById(int id) {
		// TODO Auto-generated method stub
		return (Club) em.createQuery("FROM Club cl WHERE cl.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Club getClubByName(String name) {
		// TODO Auto-generated method stub
		return (Club) em.createQuery("FROM Club cl WHERE cl.nom=:name").setParameter("name", name).getSingleResult();
	}

	@Override
	public List<Player> getTitularPlayersFromClub(Club club) {
		// TODO Auto-generated method stub
		return (List<Player>) em.createQuery("SELECT cl.players FROM Club cl WHERE cl.id=:id").setParameter("id", club.getId()).getResultList();
	}

	@Override
	public List<Person> getPersonsByLastname(String lastname) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Personne p WHERE p.nom=:lastname").setParameter("lastname", lastname).getResultList();
	}

	@Override
	public List<Person> getPersonsByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Personne p WHERE p.prénom=:firstname").setParameter("firstname", firstname).getResultList();
	}

	@Override
	public List<Person> getPersonsByNationality(String nationality) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Personne p WHERE p.nationalité=:nationality").setParameter("nationality", nationality).getResultList();
	}

	@Override
	public Player getPlayerById(int id) {
		// TODO Auto-generated method stub
		return (Player) em.createQuery("FROM Joueur j WHERE j.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Trainer getTrainerById(int id) {
		// TODO Auto-generated method stub
		return (Trainer) em.createQuery("FROM Entraineur e WHERE e.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public President getPresidentById(int id) {
		// TODO Auto-generated method stub
		return (President) em.createQuery("FROM Président p WHERE p.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return (List<Player>) em.createQuery("FROM Joueur").getResultList();
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return (List<Account>) em.createQuery("FROM Compte").getResultList();
	}

	@Override
	public List<Club> getClubs() {
		// TODO Auto-generated method stub
		return (List<Club>) em.createQuery("FROM Club").getResultList();
	}

	@Override
	public List<League> getLeagues() {
		// TODO Auto-generated method stub
		return (List<League>) em.createQuery("FROM Ligue").getResultList();
	}

	@Override
	public List<Trainer> getTrainers() {
		// TODO Auto-generated method stub
		return (List<Trainer>) em.createQuery("FROM Entraineur").getResultList();
	}
	
	

}
