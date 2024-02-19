/*
 * Beverage.h
 *
 *  Created on: May 3, 2023
 *      Author: phoebe.wang
 */

#ifndef BEVERAGE_H_
#define BEVERAGE_H_

#include <iostream>
#include <string>
using namespace std;

class Beverage {
protected:
	string ID;
	string name;
	string manufacturer;
	double price;
	int sold;
	char onMenu;

public:
	Beverage(string, string, string, double, int, char);
	virtual ~Beverage();
	virtual void display ();
	virtual void salesCheck ();
	string getID();
};

class Coffee : public Beverage {
private:
	char roast;
	string origin;
	int caffeine;
public:
	Coffee(string, string, string, double, int, char,
			char, string, int);
	virtual ~Coffee();
	virtual void display ();
	virtual void salesCheck ();
};

class Tea : public Beverage{
private:
	char color;
	int caffeine;
	string flavor;
public:
	Tea(string, string, string, double, int, char,
			char, int, string);
	virtual ~Tea();
	virtual void display ();
	virtual void salesCheck ();
};

class Soda : public Beverage{
private:
	string flavor;
	int calories;
	char caffeinated;
public:
	Soda(string, string, string, double, int, char,
			string, int, char);
	virtual ~Soda();
	virtual void display ();
	virtual void salesCheck ();
};

class Water : public Beverage{
	char filtered;
	char spring;
public:
	Water(string, string, string, double, int, char,
			char, char);
	virtual ~Water();
	virtual void display ();
	virtual void salesCheck ();
};

class Juice : public Beverage{
	string flavor;
	int calories;
	int percent;
	char blend;
public:
	Juice(string, string, string, double, int, char,
			string, int, int, char);
	virtual ~Juice();
	virtual void display ();
	virtual void salesCheck ();
};

#endif /* BEVERAGE_H_ */
