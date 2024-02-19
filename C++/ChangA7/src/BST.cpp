/*
 * BST.cpp
 *
 *  Created on: Nov 5, 2022
 *      Author: phoebe.wang
 */

#include "BST.h"
#include <cmath>

template <class T> BST<T>::BST() {
	tRoot = nullptr;
}


template <class T> bool BST<T>::search(T &data){
	bool found = false;
	if (tRoot == nullptr){
		throw runtime_error("The tree is empty");
	} else {
		Tnode<T>* current = tRoot;
		while (current != nullptr && found == false){
			if (current -> nodeValue == data){
				found = true;
			} else if (current -> nodeValue > data){
				current = current -> Lchild;
			} else {
				current = current -> Rchild;
			}
		}
	}
	return found;
}

template <class T> void BST<T>::insert(T &data){
	Tnode<T>* newData = new Tnode<T>();
	Tnode<T>* trailing = new Tnode<T>();
	newData -> nodeValue = data;
	newData -> Lchild = nullptr;
	newData -> Rchild = nullptr;
	if (tRoot == nullptr){
		throw runtime_error("The tree is empty");
	} else {
		Tnode<T>* current = tRoot;
		while(current != nullptr){
			trailing = current;
			if ( (current -> nodeValue) == data){
				throw runtime_error("Duplicate data in the tree, can't insert");
			} else if ( current -> nodeValue > data){
				current = current -> Lchild;
			} else {
				current = current -> Rchild;
			}
		}
		if (trailing -> nodeValue > data){
			trailing -> Lchild = newData;
		} else {
			trailing -> Rchild = newData;
		}
	}

}

template <class T> void BST<T>::udelete (T &data){
	bool found = false;
	if (tRoot == nullptr){
		throw runtime_error("The tree is empty");
	} else {
		Tnode<T>* current = tRoot;
		Tnode<T>* trailing = tRoot;
		while(current != nullptr && found == false){
			if (current -> nodeValue == data){
				found = true;
			} else {
				trailing = current;
				if (current -> nodeValue > data){
					current = current -> Lchild;
				} else {
					current = current -> Rchild;
				}
			}
		}

		if (current == nullptr){
			throw runtime_error("This value is not in the tree");
		} else {
			if (current -> nodeValue == tRoot -> nodeValue){
				udeleteInternal(tRoot);
			} else if (trailing -> nodeValue > data){
				udeleteInternal(trailing -> Lchild);
			} else {
				udeleteInternal(trailing -> Rchild);
			}
		}
	}
}

template <class T> void BST<T>::udeleteInternal (Tnode<T> * &data){
	Tnode<T>* temp = data;
	if (data -> Lchild == nullptr && data -> Rchild == nullptr){
		data = nullptr;
		delete temp;
	} else if (data -> Rchild == nullptr){
		data = data -> Lchild;
		delete temp;
	} else if (data -> Lchild == nullptr){
		data = data -> Rchild;
		delete temp;
	} else {
		temp = temp -> Lchild;
		Tnode<T>* trailing = data;
		if (temp -> Rchild != nullptr){
			while(temp -> Rchild != nullptr){
				trailing = temp;
				temp = temp -> Rchild;
			}
			data -> nodeValue = temp -> nodeValue;
			trailing -> Rchild = temp -> Lchild;
			delete temp;
		} else {
            data -> nodeValue = temp -> nodeValue;
            data -> Lchild = temp -> Lchild;
            delete temp;
		}

	}
}

template <class T> void BST<T>::inOrderTraversal(){
	inOrderTraversal(tRoot);
}

template <class T> void BST<T>::inOrderTraversal (Tnode<T> *data){
	if (data != nullptr){
		inOrderTraversal(data -> Lchild);
		cout << data -> nodeValue << endl;
		inOrderTraversal(data -> Rchild);
	}
}

template <class T> void BST<T>::preOrderTraversal(){
	preOrderTraversal(tRoot);
}

template <class T> void BST<T>::preOrderTraversal(Tnode<T> *data){
	if (data != nullptr){
		cout << data -> nodeValue << endl;
		preOrderTraversal(data -> Lchild);
		preOrderTraversal(data -> Rchild);
	}
}

