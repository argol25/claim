package pl.arek.claiming.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "finalClaiming")
public class ResultData {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String wBSel;
	private String oType;
	String sat,sun,mon,tue,wen,thu,fri;
	int weekNo;
	boolean checked = true;
	
	public ResultData() {
		wBSel = null;
		oType = null;
		sat = null;
		sun = null;
		mon = null;
		tue = null;
		wen = null;
		thu = null;
		fri = null;
	}
	
	public void setWBSel(String wbs){
		this.wBSel = wbs;
	}
	
	public String getWBSel(){
		return wBSel;		
	}
	
	public void setOType(String ot){
		this.oType = ot;
	}
	
	public String getOType(){
		return oType;
	}
	
	public void setMon(String mo){
		this.mon = mo;
	}
	
	public String getMon(){
		return mon;
	}
	
	public void setTue(String tu){
		this.tue = tu;
	}
	
	public String getTue(){
		return tue;
	}
	
	public void setWen(String we){
		this.wen = we;
	}
	
	public String getWen(){
		return wen;
	}
	
	public void setThu(String th){
		this.thu = th;
	}
	
	public String getThu(){
		return thu;
	}
	
	public void setFri(String fr){
		this.fri = fr;
	}
	
	public String getFri(){
		return fri;
	}
	
	public void setSat(String st){
		this.sat = st;
	}
	
	public String getSat(){
		return sat;
	}
	
	public void setSun(String su){
		this.sun = su;
	}
	
	public String getSun(){
		return sun;
	}
	
	public int getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(int weekNo) {
		this.weekNo = weekNo;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	
}
