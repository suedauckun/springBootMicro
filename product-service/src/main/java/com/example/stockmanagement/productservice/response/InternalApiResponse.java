package com.example.stockmanagement.productservice.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class InternalApiResponse<T> {
    /*RestApi için tek tipte bir response yapısı oluşturuyoruz*/
    private FriendlyMessage friendlyMessage;
    private T payload;
    private boolean hasError;
    private List<String>errorMessages;
    private HttpStatus httpStatus;
}
