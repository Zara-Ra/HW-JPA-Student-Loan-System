package ir.maktab.data.enums;

public enum City {
    TEHRAN("Tehran",CityType.CAPITAL),
    RASHT("Rasht",CityType.METROPOLIS),
    ESFAHAN("Esfahan",CityType.METROPOLIS),
    TABRIZ("Tabriz",CityType.METROPOLIS),
    SHIRAZ("Shiraz",CityType.METROPOLIS),
    AHVAZ("Ahvaz",CityType.METROPOLIS),
    QOM("Qom",CityType.METROPOLIS),
    MASHHAD("Mashhad",CityType.METROPOLIS),
    KARAJ("Karaj",CityType.METROPOLIS);

    private final String name;
    private final CityType type;
    City(String name,CityType type){
        this.name = name;
        this.type = type;
    }
}
