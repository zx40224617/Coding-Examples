//============================================================================
// Name        : ChangPA8.cpp
// Author      : Yun Chung Chang
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <fstream>
#include "Student.h"
#include "TheSort.h"
using namespace std;

bool compareFunc (Student a, Student b){
	if (a.ID > b.ID){
		return true;
	} else {
		return false;
	}
}

void swapFunc (Student &a, Student &b){
	Student temp;
	temp.name = a.name;
	temp.ID = a.ID;
	temp.tuition = a.tuition;
	a.name = b.name;
	a.ID = b.ID;
	a.tuition = b.tuition;
	b.name = temp.name;
	b.ID = temp.ID;
	b.tuition = temp.tuition;

}

int main() {
	int maxSize = 100;
	Student arr [maxSize];
	ifstream infile;
	infile.open("a8data.txt", ios::in);
	if (!infile.is_open()){
		cout << "Error, the file is not open successfully";
		return 1;
	}

	int count = 0;
	while (!infile.eof()){
		string name;
		int ID;
		float tuition;
		string line;
		getline(infile, line);
		name = line.substr(0, 20);
		ID = stoi(line.substr(20, 5));
		tuition = stof(line.substr(25, 7));
		Student s = Student(name, ID, tuition);
		arr[count] = s;
		count++;
	}

	infile.close();

	cout << "Contents of the array after build" << endl;

	for (int i = 0; i < count; i++){
		arr[i].display();
	}


	bool (*comp) (Student, Student);
	void (*swap) (Student&, Student&);


	comp = compareFunc;
	swap = swapFunc;


	TheSort <Student> Test (arr, count, comp, swap);


	Test.sortAlgo();

	for (int i = 0; i < count; i++){
		arr[i].display();
	}

	return 0;
}



