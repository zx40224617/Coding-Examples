/*
 * TheSort.cpp
 *
 *  Created on: May 12, 2023
 *      Author: phoebe.wang
 */

#include "TheSort.h"
#include "Student.h"
#include <iostream>
#include <string>
using namespace std;




template <class T>
TheSort<T>::TheSort(T* arr, int size, bool (*comp) (T, T), void (*swap) (T&, T&)) {
	this -> data = arr;
	this -> size = size;
	this -> comp = comp;
	this -> swap = swap;

}

template <class T>
TheSort<T>::~TheSort() {
	delete data;
	delete comp;
	delete swap;
}


template <class T>
void TheSort<T>::sortAlgo(){
	int i, j;
	for (i = 0; i < size - 1; i++){
		for (j = 0; j < size - 1; j++){
			if (comp(data[j], data[j+1])){
				swap (data[j], data[j+1]);
			}
		}
	}
	return;
}
