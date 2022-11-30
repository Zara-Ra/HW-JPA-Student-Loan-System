package ir.maktab.util.validation;

@FunctionalInterface
public interface TriConsumer {
    void accept(String input,String regex,String errorMsg);

}
