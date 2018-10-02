package com.silalahi.valentinus.app;

public class ParkingLotFactory {
	public static ParkingLot createParkingLot() {
		return new ParkingLotImpl();
	}
}
