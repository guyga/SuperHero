package com.example.android.superhero.network.responses

import com.example.android.superhero.database.model.SuperHeroPowerStatsDatabaseEntity

class SuperHeroPowerStatsNetworkEntity(
    var intelligence: String,
    var strength: String,
    var speed: String,
    var durability: String,
    var power: String,
    var combat: String,
) {
    fun toDatabasePowerStats(): SuperHeroPowerStatsDatabaseEntity {
        return SuperHeroPowerStatsDatabaseEntity(
            intelligence = this.intelligence,
            strength = this.strength,
            speed = this.speed,
            durability = this.durability,
            power = this.power,
            combat = this.combat
        )
    }
}