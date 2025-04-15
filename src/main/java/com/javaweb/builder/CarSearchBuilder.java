package com.javaweb.builder;


public class CarSearchBuilder {
	private String line;
	private String brand;
	private String location;
	private Long carPriceFrom;
	private Long carPriceTo;
	
	public CarSearchBuilder (Builder builder) {
		this.line = builder.line;
		this.brand = builder.brand;
		this.location = builder.location;
		this.carPriceFrom = builder.carPriceFrom;
		this.carPriceTo = builder.carPriceTo;
	} 
	
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getCarPriceFrom() {
		return carPriceFrom;
	}
	public void setCarPriceFrom(Long carPriceFrom) {
		this.carPriceFrom = carPriceFrom;
	}
	public Long getCarPriceTo() {
		return carPriceTo;
	}
	public void setCarPriceTo(Long carPriceTo) {
		this.carPriceTo = carPriceTo;
	}
	
	
	public static class Builder {
		
		private String line;
		private String brand;
		private String location;
		private Long carPriceFrom;
		private Long carPriceTo;
		
		public CarSearchBuilder build () {
			return new CarSearchBuilder (this);
		}
		
		public Builder setLine(String line) {
			this.line = line;
			return this;
		}
		public Builder setBrand (String brand) {
			this.brand = brand;
			return this;
		}
		public Builder setLocation(String location) {
			this.location = location;
			return this;
		}
		public Builder setCarPriceFrom(Long carPriceFrom) {
			this.carPriceFrom = carPriceFrom;
			return this;
		}
		public Builder setCarPriceTo(Long carPriceTo) {
			this.carPriceTo = carPriceTo;
			return this;
		}
		
	}
	
}
