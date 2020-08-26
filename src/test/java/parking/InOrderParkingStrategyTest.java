package parking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Car car = new Car("CAR");
        inOrderParkingStrategy.park(null, car);
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("lot", 10);
        parkingLots.add(parkingLot);
        Car car = new Car("CAR");
        inOrderParkingStrategy.park(parkingLots, car);
        verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot, car);

    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot("lot", 1);
        ParkingLot parkingLot2 = new ParkingLot("lot2", 1);
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot2);
        Car car = new Car("CAR");
        parkingLot.getParkedCars().add(new Car("CAR2"));
        inOrderParkingStrategy.park(parkingLots, car);
        verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot2, car);

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
