package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
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

    public boolean isMethod(String method) {
        String[] methodList = {"VOUCHER", "CASH_ON_DELIVERY", "BANK_TRANSFER"};
        if (Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))) {
            return false;
        } else {
            return true;
        }
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

        if (method.equals("BANK_TRANSFER")) {
            String bankNameValue = paymentData.get("bankName");
            String referenceCodeValue = paymentData.get("referenceCode");

            return (bankNameValue != null && !bankNameValue.isEmpty())
                    && (referenceCodeValue != null && !referenceCodeValue.isEmpty());
        }

        return false;
    }
}
