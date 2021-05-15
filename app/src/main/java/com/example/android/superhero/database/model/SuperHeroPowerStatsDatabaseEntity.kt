package com.example.android.superhero.database.model

import com.example.android.superhero.domain.model.SuperHeroPowerStats

class SuperHeroPowerStatsDatabaseEntity(
    var intelligence: String,
    var strength: String,
    var speed: String,
    var durability: String,
    var power: String,
    var combat: String,
) {
    fun toDomainPowerStats(): SuperHeroPowerStats {
        return SuperHeroPowerStats(
            intelligence = this.intelligence,
            strength = this.strength,
            speed = this.speed,
            durability = this.durability,
            power = this.power,
            combat = this.combat
        )
    }
}