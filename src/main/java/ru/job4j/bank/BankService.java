package ru.job4j.bank;

import ru.job4j.inheritance.Pacient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу простейшего банковского перевода
 * @author Dmitriy Dobrovolskiy
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей осуществляется в коллекции типа HashMap
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Добавляет пользователя в коллекцию
     * @param user пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Добавляет счет "{@param account}" к пользователю "{@link User}"
     * по номеру паспорта
     * @param passport номер паспорта
     * @param account счет
     */
    public void addAccount(String passport, Account account) {
            User user = findByPassport(passport);
            if (user != null) {
                if (!users.get(user).contains(account)) {
                    users.get(user).add(account);
                }
            }
    }

    /**
     * Находит пользователя "{@link User}" по данным его паспорта
     * если пользователя нет в колекции возвращает {@code null}
     * @param passport номер паспорта
     * @return возвращает ссылку на пользователя
     */
    public User findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
//        for (User user : users.keySet()) {
//            if (user.getPassport().equals(passport)) {
//                return user;
//            }
//        }
//        return null;
    }

    /**
     * Находит счет по данным паспорта пользователя "{@link User}"
     * и реквизитам счета {@link Account} привязанным к пользователю
     * если счета по данным реквизита нет в колекции возвращает {@code null}
     * @param passport данные паспорта
     * @param requisite реквизита счета {@link Account}
     * @return возращает ссылку на счет
     */
    public Account findByRequisite(String passport, String requisite) {
            User user = findByPassport(passport);
            if (user != null) {
                return users.get(user).stream()
                        .filter(account -> account.getRequisite().equals(requisite))
                        .findFirst()
                        .orElse(null);
                //for (Account account : users.get(user)) {
                //    if (account.getRequisite().equals(requisite)) {
                //        return account;
                //    }
                //}
            }
            return null;
    }

    /**
     * Обеспечивет трансфер денежный средст от одного пользователя к другому
     * по данным паспортов и реквизитам счетов
     * при этом обеспечивается валидация операции,
     * при успешном прохождении оперци возращается {@code true},
     * при нехватки средств, отсутствии счета или пользователя в коллекциях
     * возращается {@code false}
     * @param srcPassport паспортные данные отправителя
     * @param srcRequisite реквизиты счета отправителя
     * @param destPassport паспортные данные получателя
     * @param destRequisite реквизиты счета получателя
     * @param amount размер денежных средств перевода
     * @return возращает валидацию операции
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account accountSrc = findByRequisite(srcPassport, srcRequisite);
        Account accountDest = findByRequisite(destPassport, destRequisite);
        if (accountSrc == null && accountDest == null) {
            return false;
        } else if (accountSrc.getBalance() < amount) {
            System.out.println("Pff... your need money");
            return false;
        }
        accountSrc.setBalance(accountSrc.getBalance() - amount);
        accountDest.setBalance(accountDest.getBalance() + amount);
        return true;
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
