package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {

        if (!isMethod(method)) {
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }

        if (!isPaymentDataValid(paymentData)) {
            this.status = "REJECTED";
        } else {
            this.status = "SUCCESS";
        }

        this.id = id;
        this.order = order;
        this.paymentData = paymentData;

    }

    public void setStatus(String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
    }

    public boolean isMethod(String method) {
        return PaymentMethod.contains(method);
    }

    public boolean isPaymentDataValid(Map<String, String> paymentData) {
        if (method.equals("VOUCHER")) {
            String value = paymentData.get("voucherCode");
            int digitNumeric = 0;
            for (int i = 0; i < value.length(); i++) {
                // Check if the character is a numeric digit
                if (Character.isDigit(value.charAt(i))) {
                    digitNumeric++; // Increment the count if it's a numeric digit
                }
            }

            return (value.length() == 16) && (value.startsWith("ESHOP")) && digitNumeric == 8;
        }

        if (method.equals("CASH_ON_DELIVERY")) {
            String addressValue = paymentData.get("address");
            String deliveryFeeValue = paymentData.get("deliveryFee");

            return (addressValue != null && !addressValue.isEmpty())
                    && (deliveryFeeValue != null && !deliveryFeeValue.isEmpty());
        }

        return false;
    }
}
