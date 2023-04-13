package com.gdu.app01.xml06;

public class BmiCalculator {

	// field
	private double height;	// 단위 m
	private double weight;	// 단위 kg
	private double bmi;		// 몸무게(kg) / (키(m) * 키(m))
	private String health;	// < 20(저체중), <25(보통), >=25(비만)
	private Calculator calc;
	
	// constructor
	public BmiCalculator(double height, double weight, Calculator calc) {
		this.height = height;
		this.weight = weight;
		this.calc = calc;
		bmi = calc.div(weight, calc.mul)
	}
	
}
