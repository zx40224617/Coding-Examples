/*
 * Student.h
 *
 *  Created on: May 12, 2023
 *      Author: phoebe.wang
 */

#ifndef STUDENT_H_
#define STUDENT_H_

#include <iostream>
#include <string>
using namespace std;

class Student {

public:
	string name;
	int ID;
	float tuition;

	Student();
	Student(string, int, float);
	virtual ~Student();
	void display();
};

#endif /* STUDENT_H_ */
