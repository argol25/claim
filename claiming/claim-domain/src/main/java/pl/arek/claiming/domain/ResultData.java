package pl.arek.claiming.domain;

public class ResultData {

	private String WBSel;
	private String OType;
	String sat,sun,mon,tue,wen,thu,fri;
	
	public ResultData() {
		WBSel = null;
		OType = null;
		sat = null;
		sun = null;
		mon = null;
		tue = null;
		wen = null;
		thu = null;
		fri = null;
	}
	
	public void setWBSel(String wbs){
		this.WBSel = wbs;
	}
	
	public String getWBSel(){
		return WBSel;		
	}
	
	public void setOType(String ot){
		this.OType = ot;
	}
	
	public String getOType(){
		return OType;
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
	
}
