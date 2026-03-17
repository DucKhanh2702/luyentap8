
interface Payment {
    void processPayment(double amount);
}


class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
    }
}

class PayPalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

class CashPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Cash payment of $" + amount);
    }
}


class PaymentFactory {
    public static Payment getPaymentMethod(String type) {
        if (type == null) return null;
        
        switch (type.toUpperCase()) {
            case "CREDITCARD":
                return new CreditCardPayment();
            case "PAYPAL":
                return new PayPalPayment();
            case "CASH":
                return new CashPayment();
            default:
                throw new IllegalArgumentException("Unknown payment method: " + type);
        }
    }
}


public class PaymentSystem {
    public static void main(String[] args) {
        System.out.println("--- KICH HOAT HE THONG THANH TOAN ---");
        
        Payment payment1 = PaymentFactory.getPaymentMethod("CREDITCARD");
        payment1.processPayment(150.50);
        
        Payment payment2 = PaymentFactory.getPaymentMethod("PAYPAL");
        payment2.processPayment(89.99);
        
        Payment payment3 = PaymentFactory.getPaymentMethod("CASH");
        payment3.processPayment(20.00);
    }
}