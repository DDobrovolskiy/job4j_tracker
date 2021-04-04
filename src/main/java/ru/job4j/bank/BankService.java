package ru.job4j.bank;

import ru.job4j.inheritance.Pacient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        try {
            User user = findByPassport(passport);
            if (!users.get(user).contains(account)){
                users.get(user).add(account);
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public User findByPassport(String passport) {
        for (Map.Entry<User, List<Account>> user : users.entrySet()) {
            if(user.getKey().getPassport().equals(passport)) {
                return user.getKey();
            }
        }
        //return null;
        throw new NullPointerException("User don`t found");
    }

    public Account findByRequisite(String passport, String requisite) {
        try {
            User user = findByPassport(passport);
            int index = users.get(user).indexOf(new Account(requisite, 0));
            return users.get(user).get(index);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
            //throw new NullPointerException("Account don`t found");
            return null;
        }
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        try {
            Account accountSrc = findByRequisite(srcPassport, srcRequisite);
            Account accountDest = findByRequisite(destPassport, destRequisite);
            if (accountSrc.getBalance() < amount) {
                System.out.println("Pff... your need money");
                return false;
            } else {
                accountSrc.setBalance(accountSrc.getBalance() - amount);
                accountDest.setBalance(accountDest.getBalance() + amount);
                return true;
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        BankService bankService = new BankService();
        bankService.addAccount("sdfdfs", new Account("g333", 100));


        List<Account> accounts = new ArrayList<>();
        String requisite = "3fdsbb9";
        accounts.add(new Account("3fdsbb9", 140));
        int index = accounts.indexOf(new Account(requisite, -1));
        Account find = accounts.get(index);
        System.out.println(find.getRequisite() + " -> " + find.getBalance());
    }
}
