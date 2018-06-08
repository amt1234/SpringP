package com.bridgeit.services;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.bridgeit.module.Circle;
import com.bridgeit.module.Triangle;

@Component("service")
//@ComponentScan(basePackages="")
public class ShapeService {
public Triangle triangle;

public Circle circle;

public Triangle getTriangle() {
	return triangle;
}
@Autowired
public void setTriangle(Triangle triangle) {
	this.triangle = triangle;
}
public Circle getCircle() {
	return circle;
}
@Autowired
public void setCircle(Circle circle) {
	this.circle = circle;
}
}
