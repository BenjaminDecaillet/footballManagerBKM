package ch.hevs.footballmanager;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;

import java.util.List;
import javax.ejb.Local;

@Local
public interface Football {
	
	//Populate Database
	void populate();
	
	//Gets Globaux
	List<Player> getPlayers();
	List<Account> getAccounts();
	List<Club> getClubs();
	List<League> getLeagues();
	List<Trainer> getTrainers();
	
	//Gets sp�cifiques aux leagues
	League getLeagueById(long id);
	League getLeagueByName(String name);
	League getLeagueByNationality(String nationality);
	
	//Gets sp�cifiques aux accounts
	Account getAccountById(long id);
	Account getAccountByPlayerId(Person player);
	
	//Gets sp�cifiques aux clubs
	Club getClubById(long id);
	Club getClubByName(String name);
	List<Player> getTitularPlayersFromClub(Club club);
	
	//Gets sp�cifiques aux persons
	List<Person> getPersonsByLastname(String lastname);
	List<Person> getPersonsByFirstname(String firstname);
	List<Person> getPersonsByNationality(String nationality);
	
	//Get sp�cifique aux players
	Player getPlayerById(long id);
	
	//Get sp�cifique aux trainers
	Trainer getTrainerById(long id);

	//Get sp�cifique aux presidents
	President getPresidentById(long id);
	
	void transfer(Account compteSrc, Account compteDest, int montant) throws Exception;

}
