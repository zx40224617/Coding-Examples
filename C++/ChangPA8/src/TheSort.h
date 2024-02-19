/*
 * TheSort.h
 *
 *  Created on: May 12, 2023
 *      Author: phoebe.wang
 */

#ifndef THESORT_H_
#define THESORT_H_
#include <iostream>
#include <string>
using namespace std;

template <class T> class TheSort {

public:
	T* data;
	int size;
	bool (*comp) (T, T);
	void (*swap) (T&, T&);


	TheSort(T[], int, bool (*) (T, T), void (*) (T&, T&));
	virtual ~TheSort();
	void sortAlgo ();
};

#endif /* THESORT_H_ */
