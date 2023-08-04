package com.be.address;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
    AddressEntity findAddressEntityByCustomerInfo_Id(long customerId);
}
