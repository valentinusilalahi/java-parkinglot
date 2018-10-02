package com.silalahi.valentinus.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingLotImpl implements ParkingLot {
	private Map<Slot, Car> slotCarMap;
	private Map<String, List<Car>> colorCarListMap;
	private Map<String, Slot> registrationNumberSlotMap;

	PriorityQueue<Integer> freeSlots = new PriorityQueue<Integer>();

	public ParkingLotImpl() {
		slotCarMap = new HashMap<Slot, Car>();
		colorCarListMap = new HashMap<String, List<Car>>();
		registrationNumberSlotMap = new HashMap<String, Slot>();
	}

	public void createParkingLot(int numOfSlots) {
		for (int c = 1; c <= numOfSlots; c++) {
			slotCarMap.put(new Slot(c), null);
			freeSlots.add(c);
		}
	}

	public int park(Car c) throws ParkingNotAvailableException {
		int slotId = getFreeSlot();
		if (slotId > 0) {
			Slot s = new Slot(slotId);
			slotCarMap.put(s, c);
			addToColorCarListMap(c);
			addToRegistrationNumberSlotMap(c, s);
		} else {
			throw new ParkingNotAvailableException("Parking Full Coyyy!!!!");
		}
		return slotId;
	}

	private void addToRegistrationNumberSlotMap(Car c, Slot s) {
		registrationNumberSlotMap.put(c.getRegistrationNumber(), s);
	}

	private void addToColorCarListMap(Car c) {
		String color = c.getColor();
		List<Car> cars = colorCarListMap.get(color);
		if (c == null) {
			cars = new ArrayList<Car>();
			cars.add(c);
			colorCarListMap.put(color, cars);
		} else {
			cars.add(c);
		}
	}

	private int getFreeSlot() {
		if (freeSlots.size() > 0) {
			return freeSlots.poll();
		}
		return -1;
	}

	public int leave(int slotId) {
		Car c = slotCarMap.get(new Slot(slotId));
		slotCarMap.put(new Slot(slotId), null);
		removeFromColorCarList(c);
		removeFromRegistrationNumberSlotMap(c);
		freeSlots.add(slotId);
		return slotId;
	}

	private void removeFromRegistrationNumberSlotMap(Car c) {
		registrationNumberSlotMap.remove(c.getRegistrationNumber());
	}

	private void removeFromColorCarList(Car c) {
		String color = c.getColor();
		List<Car> cars = colorCarListMap.get(color);
		if (cars == null) {

		} else {
			cars.remove(getFreeSlot());
		}
	}

	public Map<Slot, Car> getParkingSlotStatus() {

		return this.slotCarMap;
	}

	public List<Car> getRegistrationNumbers(String color) {
		return colorCarListMap.get(color);
	}

	public Slot getSlotForCar(String registrationNumber) {
		if (registrationNumberSlotMap.containsKey(registrationNumber))
			return registrationNumberSlotMap.get(registrationNumber);
		else
			return null;
	}

	public List<Slot> getSlots(String color) {
		List<Car> cars = colorCarListMap.get(color);
		List<Slot> slots = new ArrayList<Slot>();
		for (Car c : cars) {
			Slot slot = registrationNumberSlotMap.get(c.getRegistrationNumber());
			slots.add(slot);
		}
		return slots;
	}
}
