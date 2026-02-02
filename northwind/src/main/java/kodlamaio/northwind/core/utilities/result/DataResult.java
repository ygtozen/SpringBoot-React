package kodlamaio.northwind.core.utilities.result;

public class DataResult<T> extends Result  {

    private T data;
    public DataResult(T data, boolean success, String message) { // Result'dan extend aldık
        super(success, message);
        this.data = data;
    }

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}

// DataResult<T> --> bir den fazla data tipi gelebileceği için Generic yapılar ile çalışıyoruz -- T data
