package ir.maktab.data.enums;

public enum DegreeType {
    ASSOCIATE_DEGREE(2, "associate"),
    BACHELOR(4, "bachelor"),
    CONTINUOUS_MASTER(6, "continuous master"),
    DISCONTINUOUS_MASTER(2, "discontinuous master"),
    PHD(5, "phd"),
    CONTINUOUS_PHD(5, "continuous phd"),
    DISCONTINUOUS_PHD(5, "discontinuous phd");
    public final int graduateYears;
    private final String name;
    private static final int DAYS_OF_YEAR = 365;

    DegreeType(int graduateYears, String name) {
        this.graduateYears = graduateYears;
        this.name = name;
    }

    public int getGraduationInDays() {
        return this.graduateYears * DAYS_OF_YEAR;
    }

    public DegreeGroup toDegreeGroup() {
        return switch (this) {
            case ASSOCIATE_DEGREE, BACHELOR -> DegreeGroup.GROUP1;
            case CONTINUOUS_MASTER, DISCONTINUOUS_MASTER, PHD, CONTINUOUS_PHD -> DegreeGroup.GROUP2;
            case DISCONTINUOUS_PHD -> DegreeGroup.GROUP3;
        };
    }
}
