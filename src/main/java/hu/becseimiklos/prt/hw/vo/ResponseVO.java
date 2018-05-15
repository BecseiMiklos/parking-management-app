package hu.becseimiklos.prt.hw.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseVO<T> {

    private boolean isSuccess = true;
    private String errorMessage;
    private List<T> data;

    public ResponseVO<T> setFailure(String errorMsg) {
        this.errorMessage = errorMsg;
        return this;
    }

    public ResponseVO<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

}
