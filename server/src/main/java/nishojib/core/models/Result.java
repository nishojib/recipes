package nishojib.models;

import java.util.List;

public class Result<T> {
    private List<T> data;
    private int count;

    public Result(List<T> data) {
        this.data = data;
        this.count = data.size();
    }
}
