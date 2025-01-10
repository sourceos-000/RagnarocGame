/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamerpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RIZKI ABD RAHMAN
 */
public abstract class Team {

    // List untuk menyimpan hero-hero dalam tim
    protected List<Hero> heroes;

    // Constructor untuk inisialisasi list heroes
    public Team() {
        heroes = new ArrayList<>();
    }

    // Method untuk menambahkan hero ke dalam tim
    public void addHero(Hero hero) {
        if (heroes.size() < 3) { // Tim hanya boleh berisi maksimal 3 hero
            heroes.add(hero);
        } else {
            System.out.println("Tim sudah penuh!");
        }
    }

    // Method untuk mendapatkan list heroes
    public List<Hero> getHeroes() {
        return heroes;
    }

    // Method abstrak untuk menyerang tim lain
    public abstract void attack(Team targetTeam);

    // Method untuk mendapatkan hero yang masih hidup secara acak
    public Hero getRandomAliveHero() {
        List<Hero> aliveHeroes = new ArrayList<>();
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                aliveHeroes.add(hero);
            }
        }
        if (!aliveHeroes.isEmpty()) {
            Random random = new Random();
            return aliveHeroes.get(random.nextInt(aliveHeroes.size()));
        }
        return null; // Jika tidak ada hero yang masih hidup
    }

    // Method untuk mengecek apakah tim sudah kalah (semua hero HP-nya <= 0)
    public boolean isDefeated() {
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                return false; // Masih ada hero yang hidup
            }
        }
        return true; // Semua hero sudah kalah
    }   
}
