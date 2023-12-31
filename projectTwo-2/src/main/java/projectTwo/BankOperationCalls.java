package projectTwo;

public class BankOperationCalls {
    static private account accountType;

    /**
     * Sets local accountType variable to avoid having to pass accountT from other class.
     * @param accountT - specific account set by user
     */
    public static void set_accountType(account accountT){accountType = accountT;}

    /**
     * Method containing switch statement to avoid repeated code and improve maintainability (for
     * savings and checkings).
     * @param accountT - specific account set by user and received by this class.
     */
    public static void switch_for_Sav_and_Check(account accountT){
        set_accountType(accountT);
        String str;
        do {
            str = Prompts.make_selection_prompt();
            switch (str) {
                //Under Deposit option
                case "1":
                    BankOperationCalls.deposit_call();
                    break;
                //Under widthdraw Option
                case "2":
                    BankOperationCalls.withdraw_call();
                    break;
                //Under Payment Option
                case "3":
                    BankOperationCalls.payment_call();
                    break;
                //Under Transfer Option
                case "4":
                    BankOperationCalls.transfer_call();
                    break;
                //Under view Balance Option
                case "5":
                    accountType.display_account_info();
                    break;
                case "6":
                    System.out.println("Thank You for banking with UTEP\n Goodbye!");
                    break;
            } //end of the switch
        }while(!str.equals("6"));
        CustomerFinder.find_specific_account(Prompts.prompt_account_type());
        //Go back to the previous menu where it displays the Checkings, Savings, and Credit.
        //Run_Bank.available_actions_specific(specified1);
    }

    /**
     * Call of account interface's deposit method.
     */
    public static void deposit_call(){
        double amount = Prompts.enter_strInt_prompt("Enter Deposit Amount: $");
        accountType.deposit(amount);
    }

    /**
     * Call of account interface's withdraw method.
     */
    public static void withdraw_call(){
        double amount = Prompts.enter_strInt_prompt("Enter Withdraw Amount: $");
        accountType.withdraw(amount);
    }

    /**
     * Call of account interface's payment method.
     */
    public static void payment_call(){
        double amount = Prompts.enter_strInt_prompt("Enter Payment Amount: $");
        while (amount > accountType.balance()) {
            amount = Prompts.enter_strInt_prompt("INSUFFICIENT FUNDS!! Try entering a different Payment amount: $");
        }
        account pay_acc = CustomerFinder.find_specific_account(Prompts.prompt_account_type());
        if (pay_acc != null) {
            accountType.payment(pay_acc, amount);
        }
    }

    /**
     * Call of account interface's transfer method.
     */
    public static void transfer_call(){
        double amount = Prompts.enter_strInt_prompt("Enter the amount you wish to Transfer: $");
        while (amount > accountType.balance()) {
            amount = Prompts.enter_strInt_prompt("INSUFFICIENT FUNDS!! Try Entering a Different Amount");
        }
        account transferAccount = CustomerFinder.find_specific_account(Prompts.prompt_account_type());
        if (transferAccount != null) {
            accountType.transfer(transferAccount, amount);
        }
    }

    /**
     * Method containing switch statement to avoid repeated code and improve maintainability (for
     * credit).
     * @param accountT - specific account set by user and received by this class.
     */
    public static void credit_switch_for_Sav_and_Check(account accountT){
        set_accountType(accountT);
        String str;
        do {
        str = Prompts.make_selection_prompt_credit();
        switch (str) {
            //Under Deposit option
            case "1":
                BankOperationCalls.credit_deposit_call();
                break;
            //Under widthdraw Option
            case "2":
                BankOperationCalls.withdraw_call();
                break;
            //Under Payment Option
            case "3":
                BankOperationCalls.credit_payment_call();
                break;
            //Under Transfer Option
            case "4":
                BankOperationCalls.credit_transfer_call();
                break;
            //Under view Balance Option
            case "5":
                accountType.display_account_info();
                break;
            case "6":
                System.out.println("Thank You for banking with UTEP\n Goodbye!");
                break;
            }
        }while(!str.equals("6"));


    }

    /**
     * Call of credit's deposit method via account interface.
     */
    public static void credit_deposit_call(){
        double amount = Prompts.enter_strInt_prompt("Enter Balance Payment Amount: $");
        accountType.deposit(amount);
    }

    /**
     * Call of credit's payment method via account interface.
     */
    public static void credit_payment_call(){
        double amount = Prompts.enter_strInt_prompt("Enter Payment Amount: $");
        while (Math.abs(((Credit) accountType).get_Credit_Max()) - amount < 0) {
            amount = Prompts.enter_strInt_prompt("INSUFFICIENT FUNDS!! Try entering a different Payment amount: $");
        }
        System.out.println("What kind of account are we making a payment towards?");
        account pay_acc = CustomerFinder.find_specific_account(Prompts.prompt_account_type());
        if (pay_acc != null) {
            accountType.payment(pay_acc, amount);
            accountType.display_account_info();
            pay_acc.display_account_info();
        }
    }

    /**
     * Call of credit's transfer method via account interface.
     */
    public static void credit_transfer_call(){
        double amount = Prompts.enter_strInt_prompt("Enter the amount you wish to Transfer: $");
        while (Math.abs(((Credit) accountType).get_Credit_Max()) - amount < 0) {
            amount = Prompts.enter_strInt_prompt("INSUFFICIENT FUNDS!! Try Entering a different Transfer amount: $");
        }
        System.out.println("Choose recipient AccountType to Transfer Funds.");
        account transferAccount = CustomerFinder.find_specific_account(Prompts.prompt_account_type());
        if (transferAccount != null) {
            accountType.transfer(transferAccount, amount);
            accountType.display_account_info();
            transferAccount.display_account_info();
        }
    }
}
