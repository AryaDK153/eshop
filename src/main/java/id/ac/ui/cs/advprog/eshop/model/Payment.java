package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;

@Getter
public class Payment {
    private String id;
    private String method;
    private Map<String,String> paymentData;
    private String status;
    
    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;

        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }

        if (method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            if (paymentData.size() != 1 || !paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException();
            }
            String code = paymentData.get("voucherCode");
            int numericCount = 0;
            for (int i = 0; i < code.length(); i++) {
                if (Character.isDigit(code.charAt(i))) {
                    numericCount++;
                }
            }
            if (code.length() != 16 || !code.startsWith("ESHOP") || numericCount != 8) {
                this.status = PaymentStatus.REJECTED.getValue();
            } else {
                this.status = PaymentStatus.SUCCESS.getValue();
            }
        }
        if (method.equals(PaymentMethod.CASH_ON_DELIVERY.getValue())) {
            String[] mustHaveKeys = {"address", "deliveryFee"};
            if (paymentData.size() != 2) {
                throw new IllegalArgumentException();
            } else {
                for (String key : mustHaveKeys) {
                    if (!paymentData.containsKey(key)) {
                        throw new IllegalArgumentException();
                    }
                }
            }
            String address = paymentData.get("address");
            if (address.isEmpty()) {
                this.status = PaymentStatus.REJECTED.getValue();
            } else {
                String deliveryFee = paymentData.get("deliveryFee");
                if (deliveryFee.isEmpty()) {
                    this.status = PaymentStatus.REJECTED.getValue();
                } else {
                    this.status = PaymentStatus.SUCCESS.getValue();
                }
            }
        }
    }

//    public void setStatus(String status) {
//        if (!PaymentStatus.contains(status)) {
//            throw new IllegalArgumentException();
//        } else {
//            this.status = status;
//        }
//    }
}
