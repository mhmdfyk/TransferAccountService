/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.bus;

import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transfer.controler.AccountRepository;
import transfer.controler.TransferRequestRepository;
import transfer.dto.AccountRequestDTO;
import transfer.dto.TransferRequestDTO;
import transfer.entities.Accounts;
import transfer.entities.TransferRequest;

/**
 *
 * @author REACH_MFAYEK
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRequestRepository transferRequestRepository;

    @Override
    public void validateAndCreateAccount(AccountRequestDTO accountRequestDTO) throws Exception {
        if (accountRequestDTO == null
                || accountRequestDTO.getAccountId() == null
                || accountRequestDTO.getBalance() == null
                || accountRequestDTO.getEmployeeName() == null) {
            throw new Exception("Account Id or Balance or Employee Name are missing");
        }

        if (accountRequestDTO.getBalance().doubleValue() < 0) {
            throw new Exception("Balance should be positive value");
        }

        if (accountRepository.findByAccountId(accountRequestDTO.getAccountId()) != null) {
            throw new Exception("Account Id already avaliable : (" + accountRequestDTO.getAccountId().intValue() + ")");
        }

        Accounts account = new Accounts();
        account.setAccountId(accountRequestDTO.getAccountId());
        account.setEmployeeName(accountRequestDTO.getEmployeeName());
        account.setBalance(accountRequestDTO.getBalance());

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void validateTransferMoneyDataAndCreateTransferRequestObj(TransferRequestDTO transferRequestDTO) throws Exception {
        if (transferRequestDTO == null
                || transferRequestDTO.getYourAccount() == null
                || transferRequestDTO.getToAccount() == null
                || transferRequestDTO.getTransferredAmount() == null) {
            throw new Exception("YourAccount or ToAccount or TransferredAmount are missing");
        }
       
        Accounts yourAccount = accountRepository.findByAccountId(new BigInteger(transferRequestDTO.getYourAccount().toString()));
        Accounts ToAccount   = accountRepository.findByAccountId(new BigInteger(transferRequestDTO.getToAccount().toString()));
        if (yourAccount == null) {
            throw new Exception("Your Account value is not valid Account");
        }

        if (ToAccount == null) {
            throw new Exception("To Account value is not valid Account");
        }

        if (transferRequestDTO.getYourAccount().doubleValue() == transferRequestDTO.getToAccount().doubleValue()) {
            throw new Exception("You Should Enter Two Different Accounts");
        }

        if (yourAccount.getBalance().doubleValue() < transferRequestDTO.getTransferredAmount().doubleValue()) {
            throw new Exception("you don't have available balance in your current account ");
        }

        yourAccount.setBalance(yourAccount.getBalance().subtract(transferRequestDTO.getTransferredAmount()));
        ToAccount.setBalance(ToAccount.getBalance().add(transferRequestDTO.getTransferredAmount()));

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAccountFromId(yourAccount);
        transferRequest.setAccountToId(ToAccount);
        transferRequest.setTransferredAmount(transferRequestDTO.getTransferredAmount());
        
        accountRepository.save(yourAccount);
        accountRepository.save(ToAccount);
        transferRequestRepository.save(transferRequest);
    }

}
