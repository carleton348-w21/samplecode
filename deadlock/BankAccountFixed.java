class BankAccountFixed {
    private int balance;
    private int id;

    public BankAccountFixed(int balance, int id) {
        this.balance = balance;
        this.id = id;
    }
    
    public synchronized void withdraw(int amt) {
        balance -= amt;                  
    };                                   

    public synchronized void deposit(int amt) { 
        balance += amt;                  
    }                                    

    public void transferTo(int amt, BankAccountFixed other) {
        if (this.id < other.id)
            synchronized(this) {
                synchronized(other) {
                    this.withdraw(amt);                                   
                    other.deposit(amt);
                }
            }
        else
            synchronized(other) {
                synchronized(this) {
                    this.withdraw(amt);                                   
                    other.deposit(amt);
                }
            }
    }

    public static void main(String[] args) {
        BankAccountFixed one = new BankAccountFixed(1000,12345);
        BankAccountFixed two = new BankAccountFixed(2000,12346);

        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    one.transferTo(500,two);
                    System.out.println("Transfering 1");
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    two.transferTo(100,one);
                    System.out.println("Transfering 2");
                }
            }
        };
        t1.start();
        t2.start();
    }
}
