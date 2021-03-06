package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @InjectMocks
    private VipParkingStrategy vipParkingStrategy;

    @Mock
    private CarDaoImpl carDao;

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */

        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        Car vipCar1 = createMockCar("vipCar1");
        Car vipCar2 = createMockCar("vipCar2");
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("vipParkingLot", 1);
        parkingLot.getParkedCars().add(vipCar1);
        ParkingLot parkingLot1 = new ParkingLot("vipParkingLot2", 1);
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        when(carDao.isVip(anyString())).thenReturn(true);

        //when
        Receipt actualReceipt = vipParkingStrategy.park(parkingLots, vipCar2);

        //then
        verify(vipParkingStrategy, times(1)).createReceipt(parkingLot1, vipCar2);
        assertEquals("vipCar2", actualReceipt.getCarName());
        assertEquals("vipParkingLot2", actualReceipt.getParkingLotName());

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */

        //given
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        Car vipCar1 = createMockCar("vipCar1");
        Car notVipCar2 = createMockCar("notVipCar2");
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("vipParkingLot", 1);
        parkingLot.getParkedCars().add(vipCar1);
        parkingLots.add(parkingLot);
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        when(carDao.isVip(anyString())).thenReturn(false);

        //when
        Receipt actualReceipt = vipParkingStrategy.park(parkingLots, notVipCar2);

        //then
        verify(vipParkingStrategy, times(1)).createNoSpaceReceipt(notVipCar2);
        assertEquals("notVipCar2", actualReceipt.getCarName());
        assertEquals("No Parking Lot", actualReceipt.getParkingLotName());

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car vipCar = createMockCar("A");
        when(carDao.isVip(anyString())).thenReturn(true);

        //when
        boolean actual = vipParkingStrategy.isAllowOverPark(vipCar);

        //then
        assertTrue(actual);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car vipCar = createMockCar("B");
        when(carDao.isVip(anyString())).thenReturn(true);

        //when
        boolean actual = vipParkingStrategy.isAllowOverPark(vipCar);

        //then
        assertFalse(actual);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car notVipCar = createMockCar("A");
        when(carDao.isVip(anyString())).thenReturn(false);

        //when
        boolean actual = vipParkingStrategy.isAllowOverPark(notVipCar);

        //then
        assertFalse(actual);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car notVipCar = createMockCar("B");
        when(carDao.isVip(anyString())).thenReturn(false);

        //when
        boolean actual = vipParkingStrategy.isAllowOverPark(notVipCar);

        //then
        assertFalse(actual);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
