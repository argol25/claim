package pl.arek.claiming.domain;

public class ResultData {

	private String WBSel;
	private String OType;
	Float sat,sun,mon,tue,wen,thu,fri;
	
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
	
	public void setMon(float mo){
		this.mon = mo;
	}
	
	public Float getMon(){
		return mon;
	}
	
	public void setTue(float tu){
		this.tue = tu;
	}
	
	public Float getTue(){
		return tue;
	}
	
	public void setWen(float we){
		this.wen = we;
	}
	
	public Float getWen(){
		return wen;
	}
	
	public void setThu(float th){
		this.thu = th;
	}
	
	public Float getThu(){
		return thu;
	}
	
	public void setFri(float fr){
		this.fri = fr;
	}
	
	public Float getFri(){
		return fri;
	}
	
	public void setSat(float st){
		this.sat = st;
	}
	
	public Float getSat(){
		return sat;
	}
	
	public void setSun(float su){
		this.sun = su;
	}
	
	public Float getSun(){
		return sun;
	}
	
}
