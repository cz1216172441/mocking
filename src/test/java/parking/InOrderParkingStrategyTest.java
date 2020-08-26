package parking;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InOrderParkingStrategyTest {

    private InOrderParkingStrategy inOrderParkingStrategy;

    @Before
    public void setUp() {
        inOrderParkingStrategy = new InOrderParkingStrategy();
    }

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
        // given
        Car car = mock(Car.class);
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        // when
        when(parkingLot.getName()).thenReturn("p1");
        when(car.getName()).thenReturn("car");
        when(parkingLot.isFull()).thenReturn(false);

        Receipt actualReceipt = inOrderParkingStrategy.park(parkingLots, car);

        // then
        assertEquals("p1", actualReceipt.getParkingLotName());
        assertEquals("car", actualReceipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        // given
        Car car = mock(Car.class);
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        // when
        when(parkingLot.getName()).thenReturn("p1");
        when(car.getName()).thenReturn("car");
        when(parkingLot.isFull()).thenReturn(true);

        Receipt actualReceipt = inOrderParkingStrategy.park(parkingLots, car);

        // then
        assertEquals("No Parking Lot", actualReceipt.getParkingLotName());
        assertEquals("car", actualReceipt.getCarName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
