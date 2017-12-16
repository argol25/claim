package pl.arek.claiming.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//klasa zawierająca informacje na temat danych, które muszą być dostarczone przez użytkownika i które użytkownik otrzymuje po przetworzeniu
@Entity
public class ClaimData {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="Account", nullable=false)
	private String accountName;
	
	// data rozpoczęcia nadgodzin
	@Column(name="StartDate")
	private Date startTime;
	// data zakończenia nadgodzin
	private Date endTime;
	// nazwa klienta, dla którego wykonano nadgodziny
	// godziny rozpoczęcia nadgodzin
	private String stHour;

	// godzina zakończenia nadgodzin
	private String endHour;

	// ilość godzin standby'a pierwszego dnia
	private String sb1Day;
	// ilość godzin stanby'a drugiego dnia
	private String sb2Day;

	// gettery i settery
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStHour() {
		return stHour;
	}

	public void setStHour(String stHour) {
		this.stHour = stHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getSb1Day() {
		return sb1Day;
	}

	public void setSb1Day(String sb1Day) {
		this.sb1Day = sb1Day;
	}

	public String getSb2Day() {
		return sb2Day;
	}

	public void setSb2Day(String sb2Day) {
		this.sb2Day = sb2Day;
	}

	/*
	 * // gettery i settery public void setStartTime(Date stTime) {
	 * this.startTime = stTime; }
	 * 
	 * public Date getStartTime() { return startTime; }
	 * 
	 * public void setEndTime(Date eTime) { this.endTime = eTime; }
	 * 
	 * public Date getEndTime() { return endTime; }
	 * 
	 * public void setStartHour(String sh) { this.stHour = sh; }
	 * 
	 * public String getStartHour() { return stHour; }
	 * 
	 * public void setEndHour(String eh) { this.endHour = eh; }
	 * 
	 * public String getEndHour() { return endHour; }
	 * 
	 * public void setAccountName(String aName) { this.accountName = aName; }
	 * 
	 * public String getAccountName() { return accountName; }
	 * 
	 * public void setSb1Day(String sbOn) { this.sb1Day = sbOn; }
	 * 
	 * public String getSb1Day() { return sb1Day; }
	 * 
	 * public void setSb2Day(String sbD) { this.sb2Day = sbD; }
	 * 
	 * public String getSb2Day() { return sb2Day; }
	 */
}
