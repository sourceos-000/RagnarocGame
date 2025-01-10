/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamerpg;

/**
 *
 * @author RIZKI ABD RAHMAN
 */
public class PlayerTeam extends Team {

    @Override
    public void attack(Team targetTeam) {
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) { // Cek apakah hero masih hidup
                Hero enemyHero = targetTeam.getRandomAliveHero(); // Ambil hero musuh yang masih hidup
                if (enemyHero != null) {
                    int damage = hero.getAttackPower(); // Hitung damage
                    enemyHero.takeDamage(damage); // Kurangi HP musuh
                    System.out.println(hero.getName() + " menyerang " + enemyHero.getName() + " dan mengurangi " + damage + " HP!");
                }
            }
        }
    }

   
}
