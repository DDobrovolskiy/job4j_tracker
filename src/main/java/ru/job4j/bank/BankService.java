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
            User user = findByPassport(passport);
            if (user != null) {
                if (!users.get(user).contains(account)) {
                    users.get(user).add(account);
                }
            }
    }

    public User findByPassport(String passport) {
        for (Map.Entry<User, List<Account>> user : users.entrySet()) {
            if (user.getKey().getPassport().equals(passport)) {
                return user.getKey();
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
            User user = findByPassport(passport);
            if (user != null) {
                int index = users.get(user).indexOf(new Account(requisite, 0));
                return users.get(user).get(index);
            }
            return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account accountSrc = findByRequisite(srcPassport, srcRequisite);
        Account accountDest = findByRequisite(destPassport, destRequisite);
        if (accountSrc == null && accountDest == null) {
            return false;
        } else if (accountSrc.getBalance() < amount) {
            System.out.println("Pff... your need money");
            return false;
        } else {
            accountSrc.setBalance(accountSrc.getBalance() - amount);
            accountDest.setBalance(accountDest.getBalance() + amount);
            return true;
        }
    }

    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        String requisite = "3fdsbb9";
        accounts.add(new Account("3fdsbb9", 140));
        int index = accounts.indexOf(new Account(requisite, -1));
        Account find = accounts.get(index);
        System.out.println(find.getRequisite() + " -> " + find.getBalance());
    }
}
