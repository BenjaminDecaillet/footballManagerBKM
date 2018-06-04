package ch.hevs.footballmanager;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.Contract;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;
import ch.hevs.exception.TransferException;
import ch.hevs.managedbeans.PersonBean;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

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
	
	//Gets spécifiques aux leagues
	League getLeagueById(long id);
	League getLeagueByName(String name);
	League getLeagueByNationality(String nationality);
	
	//Gets spécifiques aux accounts
	Account getAccountById(long id);
	Account getAccountByPlayerId(Person player);
	
	//Gets spécifiques aux clubs
	Club getClubById(long id);
	Club getClubByName(String name);
	List<Player> getTitularPlayersFromClub(Club club);
	List<Club> getOtherClubsThanCurent(Player player);
	
	//Gets spécifiques aux persons
	List<Person> getPersonsByLastname(String lastname);
	List<Person> getPersonsByFirstname(String firstname);
	List<Person> getPersonsByNationality(String nationality);
	
	//Get spécifique aux players
	Player getPlayerById(long id);
	
	//Get spécifique aux trainers
	Trainer getTrainerById(long id);
	List<Trainer> getTrainersWithoutJob();

	//Get spécifique aux presidents
	President getPresidentById(long id);
	
	//Transfer method
	void transfer(Player player, Club clubDst, int montant, Contract newContract) throws TransferException;

	//Login method
	Person login(String firstname,String lastname);

	//Persist new objects in DB
	void newPlayer(Player newPlayerObj);
	void newTrainer(Trainer newTrainerObj);
	void newPresident(PersonBean newPresidentObj);
	void newClub(Club newClubObj);
	
	//Merge updated objects
	void updatePlayer(Player updatedPlayerObj);
	void updateTrainer(Trainer updatedTrainerObj);
	void updatePresident(PersonBean updatedPresidentObj);
	void updateClub(Club updatedClubObj);
	
	//Remove objects
	void removePlayer(Player player);
	void removeTrainer(Trainer trainer);
	void removePresident(President president);
	void removeClub(Club club);	
}