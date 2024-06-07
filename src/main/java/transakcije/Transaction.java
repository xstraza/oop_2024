package transakcije;

public class Transaction {
    private int amount;
    private String senderReceiver;
    private String date;
    private TransactionType transactionType;
    private String category;

    public Transaction(int amount, String senderReceiver, String date, TransactionType transactionType, String category) {
        this.amount = amount;
        this.senderReceiver = senderReceiver;
        this.date = date;
        this.transactionType = transactionType;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSenderReceiver() {
        return senderReceiver;
    }

    public void setSenderReceiver(String senderReceiver) {
        this.senderReceiver = senderReceiver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", senderReceiver='" + senderReceiver + '\'' +
                ", date='" + date + '\'' +
                ", transactionType=" + transactionType +
                '}';
    }
}
