package com.threeone.mealplanner.test.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath*:applicationContext*.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=true)
public abstract class AbstractSpringCommonTest {

}
