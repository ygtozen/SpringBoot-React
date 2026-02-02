package kodlamaio.northwind.core.utilities.result;

public class Result {
    private boolean success;
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this(success); // yukarıdaki tek parametreli success constructor'ını çağırıyor
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

}
