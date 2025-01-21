package com.wsb.findapart.data

object ListMapping {

    val cityMapping = mapOf(
        "warszawa" to "Warszawa",
        "krakow" to "Kraków",
        "wroclaw" to "Wrocław",
        "gdansk" to "Gdańsk",
        "poznan" to "Poznań",
        "szczecin" to "Szczecin",
        "gdynia" to "Gdynia",
        "bialystok" to "Białystok",
        "radom" to "Radom",
        "rzeszow" to "Rzeszów",
        "lodz" to "Łódź",
        "katowice" to "Katowice",
        "lublin" to "Lublin",
        "czestochowa" to "Częstochowa",
        "bydgoszcz" to "Bydgoszcz"
    )

    val typeMapping = mapOf(
        "blockOfFlats" to "Block Of Flats",
        "apartmentBuilding" to "Apartment Building",
        "tenement" to "Tenement"
    )

    val ownershipMapping = mapOf(
        "condominium" to "Condominium",
        "cooperative" to "Cooperative"
    )

    val buildingMaterialMapping = mapOf(
        "brick" to "Brick",
        "concreteSlab" to "Concrete Slab",
        "" to "-"
    )

    val conditionMapping = mapOf(
        "premium" to "Premium",
        "low" to "Low",
        "" to "-"
    )

}