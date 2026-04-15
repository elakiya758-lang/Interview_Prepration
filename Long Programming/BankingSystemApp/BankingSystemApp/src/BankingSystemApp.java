import java.time.LocalDateTime;
import java.util.*;

class Transaction {
    String type;
    double amount;
    String fromAccount;
    String toAccount;
    LocalDateTime timestamp;

    public Transaction(String type, double amount, String from, String to) {
        this.type = type;
        this.amount = amount;
        this.fromAccount = from;
        this.toAccount = to;
        this.timestamp = LocalDateTime.now();
    }
}
class Account {
    String accountId;
    String accountType;
    String aadharNo;
    String holderName;
    double balance;
    List<Transaction> transactions = new ArrayList<>();
    double minBalance;

    public Account(String accountId, String holderName, String accountType, String aadharNo, String bankName, String branch, String IFSC) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = 0.0;
        this.accountType = accountType;
        this.aadharNo = aadharNo;


        if (accountType.equalsIgnoreCase("SAVINGS")) {
            this.minBalance = 1000;
        }
        else {
            this.minBalance = 0;
        }
    }
    public String toString(){
        return "Account Id: " + this.accountId + ", Account Type: " + this.accountType + "Balance" + this.balance;
    }
}

class Bank {
    String bankName;
    String Branch;
    String IFSC;
    Map<String, Account> accounts = new HashMap<>();

    public Bank(String name, String Branch, String IFSC) {
        this.bankName = name;
        this.Branch = Branch;
        this.IFSC = IFSC;
    }
}

public class BankingSystemApp {

    private final Map<String, Account> accountRegistry = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);


    public void createAccount(String accountId, String holderName,String accountType, String aadharNo,String bankName, String Branch, String IFSC) {
        if (accountRegistry.containsKey(accountId)) {
            System.out.println("! Error: Account already exists.");
            return;
        }
        accountRegistry.put(accountId, new Account(accountId,holderName,accountType,aadharNo,bankName,Branch,IFSC));
        System.out.println("√ Account created for " +holderName);
    }

    public void deposit(String accId, double amount) {
        Account acc = accountRegistry.get(accId);

        if (acc == null) {
            System.out.println("! Error: Account not found.");
            return;
        }

        acc.balance += amount;
        acc.transactions.add(new Transaction("DEPOSIT", amount, accId, accId));

        System.out.println("√ Deposited $" + amount);
    }

    public void withdraw(String accId, double amount) {
        Account acc = accountRegistry.get(accId);

        if (acc == null) {
            System.out.println("! Error: Account not found.");
            return;
        }

        if (acc.balance - amount < acc.minBalance) {
            System.out.println("! Error: Minimum balance requirement violated.");
            return;
        }

        if (acc.balance < amount) {
            System.out.println("! Error: Insufficient balance.");
            return;
        }

        acc.balance -= amount;
        acc.transactions.add(new Transaction("WITHDRAW", amount, accId, accId));

        System.out.println("√ Withdrawn $" + amount);
    }
    public void balance(String accountId) {
    Account acc = accountRegistry.get(accountId);
    if (acc == null)
    {
        System.out.println("! Error: Account not found.");
        return;
    }
        System.out.println("Current Balance"+acc.balance);
}
public void transfer(String fromAccount, String toAccount, double amount)
{
    Account from= accountRegistry.get(fromAccount);
    Account to = accountRegistry.get(toAccount);
    if (fromAccount == null || toAccount == null)
    {
    System.out.println("! Error: Account not found.");
    return;
    }
    if(from.balance < amount)
    {
        System.out.println("! Error: Insufficient balance.");
        return;
    }
    if (from.balance - amount < from.minBalance)
    {
        System.out.println("! Error: Minimum balance requirement violated.");
        return;
    }
    from.balance -= amount;
    to.balance += amount;

    Transaction t = new Transaction("TRANSFER", amount, fromAccount, toAccount);
    from.transactions.add(t);
    to.transactions.add(t);
    System.out.println("Transfer $" + amount);
}
public void printAccounts()
{
    for (Account acc : accountRegistry.values())
    {
        System.out.println(acc);
    }
}
public static void main(String[] args)
{
        BankingSystemApp app = new BankingSystemApp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Baking System App");
        while (true)
        {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Account");
            System.out.println("3. Withdraw Account");
            System.out.println("4.balance");
            System.out.println("5. Transfer Account");
            System.out.println("6.Print Account");
            System.out.println("7. Exit");
            try
            {
                int choice=scanner.nextInt();
                switch (choice)
                {
                    case 1:
                        System.out.println("Aadhar No : ");
                        String aadharNo = scanner.next();
                        if(aadharNo.length()!=12)
                        {
                            System.out.println("! Error: Invalid Aadhar No.");
                            return;
                        }
                        System.out.println("Account Id");
                        String accountId = scanner.next();
                        System.out.println("Account Holder : ");
                        String holderName = scanner.next();
                        System.out.println("Account Type : ");
                        String accountType = scanner.next();
                        System.out.println("Bank Name : ");
                        String bankName = scanner.next();
                        System.out.println("Branch : ");
                        String Branch = scanner.next();
                        System.out.println("IFSC : ");
                        String IFSC = scanner.next();
                        app.createAccount(accountId,aadharNo,accountType,holderName,bankName,Branch,IFSC);
                        break;
                    case 2:
                        System.out.println("Account Id");
                        accountId = scanner.next();
                        System.out.println("Amount");
                        double amount =scanner.nextDouble();
                        app.deposit(accountId,amount);
                        break;
                    case 3:
                        System.out.println("Account Id");
                        accountId = scanner.next();
                        System.out.println("Amount");
                        amount =scanner.nextDouble();
                        app.withdraw(accountId,amount);
                        break;
                    case 4:
                        System.out.println("Account Id");
                        accountId = scanner.next();
                        app.balance(accountId);
                        break;
                    case 5:
                        System.out.println("From Account Id : ");
                        String fromAccount = scanner.next();
                        System.out.println("To Account Id : ");
                        String toAccount = scanner.next();
                        System.out.println("Amount : ");
                        amount =scanner.nextDouble();
                        app.transfer(fromAccount,toAccount,amount);
                        break;
                    case 6:
                        System.out.println("Account Id : ");
                        accountId = scanner.next();
                        app.printAccounts();
                        break;
                    case 7:
                        System.out.println("Shuting down");
                        System.exit(0);
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }

            }
            catch (Exception e)
            {
                System.out.println("! Error: Invalid choice.");
            }
        }

}
}