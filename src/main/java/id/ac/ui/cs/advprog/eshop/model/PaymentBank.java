package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentBank extends Payment{
    public PaymentBank(String id, String method, Map<String, String> paymentData, Order order) {
        super(id, method, paymentData, order);
    }

    @Override
    public boolean isPaymentDataValid(Map<String, String> paymentData) {
        String bankNameValue = paymentData.get("bankName");
        String referenceCodeValue = paymentData.get("referenceCode");

        return (bankNameValue != null && !bankNameValue.isEmpty())
                && (referenceCodeValue != null && !referenceCodeValue.isEmpty());
    }
}
