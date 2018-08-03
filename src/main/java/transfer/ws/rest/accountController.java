/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.ws.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import transfer.bus.AccountService;
import transfer.dto.AccountRequestDTO;
import transfer.dto.CommonDTO;
import transfer.dto.TransferRequestDTO;
import transfer.entities.Accounts;
import transfer.entities.TransferRequest;

/**
 *
 * @author REACH_MFAYEK
 */
@RestController
@RequestMapping("/account/")
public class accountController {
    
    @Autowired
    AccountService accountService;
    /**
     * Retrieves representation of an instance of
     * transfer.ws.rest.service.GenericResource
     *
     * @return an instance of ae.dm.common.dto.CommonDTO
     */
    @RequestMapping(value = "/createAccount/", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public CommonDTO createAccount(@RequestBody AccountRequestDTO accountRequestDTO) throws Exception {

        CommonDTO commonDTO = new CommonDTO();
        try {
            accountService.validateAndCreateAccount(accountRequestDTO);
            
            commonDTO.setStatus("Success");
            commonDTO.setStatusMessage("Account Created Successfully");
            return commonDTO;
        } catch (Exception ex) {
            // if failed
            ex.printStackTrace();
            commonDTO.setStatus("failed");
            commonDTO.setStatusMessage("Failed To Create Account");
            commonDTO.setErrorMessage(ex.getMessage());
            if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
                String exceptionCause = ex.getStackTrace()[0].toString();
                commonDTO.setExceptionCause(exceptionCause);
            }
            return commonDTO;
        }
    }
    

    @RequestMapping(value = "/transferMoney/", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public CommonDTO transferMoney(@RequestBody TransferRequestDTO transferRequestDTO) throws Exception {

        CommonDTO commonDTO = new CommonDTO();
        try {
            
            accountService.validateTransferMoneyDataAndCreateTransferRequestObj(transferRequestDTO);
            
            commonDTO.setStatus("Success");
            commonDTO.setStatusMessage("Transfered Money Successfully");
            return commonDTO;
        } catch (Exception ex) {
            // if failed
            commonDTO.setStatus("failed");
            commonDTO.setStatusMessage("Failed To Transfered Money");
            commonDTO.setErrorMessage(ex.getMessage());
            if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
                String exceptionCause = ex.getStackTrace()[0].toString();
                commonDTO.setExceptionCause(exceptionCause);
            }
            return commonDTO;
        }
    }

}
