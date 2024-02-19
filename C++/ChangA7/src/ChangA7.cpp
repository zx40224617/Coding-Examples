//============================================================================
// Name        : ChangA7.cpp
// Author      : Yun Chung Chang
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;
#include "BST.h"

int main() {

	BST <int> mytree;
	int i;

	i=5;
	mytree.insert(i);
	i=3;					// gaddis p. 1162
	mytree.insert(i);
	i=8;
	mytree.insert(i);
	i=12;
	mytree.insert(i);
	i=9;
	mytree.insert(i);
	cout << "--------------------------------------------------------------" << endl;
	mytree.printRohit();
	cout << "--------------------------------------------------------------" << endl;

	cout << "in order" << endl;
	mytree.inOrderTraversal();
	cout << "pre order" << endl;
	mytree.preOrderTraversal();
	cout << "post order" << endl;
	mytree.postOrderTraversal();
	cout << "--------------------------------------------------------------" << endl;
	i = 12;
	bool b;
	b=mytree.search(i);
	if (b)
	{
		cout << "node 12 found" << endl;
	}
	else
	{
		cout << "node 12 not found" << endl;
	}
	i = 4;
	mytree.insert(i);
	cout << "--------------------------------------------------------------" << endl;
	cout << "after insert of node 4" << endl;
	mytree.printRohit();
	i = 12;
	mytree.udelete(i);
	cout << "--------------------------------------------------------------" << endl;
	cout << "after delete of node 12" << endl;
	mytree.printRohit();
	cout << "--------------------------------------------------------------" << endl;
	i = 5;
	mytree.udelete(i);
	cout << "--------------------------------------------------------------" << endl;
	cout << "after delete of node 5" << endl;
	mytree.printRohit();
	cout << "--------------------------------------------------------------" << endl;
	i = 9;
	mytree.udelete(i);
	cout << "--------------------------------------------------------------" << endl;
	cout << "after delete of node 9" << endl;
	mytree.printRohit();
	cout << "--------------------------------------------------------------" << endl;
	BST <int> myOtherTree;
	int array[14] = {60,50,70,30,53,80,35,57,75,32,40,77,48,45};	// malik p. 622
	for (int i=0;i<14;i++)
	{
		myOtherTree.insert(array[i]);
	}
	myOtherTree.printRohit();
	cout << "--------------------------------------------------------------" << endl;
	return 0;
}
