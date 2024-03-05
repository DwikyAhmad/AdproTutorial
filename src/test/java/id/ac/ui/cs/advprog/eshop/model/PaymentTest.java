package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private Order order;


    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("f74a8e87-31b4-4a11-b1fb-37b5114a61fd", "VOUCHER",
                paymentData, this.order);

        assertEquals("f74a8e87-31b4-4a11-b1fb-37b5114a61fd", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(this.order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("f74a8e87-31b4-4a11-b1fb-37b5114a61fd", "NULL",
                    paymentData, this.order);
        });
    }

    @Test
    void testCreatePaymentRejectedPaymentDataVoucher() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "IAMAVOUCHER");

        Payment payment = new Payment("f74a8e87-31b4-4a11-b1fb-37b5114a61fd",
                "VOUCHER", paymentData, this.order);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedPaymentDataCOD() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "LUBANG BUAYA");

        Payment payment = new Payment("f74a8e87-31b4-4a11-b1fb-37b5114a61fd", "CASH_ON_DELIVERY",
                paymentData, this.order);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedPaymentDataBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "MANDIRI");


        Payment payment = new Payment("f74a8e87-31b4-4a11-b1fb-37b5114a61fd", "BANK_TRANSFER",
                paymentData, this.order);
        assertEquals("REJECTED", payment.getStatus());
    }
}
