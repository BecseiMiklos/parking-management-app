package hu.becseimiklos.prt.hw.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * General Reponse value object, used to transfer data and the result of the request in REST calls.
 * @param <T> The type of the stored data.
 */
@Getter
@Setter
public class ResponseVO<T> {

    private boolean isSuccess = true;
    private String errorMessage;
    private List<T> data;

    /**
     * Sets the ResponseVO to failed state and stores the given error message.
     * @param errorMsg The error message sent to the client.
     * @return The ResponseVO.
     */
    public ResponseVO<T> setFailure(String errorMsg) {
        this.isSuccess = false;
        this.errorMessage = errorMsg;
        return this;
    }

    /**
     * Sets the data of the ResponseVO.
     * @param data The data to set.
     * @return The ResponseVO.
     */
    public ResponseVO<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    /**
     * Dedicated method to set one record to the ResponseVO.
     * @param data The record to be put into the ResponseVO.
     * @return The ResponseVO.
     */
    public ResponseVO<T> setSingleData(T data) {
        this.data = new ArrayList<>();
        this.data.add(data);
        return this;
    }

}
