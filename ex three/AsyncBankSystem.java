import java.util.concurrent.CompletableFuture;

public class AsyncBankSystem {

    public static void main(String[] args) {
        System.out.println("Bat dau xu ly giao dich...\n");

        String customerId = "KH001";
        double amountToTransfer = 500.0;

        CompletableFuture<Void> transactionProcess = CompletableFuture

            .supplyAsync(() -> authenticateCustomer(customerId))

            .thenApplyAsync(isAuthenticated -> {
                if (!isAuthenticated) {
                    throw new RuntimeException("Xac minh khach hang that bai!");
                }
                return checkBalance(customerId, amountToTransfer);
            })

            .thenAcceptAsync(isBalanceSufficient -> {
                if (!isBalanceSufficient) {
                    throw new RuntimeException("So du khong du de giao dich!");
                }
                transferMoney(customerId, amountToTransfer);
            })
            
            // Xử lý lỗi cho toàn bộ chuỗi
            .exceptionally(ex -> {
                System.err.println("[LOI] Giao dich bi huy bo: " + ex.getMessage());
                return null;
            });

        transactionProcess.join();
        
        System.out.println("\nket thuc chuong trinh.");
    }



    private static boolean authenticateCustomer(String customerId) {
        System.out.println("[1] Đang xac minh thong tin khach hang " + customerId + "...");
        delay(1000); // Trễ 1 giây
        System.out.println("-> xac minh thanh cong.");
        return true;
    }

    private static boolean checkBalance(String customerId, double amount) {
        System.out.println("[2] kiem tra so du  " + customerId + "...");
        delay(1500);
        

        double currentBalance = 1000.0; 
        boolean isOk = currentBalance >= amount;
        
        if (isOk) {
            System.out.println("-> So du hop le.");
        } else {
            System.out.println("-> So du khong du.");
        }
        return isOk; 
    }

    private static void transferMoney(String customerId, double amount) {
        System.out.println("[3] Đang bat dau chuyen khoan: $" + amount + "...");
        delay(2000); // Trễ 2 giây
        System.out.println("-> [THANH CONG] Chuyen khoan hoan tat!");
    }


    private static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}