template <class T> void BST<T>::postOrderTraversal(){
	postOrderTraversal(tRoot);
}

template <class T> void BST<T>::postOrderTraversal(Tnode<T> *data){
	if (data != nullptr){
		postOrderTraversal(data -> Lchild);
		postOrderTraversal(data -> Rchild);
		cout << data -> nodeValue << endl;
	}
}

template <class T> int BST<T>::height(){
	return heightInternal(tRoot);
}

template <class T> int BST<T>::heightInternal(Tnode<T> *p){
	if (p != nullptr){
		int lh = heightInternal(p -> Lchild);
		int rh = heightInternal(p -> Rchild);
		if (lh > rh){
			return lh + 1;
		} else {
			return rh + 1;
		}
	} else {
		return -1;
	}
}

template <class T> void BST<T>::printRohit(){
/* Created by Rohit Jaganathan for Professor Hrycewicz's CS 20 class on 11/27/2020
 *
 * Prints the tree vertically
 * First, a string is generated for each line. Because this results in very wide trees, the second part of the function makes it so that the space between each node is equal to GAP
 *
 * Preconditions:
 * none
 *
 * Postconditions:
 * tree has been printed
 *
 * Usage:
 *      treename.printRohit();		 treename is the name of a BinarySearchTree object
 *
 *      The BinarySearchTree class must have a tree root (pointer) named treeRoot
 *      A class or struct named Treenode must be used with the following members:
 *           Lchild - pointer to the left subtree
 *           Rchild - pointer to the right subtree
 *           nodeValue - an integer containing the node's key value
 *      When a subtree is null, the child pointer must contain nullptr
 */
	const int WIDTH = 3;		//must be greater than the max characters printed per node; for example, if printing 2 digit numbers, WIDTH should be >= 3
	const int GAP = 5;			//gap between each node
	int height = this->height();
	string lines[height+1];

	//generates a string for each level of the tree
	for(int i=1; i<=height+1; i++){
		string line;
		printRohitLevel(tRoot, i, i, height, line, WIDTH);
		lines[i-1] = line;
	}

	//removes the unnecessary spaces from the lines
	int space = 0;
	bool allSpace;
	for(unsigned int i=0; i<lines[0].length(); i++){
		allSpace = true;
		for(string line: lines){
			if(line.at(i) != ' '){
				allSpace = false;
			}
		}
		if(allSpace){		//every line has a space at this index
			space += 1;
		}
		else{
			if(space > GAP){		//the space is too long and must be reduced to GAP
				for(string& line: lines){
					line.erase(i-space, space-GAP);
				}
				i-= (space-GAP);
			}
			else if(space < GAP && space>0){		//the space is too small and must be expanded to GAP
				string spaces;
				for(int j=0; j< (GAP-space); j++){
					spaces += " ";
				}
				for(string& line: lines){
					line.insert(i-space, spaces);
				}
				i += (GAP-space);
			}
			space = 0;
		}
	}

	//prints out each line
	for(string line: lines){
		cout << line << endl << endl << endl;
	}

}

template <class T>
void BST<T>::printRohitLevel(Tnode<T>* node, int x, int level, int height, string& print, const int WIDTH){
	if(node == nullptr){		//this node is empty so generates enough spaces based on which level the node is located on in relation to the level that must be printed and the height of the tree
		for(int i=0; i< WIDTH*pow(2,height+1-level+x); i++)
			print+= " ";
	}
	else if(x == 1){		//the node is on the right level
		for(int i=0; i< WIDTH*(pow(2,height+1-level)); i++)		//space to print before node
			print+= " ";
		int val = node->nodeValue;
		string value = to_string(val);
		print+= value;
		for(unsigned int i=0; i< (WIDTH-value.length()); i++)		//if the width of the node is less than WIDTH, prints spaces
			print+= " ";
		for(int i=0; i< WIDTH*(pow(2,height+1-level)-1); i++)		//space to print after node
			print+= " ";
	}
	else{
		printRohitLevel(node->Lchild, x-1, level, height, print, WIDTH);
		printRohitLevel(node->Rchild, x-1, level, height, print, WIDTH);
	}
}



