package com.data.ox.infrastructure.repository;

import com.data.ox.infrastructure.model.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    @Query("SELECT table FROM ClientModel table") // TODO
    Page<ClientModel> findClient(String searchKey, Pageable pageable);
}
