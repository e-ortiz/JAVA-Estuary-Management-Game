package possibleAdvancingChange;
public enum QuestName {
	//advancing thought but haven't implement
	//save for possible future use
	phragmites("phragmites",-20,50),
	desecratedLand("desecratedLand",-20,50),
	mosquitoSwarm("mosquitoSwarm",-20,50),
	tillLand("tillLand",20,50),
	plantSeeds("plantSeeds",20,50),
	waterSeeds("rotary_excavator",20,50);
	
	private String name = null;
	private int damage = -1;
	private int hp =0;
	
	private QuestName(String s,int damage, int hp){
		name = s;
		this.damage = damage;
		this.hp=50;
	}
	
	public String getName(){
		return name;
	}
	
	public int getDamage(){
		return damage;
	}
	public int gethp() {
		return hp;
	}
}
