package ir.maktab.data.enums;

public enum DegreeType {
    ASSOCIATE_DEGREE(2),
    BACHELOR(4),
    CONTINUOUS_MASTER(6),
    DISCONTINUOUS_MASTER(2),
    PHD(5),
    CONTINUOUS_PHD(5),
    DISCONTINUOUS_PHD(5);
    public final int graduateYears;
    DegreeType(int graduateYears){
        this.graduateYears = graduateYears;
    }
    public DegreeGroup toDegreeGroup() {
        return switch (this) {
            case ASSOCIATE_DEGREE, BACHELOR -> DegreeGroup.GROUP1;
            case CONTINUOUS_MASTER,DISCONTINUOUS_MASTER , PHD, CONTINUOUS_PHD -> DegreeGroup.GROUP2;
            case DISCONTINUOUS_PHD -> DegreeGroup.GROUP3;
        };
    }
}
