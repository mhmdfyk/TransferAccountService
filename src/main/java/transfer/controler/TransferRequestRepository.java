/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package transfer.controler;

import org.springframework.data.jpa.repository.JpaRepository;
import transfer.entities.TransferRequest;

/**
   Access the EntityManager from Spring Data JPA
 * @author REACH_MFAYEK
 */  
public interface TransferRequestRepository extends JpaRepository<TransferRequest,Integer> {
    
}
