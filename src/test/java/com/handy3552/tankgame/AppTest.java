package com.handy3552.tankgame;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class AppTest {
    
/*
 * 1.画出地图，并设置沼泽点；
 * 2.坦克在地图上移动，上北下南，左西右东，
 * 3.坦克移动到地图边界时无法继续移动；
 * 4.坦克掉进沼泽地时无法移动；
 * 5.坦克掉进沼泽地时向控制台发出求救信号sos；
 * 6.其他坦克来救援，救援成功后掉进沼泽地的坦克位置是掉进沼泽的前一步的位置；
 * 7.每个坦克初始积分为100分，掉进沼泽地的坦克积分减10，救援坦克的积分加10（不考虑救援坦克路上掉进沼泽的情况）；
 * 
 * */
	
	private GameMap gameMap;
	private ControllCenter controllCenter;
	
	@Before
    public void setUp() {
    	 gameMap = new GameMap();
         gameMap.setMap(new Position(1000,1000));
         gameMap.setBogs(Arrays.asList(new Position(50, 50), new Position(80, 80)));
         controllCenter = mock(ControllCenter.class);
    }
			
    @Test
    public void testApp() {
        //should
    	
    	//when
    	
    	//then
    }
    
    //5.坦克掉进沼泽地时向控制台发出求救信号sos；
    @Test
    public void should_send_sos_to_controllCenter_when_tank_lost_in_bog() {
        //should
    	Tank tank = new Tank();
        tank.setMap(gameMap);
        tank.setPosition(990, 50);
        tank.setPoint(100);
        tank.setControllCenter(controllCenter);
    	//when
        doNothing().when(controllCenter).sos();
    	//then
    	Mockito.verify(controllCenter, times(1)).sos();
    }
    
    //4.坦克掉进沼泽地时无法移动；
    @Test
    public void should_stop_when_tank_lost_in_bog() {
    	Tank tank = new Tank();
        tank.setMap(gameMap);
        tank.setPosition(990, 50);
        tank.setPoint(100);
        tank.setControllCenter(controllCenter);
    	//when
    	tank.move("E", 950);//40,50
    	//then
    	assertEquals(new Position(50, 50), tank.getPosition());
    }
    
    @Test
    public void should_fail_when_tank_move_to_boundary() {
        //should
    	Tank tank = new Tank();
        tank.setMap(gameMap);
        tank.setPosition(990, 50);
        tank.setPoint(100);
        tank.setControllCenter(controllCenter);
    	//when
    	tank.move("E", 20);
    	//then
    	assertEquals(new Position(1000, 50), tank.getPosition());
    }
    
    @Test
    public void should_tank_can_move() {
    	Tank tank = new Tank();
        tank.setMap(gameMap);
        tank.setPosition(990, 50);
        tank.setPoint(100);
        tank.setControllCenter(controllCenter);
        //when
    	tank.move("E", 5);
    	
    	assertEquals("移动位置有误", new Position(45, 50), tank.getPosition());
    }
    
    @Test
    public void should_make_map_for_tank() {
       //assertSame(expected, actual);
    }
    
    
}
