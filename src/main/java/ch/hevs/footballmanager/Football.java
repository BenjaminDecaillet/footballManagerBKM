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
	
	//Gets Globaux
	List<Player> getPlayers();
	List<Account> getAccounts();
	List<Club> getClubs();
	List<League> getLeagues();
	List<Trainer> getTrainers();
	
	//Gets sp�cifiques aux leagues
	League getLeagueById(int id);
	League getLeagueByName(String name);
	League getLeagueByNationality(String nationality);
	
	//Gets sp�cifiques aux accounts
	Account getAccountById(int id);
	
	//Gets sp�cifiques aux clubs
	Club getClubById(int id);
	Club getClubByName(String name);
	List<Player> getTitularPlayersFromClub(Club club);
	
	//Gets sp�cifiques aux persons
	List<Person> getPersonsByLastname(String lastname);
	List<Person> getPersonsByFirstname(String firstname);
	List<Person> getPersonsByNationality(String nationality);
	
	//Get sp�cifique aux players
	Player getPlayerById(int id);
	
	//Get sp�cifique aux trainers
	Trainer getTrainerById(int id);

	//Get sp�cifique aux presidents
	President getPresidentById(int id);
	

}
