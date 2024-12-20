package com.wsb.findapart.data

object DropdownMapping {

    val cityMapping = mapOf(
        "Warszawa" to "warszawa",
        "Kraków" to "krakow",
        "Wrocław" to "wroclaw",
        "Gdańsk" to "gdansk",
        "Poznań" to "poznan",
        "Szczecin" to "szczecin",
        "Gdynia" to "gdynia",
        "Białystok" to "bialystok",
        "Radom" to "radom",
        "Rzeszów" to "rzeszow",
        "Łódź" to "lodz",
        "Katowice" to "katowice",
        "Lublin" to "lublin",
        "Częstochowa" to "czestochowa",
        "Bydgoszcz" to "bydgoszcz"
    )

    val typeMapping = mapOf(
        "Block of flats" to "blockOfFlats",
        "Apartment building" to "apartmentBuilding",
        "Tenement" to "tenement"
    )

    val squareMetersMapping = mapOf(
        "below 40 m²" to "squareMeters <= 40",
        "40–60 m²" to "squareMeters > 40 AND squareMeters <= 60",
        "60–80 m²" to "squareMeters > 60 AND squareMeters <= 80",
        "above 80 m²" to "squareMeters > 80"
    )

    val roomsMapping = mapOf(
        "1–3 rooms" to "rooms <= 3",
        "4–6 rooms" to "rooms > 3 AND rooms <= 6"
    )

    val floorMapping = mapOf(
        "1–5 floor" to "floor <= 5",
        "6–10 floor" to "floor > 5 AND floor <= 10",
        "above 10th floor" to "floor > 10"
    )

    val centreDistanceMapping = mapOf(
        "0–5 km" to "centreDistance <= 5",
        "5–10 km" to "centreDistance > 5 AND centreDistance <= 10",
        "above 10 km" to "centreDistance > 10"
    )

    val ownershipMapping = mapOf(
        "Condominium" to "condominium",
        "Cooperative" to "cooperative"
    )

    val priceMapping = mapOf(
        "below 500k PLN" to "price <= 500000",
        "500k-1mln PLN" to "price > 500000 AND price <= 1000000",
        "1mln-1,5mln PLN" to "price > 1000000 AND price <= 1500000",
        "1,5mln-2mln PLN" to "price > 1500000 AND price <= 2000000",
        "above 2mln PLN" to "price > 2000000"
    )

}