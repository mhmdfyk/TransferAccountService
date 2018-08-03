/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.controler;

import java.math.BigInteger;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import transfer.entities.Accounts;

/**
 *
 * @author REACH_MFAYEK
 */
@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    /**
     *
     * @param accountId
     * @return
     */
     Accounts findByAccountId(@Param("accountId")BigInteger accountId);
}
