package gamerpg;

public abstract class Character {
    
    private String name;
    private int health;
    private int attackPower;

    Character(String name,int health, int attackPower){
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
    public boolean isAlive(){
       return this.health > 0;
    }
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " took " + damage + " damage. Health now: " + health);
    }
    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
