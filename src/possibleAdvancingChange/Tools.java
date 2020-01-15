package possibleAdvancingChange;

enum Tools {
	//save for possible future use
	HOE("hoe",1),
	SEED("seed",2),
	WATERING_CAN("watering_can",3),
	HERBICIDE("herbicide",4),
	SEEDLING("seedling",5),
	ROTARY_EXCAVATOR("rotary_excavator",6);
	
	private String name = null;
	private int num = -1;
	
	private Tools(String s,int num){
		name = s;
		this.num = num;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNum(){
		return num;
	}
}