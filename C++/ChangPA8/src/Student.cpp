/*
 * Student.cpp
 *
 *  Created on: May 12, 2023
 *      Author: phoebe.wang
 */

#include "Student.h"
#include <iostream>
#include <string>
using namespace std;

Student::Student() {
	// TODO Auto-generated constructor stub

}

Student::~Student() {

}

Student::Student(string name, int ID, float tuition){
	this -> name = name;
	this -> ID = ID;
	this -> tuition = tuition;
}

void Student::display(){
	cout << "Name: " << name << " ID: " << ID << " tuition: " << tuition << endl;
}

