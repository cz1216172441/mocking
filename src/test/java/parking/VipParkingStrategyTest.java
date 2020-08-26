package parking;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        Car vipCar1 = new Car("vipCar1");
        Car vipCar2 = new Car("vipCar2");
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("vipParkingLot", 1);
        parkingLot.getParkedCars().add(vipCar1);
        ParkingLot parkingLot1 = new ParkingLot("vipParkingLot2", 1);
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        CarDaoImpl carDao = mock(CarDaoImpl.class);
        when(carDao.isVip(anyString())).thenReturn(true);
        Receipt actualReceipt = vipParkingStrategy.park(parkingLots, vipCar2);
        verify(vipParkingStrategy, times(1)).createReceipt(parkingLot1, vipCar2);
        assertEquals("vipCar2", actualReceipt.getCarName());
        assertEquals("vipParkingLot2", actualReceipt.getParkingLotName());

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */


    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
