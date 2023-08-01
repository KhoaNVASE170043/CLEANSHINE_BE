package com.be.bill;

import com.be.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    @Query("SELECT b from BillEntity b WHERE b.completeStatus = false and b.customer.id = :customer_id and b.serviceDetail.id = :service_detail and b.billStatus <> com.be.status.BillStatus.CUSTOMER_DENY")
    BillEntity getExistBill(@Param("customer_id") long customer_id, @Param("service_detail") long service_detail);

    @Query("SELECT b from BillEntity b WHERE b.completeStatus = false and b.customer.id = :customer_id")
    List<BillEntity> getCustomerBills(@Param("customer_id") long customer_id);

    @Query("SELECT b from BillEntity b WHERE b.completeStatus = true and b.customer.id = :customer_id")
    List<BillEntity> getHistoryBills(@Param("customer_id") long customer_id);

    @Query("SELECT b from BillEntity b WHERE b.completeStatus = true and b.employee.id = :employee_id")
    List<BillEntity> getEmployeeHistory(@Param("employee_id") long employee_id);

    @Query("SELECT b from BillEntity b WHERE b.completeStatus = false and b.billStatus = com.be.status.BillStatus.ADMIN_ASSIGN and b.employee.id = :employee_id")
    List<BillEntity> getEmployeeSchedule(@Param("employee_id") long employee_id);

    @Query("SELECT b from BillEntity b WHERE b.completeStatus = false and b.billStatus = com.be.status.BillStatus.STAFF_ACCEPT and b.employee.id = :employee_id")
    List<BillEntity> getAcceptedBills(@Param("employee_id") long employee_id);

    @Query("SELECT b from BillEntity b WHERE b.billStatus <> com.be.status.BillStatus.CUSTOMER_DENY")
    List<BillEntity> getAllBills();
}
