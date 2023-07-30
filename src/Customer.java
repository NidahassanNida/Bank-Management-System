import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface SavingsAccount
{
	final double rate = 0.04,limit = 10000,limit1 = 200;
	void deposit(double n,Date d);
	void withdraw(double n,Date d);
        void update(Date d);
}
interface Info extends SavingsAccount
{
        void error();
	void putdata();

}
public class Customer implements Info
{
	String username,password,name,address,phone;
	double balance;
	ArrayList<String> transactions;
        ArrayList<String> transactions1;
        ArrayList<String> transactions2;
        
        void data(String username,String password,String name,String address,String phone,double balance,Date date){
            this.username = username;
	    this.password = password;
	    this.name = name;
	    this.address = address;
	    this.phone = phone;
	    this.balance = balance;
            date=new Date();
	    transactions  =  new ArrayList<String>(5);
            transactions1  =  new ArrayList<String>(5);
            transactions2  =  new ArrayList<String>(5);
	    addTransaction(String.format("Initial deposit: " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
            addTransactionTime(String.format("Account created on  " + "%1$tD "+" at "+"%1$tT.",date));
        }
//********************************************************************************************************************************************************       
	@Override
        public void update(Date date)
	{
            if(balance>= 10000){
                balance += rate*balance;
		}
	    else{
                balance -= (int)(balance/100.0);
		}
		addTransactionUpdate(String.format("Account updated. Balance: " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
//********************************************************************************************************************************************************
	@Override
	public void deposit(double amount,Date date){
            try{
                if(amount < 0){
                    throw new IllegalArgumentException(Double.toString(amount));
                }
                this.balance += amount;
                System.out.println(amount+" has been deposited to your account");
            }
            catch(IllegalArgumentException e){
                error();
            }
            addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" credited to your account. Balance: " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
        }
//********************************************************************************************************************************************************
	@Override
	public void withdraw(double amount,Date date){
            try{
            if(amount>balance){
                throw new IllegalArgumentException("\nAmount out of range "+Double.toString(amount));
            }
            this.balance -= amount;
            System.out.println(amount+" has been debited from your account");
        }
        catch(IllegalArgumentException e){
            error();
        }
	addTransaction(String.format(NumberFormat.getCurrencyInstance().format(amount)+" debited from your account. Balance: " +NumberFormat.getCurrencyInstance().format(balance)+" as on " + "%1$tD"+" at "+"%1$tT.",date));
	}
//********************************************************************************************************************************************************
        @Override
        public void putdata(){
            System.out.println("\n***********************************************************************************************************");
            System.out.println("Accountholder name : "+this.name);
	    System.out.println("Accountholder address : "+this.address);
	    System.out.println("Accountholder contact : "+this.phone);
            try{
                for(String transactions2 : transactions2){
                    System.out.println(transactions2);}
            }
            catch(Exception e){
                error();
            }
            System.out.println("***********************************************************************************************************");
        }      
//********************************************************************************************************************************************************
        private void addTransactionUpdate(String message){
	    transactions1.add(0,message);
	    if(transactions1.size()>5){
                transactions1.remove(5);
                transactions1.trimToSize();
	    }
	} 
        private void addTransactionTime(String message){
	    transactions2.add(0,message);
	    if(transactions2.size()>5){
                transactions2.remove(5);
		transactions2.trimToSize();
	    }
	}  
//********************************************************************************************************************************************************
	private void addTransaction(String message){
            transactions.add(0,message);
            if(transactions.size()>5){
                transactions.remove(5);
                transactions.trimToSize();
            }
	}
//********************************************************************************************************************************************************
        @Override
        public void error() {
             System.out.println("\n***********************************************************************************************************");
             System.out.print("\t\t\t\t     |-----------> ERROR <--------| \n");
             System.out.print("\t\t                         OR\n");
             System.out.print("\t\t\t\t     There may be some othere Problem\n");
             System.out.print("\t\t\t\t   It is better for you to try again...!\n");
             System.out.println("***********************************************************************************************************");
             
 }
}