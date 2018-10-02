package com.silalahi.valentinus.app;

import java.util.List;
import java.util.Map;

public interface ParkingLot {
	public void createParkingLot(int numOfSlots);

	public int park(Car c) throws ParkingNotAvailableException;

	public int leave(int slotId);

	public Map<Slot, Car> getParkingSlotStatus();

	public List<Car> getRegistrationNumbers(String color);

	public Slot getSlotForCar(String registrationNumber);

	public List<Slot> getSlots(String color);
}
