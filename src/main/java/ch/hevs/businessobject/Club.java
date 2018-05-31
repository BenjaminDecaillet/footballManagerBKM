package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Club")
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="nom")
	private String name;
	@Column(name="nationalité")
	private String nationality;
	
	//Relations
	@ManyToOne
	private League league;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_fk", nullable = false)
	private Account accountClub;
	@OneToOne(cascade = CascadeType.ALL)
	private Person trainer;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "president_fk", nullable = false)
	private Person president;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="club")
	private List<Player> players;
	
	public Club(){
		players = new ArrayList<Player>();
		president = new President();
		accountClub = new Account();
		trainer = new Trainer();
	}

	public Club(String name, String nationality) {
		this();
		this.name = name;
		this.nationality = nationality;
	}
	/**
	 * Gets the id of the Club
	 * @return long with the id value
	 */
	public long getId() {
		return id;
	}
	/**
	 * Sets the id of the Club
	 * @param id long value of the id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the Club's name
	 * @return String representing the Club's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Club's name
	 * @param name String representing the Club's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the Club's nationality
	 * @return String representing the Club's nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * String representing the Club's nationality
	 * @param nationality String representing the Club's nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the league
	 */
	public League getLeague() {
		return league;
	}

	/**
	 * @param league the league to set
	 */
	public void setLeague(League league) {
		this.league = league;
	}

	/**
	 * @return the account
	 */
	public Account getAccountClub() {
		return accountClub;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccountClub(Account accountClub) {
		this.accountClub = accountClub;
	}

	/**
	 * @return the trainer
	 */
	public Person getTrainer() {
		return trainer;
	}

	/**
	 * @param trainer the trainer to set
	 */
	public void setTrainer(Person trainer) {
		this.trainer = trainer;
	}

	/**
	 * @return the president
	 */
	public Person getPresident() {
		return president;
	}

	/**
	 * @param president the president to set
	 */
	public void setPresident(Person president) {
		this.president = president;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	/**
	 * Add the specified player to the club
	 * @param player to add to the club
	 */
	public void addPlayer(Player player){
		this.players.add(player);
	}
	/**
	 * Remove the specified player from the club
	 * @param player to be removed
	 */
	public String removePlayer(Player player){		
		for (Person p : players) {
			if(p.getId()==player.getId())
				players.remove(p);
		}
		return "player "+player.getFirstname()+" "+player.getLastname()+" has been removed from the club.";
	}
	
	@Override
	public boolean equals(Object object) {
		return (object instanceof Club) && (((Long)id) != null) 
	             ? ((Long)id).equals(((Club) object).id) 
	             : (object == this);
	}
}
