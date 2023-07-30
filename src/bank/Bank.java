package bank;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Bank
{
	Map<String,Customer> customerMap;
	Bank(){
		customerMap = new HashMap <String,Customer>();
	}
	public static void main(String []args)
	{
                Scanner sc  =  new Scanner(System.in);
		String username,password;double amount;
		Customer customer=new Customer();
                Bank bank  =  new Bank();
		int choice=0;
                
	outer:	while(true)
		{
			System.out.println("\n\t\t====================");
			System.out.println("\t\tBANK    OF     JAVA");
			System.out.println("\t\t====================\n");
			System.out.println("\t\t1. Register account.");
			System.out.println("\t\t2. Login.");
			System.out.println("\t\t3. Update accounts.");
			System.out.println("\t\t4. Exit.");
			System.out.print("\nEnter your choice : ");
                        try{
                            choice = sc.nextInt();
                            sc.nextLine();      
                        }
                        catch(Exception e){
                                customer.error();
                                break;
			}
                        int ch=choice;
			switch(choice)
			{
				case 1:
                                        
                                        System.out.println("\n\t\t======================");
                                        System.out.print("\t\tAccount Registeration");
                                        System.out.println("\n\t\t======================");
					System.out.print("\nEnter name : ");
					String name = sc.nextLine();
					System.out.print("Enter address : ");
					String address = sc.nextLine();
					System.out.print("Enter contact number : ");
					String phone = sc.nextLine();
					System.out.println("\nSet username : ");
					username = sc.next();
					while(bank.customerMap.containsKey(username))
					{
						System.out.println("Username already exists. Set again : ");
						username = sc.next();
					}
                                        System.out.println("***********************************************************************************************************");
					System.out.println("Set a password (minimum 8 chars; minimum 1 digit, 1 lowercase, 1 uppercase, 1 special character[!@#$%^&*_]) ");
					System.out.println("***********************************************************************************************************");
                                        System.out.println("Enter password: ");
                                        password = sc.next();
					sc.nextLine();
					while(!password.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
					{
						System.out.println("\t\t|----------->Invalid password condition<-----------| \nSet again :");
						password=sc.next();
					}
					System.out.print("Enter initial deposit : ");
					sc.hasNextDouble();
					while(!sc.hasNextDouble()){
						System.out.println("Invalid amount. Enter again :");
						sc.nextDouble();
					}
					amount=sc.nextDouble();
                                        customer = new Customer();
					customer.data(username,password,name,address,phone,amount,new Date());
                                        bank.customerMap.put(username,customer);
					break;                                             
				case 2:
					System.out.println("\n***********************************************************************************************************");
                                        System.out.println("Enter username : ");
					username = sc.next();
					sc.nextLine();
					System.out.println("Enter password : ");
					password = sc.next();
					sc.nextLine();
                                        System.out.println("***********************************************************************************************************");
					if(bank.customerMap.containsKey(username))
					{
						customer = bank.customerMap.get(username);
						if(customer.password.equals(password))
						{
                                                System.out.println("\n\t\t==========================================");
						System.out.println("\t\t  W  E  L  C  O  M  E     Mr   /   Ms   "+customer.name.toUpperCase());
						System.out.println("\t\t===========================================");
							while(true){
								System.out.print("\n\t\t1. Deposit.");
                                                                System.out.println("\t\t2. Withdraw.");
								System.out.print("\n\t\t3. Transfer.");
								System.out.println("\t\t4. Last 5 transactions.");
								System.out.print("\n\t\t5. User information.");
                                                                System.out.println("\t6. Current Balance.");
								System.out.println("\n\t\t\t\t7. Log out.");
								System.out.print("\nEnter your choice : ");
								choice = sc.nextInt();
								sc.nextLine();
								switch(choice){
									case 1:
									       System.out.print("Enter amount : ");
									       while(!sc.hasNextDouble()){
										       System.out.println("Invalid amount. Enter again :");
										       sc.nextLine();
									       }
									       amount = sc.nextDouble();
									       sc.nextLine();
                                                                               System.out.println("\n***********************************************************************************************************");
	                                                                       customer.deposit(amount,new Date());
                                                                               System.out.println("***********************************************************************************************************");
									       break;  
                                                                        case 2:
									       System.out.print("Enter amount : ");
									       amount = sc.nextDouble();
									       sc.nextLine();
                                                                               System.out.println("\n***********************************************************************************************************");
	                                                                       customer.withdraw(amount,new Date());
                                                                               System.out.println("***********************************************************************************************************");
									       break;
									case 3:
									       System.out.print("\nEnter payee username : ");
									       username = sc.next();
									       sc.nextLine();
									       System.out.println("Enter amount : ");
									       while(!sc.hasNextDouble()){
										       System.out.println("Invalid amount. Enter again :");
										       sc.nextLine();
									       }
									       amount = sc.nextDouble();
									       sc.nextLine();
									       if(amount > 300000){
										       System.out.println("\n***********************************************************************************************************");
                                                                                       System.out.println("Transfer limit exceeded. Contact bank manager.");
										       System.out.println("***********************************************************************************************************");
                                                                                       break;     
									       }
									       if(bank.customerMap.containsKey(username)){
										       Customer payee = bank.customerMap.get(username);
                                                                                       System.out.println("\n***********************************************************************************************************");
										       payee.deposit(amount,new Date());
										       customer.withdraw(amount,new Date());
                                                                                       System.out.println("***********************************************************************************************************");
									       }
									       else{
										       System.out.println("Username doesn't exist.");}
									       break;
									case 4:
									       for(String transactions : customer.transactions){
                                                                                       System.out.println("\n***********************************************************************************************************");
										       System.out.println(transactions);
                                                                                       System.out.println("***********************************************************************************************************");
									       }
									       break;
									case 5:
                                                                               customer.putdata();
									       break;
                                                                        case 6:
                                                                            System.out.println("\n***********************************************************************************************************");
                                                                            System.out.println("Your current Balance is "+customer.balance);
                                                                            System.out.println("***********************************************************************************************************");
                                                                            break;
                                                                        case 7:
									       continue outer;
								        default:
									        System.out.println("Wrong choice !");
								}
							}
						}
						else{
							System.out.println("\nWrong username/password.");}
					}
					else{
						System.out.println("\nWrong username/password.");}
					break;
				case 3:
					System.out.println("Enter username : ");
					username = sc.next();
                                        System.out.println("***********************************************************************************************************");
					if(bank.customerMap.containsKey(username)){
						customer.update(new Date());}
					else{
						System.out.println("Username doesn't exist.");}
                                        
                                        try{
                                            for(String transactions1 : customer.transactions1){
                                             System.out.println(transactions1);}
                                        }
                                        catch(Exception e){
                                            customer.error();}
                                        
                                        System.out.println("***********************************************************************************************************");
					break;
				case 4:
					System.out.println("\n\n***********************************************************************************************************");
                                        System.out.println("Thank you for choosing Bank Of Java."); 
                                        System.out.println("***********************************************************************************************************");
					System.exit(0);
					break;
				default:
					System.out.println("Wrong choice !");
                        }
		}
	}
}

