package com.data.ox.domain.logic.dao;

import com.data.ox.core.common.Page;
import com.data.ox.core.data.ClientData;

public interface ClientDaoI extends DaoI<ClientData> {

    Page<ClientData> findClient(String searchKey, int pageNumber, int limit);
}
