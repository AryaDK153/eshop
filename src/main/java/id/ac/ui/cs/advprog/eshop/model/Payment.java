package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Payment {
    private String id;
    private String method;
    private Map<String,String> paymentData;
    private String status;
    
    public Payment(String id, String method, Map<String, String> paymentData) {}

    public void setStatus(String string) {}
}
