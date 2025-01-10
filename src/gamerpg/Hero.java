package gamerpg;

public class Hero {

    private String name;
    private int health;
    private int baseAttackPower;
    private WeaponHero weapon;

    public Hero(String name, int health, int baseAttackPower, WeaponHero weapon) {
        this.name = name;
        this.health = health;
        this.baseAttackPower = baseAttackPower;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return baseAttackPower + weapon.getDamage();
    }
    
    public WeaponHero getWeapon() {
        return weapon;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

}
