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
	
	public void setWBS(String wbs){
		this.WBSel = wbs;
	}
	
	public String getWBS(){
		return WBSel;		
	}
	
	public void setOtype(String ot){
		this.OType = ot;
	}
	
	public String getOtype(){
		return OType;
	}
	
	public void setMon(float mo){
		this.mon = mo;
	}
	
	public float getMon(){
		return mon;
	}
	
	public void setTue(float tu){
		this.tue = tu;
	}
	
	public float getTue(){
		return tue;
	}
	
	public void setWen(float we){
		this.wen = we;
	}
	
	public float getWen(){
		return wen;
	}
	
	public void setThu(float th){
		this.thu = th;
	}
	
	public float getThu(){
		return thu;
	}
	
	public void setFri(float fr){
		this.fri = fr;
	}
	
	public float getFri(){
		return fri;
	}
	
	public void setSat(float st){
		this.sat = st;
	}
	
	public float getSat(){
		return sat;
	}
	
	public void setSun(float su){
		this.sun = su;
	}
	
	public float getSun(){
		return sun;
	}
	
}
