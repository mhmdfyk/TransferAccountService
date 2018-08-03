/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.bus;

import transfer.dto.AccountRequestDTO;
import transfer.dto.TransferRequestDTO;

/**
 *
 * @author REACH_MFAYEK
 */
public interface AccountService {

    public void validateAndCreateAccount(AccountRequestDTO accountRequestDTO) throws Exception;

    public void validateTransferMoneyDataAndCreateTransferRequestObj(TransferRequestDTO transferRequestDTO) throws Exception;

}
