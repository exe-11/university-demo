package uz.exe.unversitydemo.exception;

public class DataNotFoundException extends RuntimeException{
    private static final String NOT_FOUND_WITH_ID = "%s not found with id %d";

    private static final String NOT_FOUND_WITH_NAME = "%s not found with name %s";


    public DataNotFoundException(String message) {
        super(message);
    }

    public static DataNotFoundException of(String resource, Long id)
    {
        return new DataNotFoundException(String.format(NOT_FOUND_WITH_ID,resource,id));
    }

    public static DataNotFoundException of(String resource, String name)
    {
        return new DataNotFoundException(String.format(NOT_FOUND_WITH_ID,resource, name));
    }

}
