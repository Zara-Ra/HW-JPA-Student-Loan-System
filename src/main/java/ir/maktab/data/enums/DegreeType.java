package ir.maktab.data.enums;

public enum DegreeType {
    ASSOCIATE_DEGREE,
    BACHELOR,
    MASTER,
    PHD,
    CONTINUOUS_PHD,
    DISCONTINUOUS_PHD;
    public DegreeGroup toDegreeGroup() {
        return switch (this) {
            case ASSOCIATE_DEGREE, BACHELOR -> DegreeGroup.GROUP1;
            case MASTER, PHD, CONTINUOUS_PHD -> DegreeGroup.GROUP2;
            case DISCONTINUOUS_PHD -> DegreeGroup.GROUP3;
        };
    }
}
