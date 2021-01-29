class BankAccount {
    private int balance;

    public synchronized void setBalance(int balance) {
        this.balance = balance;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void withdraw(int amount) {
        balance = balance - amount;
    }
}
