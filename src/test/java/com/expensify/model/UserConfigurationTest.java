package com.expensify.model;

import com.expensify.persistenceLayerMock.UserConfigurationDAOServiceMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserConfigurationTest {

    @Test
    public void getUserConfigurationSuccessTest(){
        UserConfigurationDAOServiceMock userConfigurationDAOServiceMock = new UserConfigurationDAOServiceMock();
        IUserConfiguration userConfiguration = userConfigurationDAOServiceMock.getUserConfigurations(1);
        Assertions.assertNotEquals(null,userConfiguration);
    }
    @Test
    public void getUserConfigurationFailureTest(){
        UserConfigurationDAOServiceMock userConfigurationDAOServiceMock = new UserConfigurationDAOServiceMock();
        IUserConfiguration userConfiguration = userConfigurationDAOServiceMock.getUserConfigurations(2);
        Assertions.assertEquals(null,userConfiguration);
    }
    @Test
    public void setUserConfigurationSuccessTest(){
        UserConfigurationDAOServiceMock userConfigurationDAOServiceMock = new UserConfigurationDAOServiceMock();
        boolean result = userConfigurationDAOServiceMock.updateUserConfiguration(1,1,1,1);
        Assertions.assertEquals(true,result);
    }
    @Test
    public void setUserConfigurationFailureTest(){
        UserConfigurationDAOServiceMock userConfigurationDAOServiceMock = new UserConfigurationDAOServiceMock();
        boolean result = userConfigurationDAOServiceMock.updateUserConfiguration(0,1,1,1);
        Assertions.assertEquals(false,result);
    }
}
