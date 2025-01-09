package gamerpg;

public class EnemyTeam extends Team {

    @Override
    public void attack(Team targetTeam) {
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) { // Cek apakah hero masih hidup
                Hero playerHero = targetTeam.getRandomAliveHero(); // Ambil hero pemain yang masih hidup
                if (playerHero != null) {
                    int damage = hero.getAttackPower(); // Hitung damage
                    playerHero.takeDamage(damage); // Kurangi HP pemain
                    System.out.println(hero.getName() + " menyerang " + playerHero.getName() + " dan mengurangi " + damage + " HP!");
                }
            }
        }
    }

}
