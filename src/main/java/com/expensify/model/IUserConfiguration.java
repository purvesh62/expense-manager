package com.expensify.model;

import com.expensify.persistenceLayer.IUserConfigurationDAOService;

public interface IUserConfiguration {

    IUserConfiguration getUserConfiguration(int userId);

    IUserConfigurationDAOService getUserConfigurationDAOService();

    boolean setUserConfiguration(int userId);
}
