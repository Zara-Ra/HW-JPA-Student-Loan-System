package ir.maktab.data.enums;

public enum City {
    TEHRAN(CityType.CAPITAL),
    RASHT(CityType.METROPOLIS),
    ESFAHAN(CityType.METROPOLIS),
    TABRIZ(CityType.METROPOLIS),
    SHIRAZ(CityType.METROPOLIS),
    AHVAZ(CityType.METROPOLIS),
    QOM(CityType.METROPOLIS),
    MASHHAD(CityType.METROPOLIS),
    KARAJ(CityType.METROPOLIS),
    OTHER( CityType.OTHER);

    public final CityType type;

    City(CityType type) {
        this.type = type;
    }

    public static City valueOfCity(String cityName) {
        try {
            return City.valueOf(cityName);
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }
}
