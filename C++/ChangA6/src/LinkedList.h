/*
 * LinkedList.h
 *
 *  Created on: Oct 16, 2022
 *      Author: phoebe.wang
 */

#ifndef LINKEDLIST_HPP_
#define LINKEDLIST_HPP_

#include <iostream>

using namespace std;


template <class T> struct LLnode {
	LLnode * fwdPtr;
	T theData;
};

template <class T> class LL {
	private:
		LLnode<T>* headPtr;
	public:
		LL();
		void push_front (T);
		void  push_back_helper(T, LLnode<T>*);
		void push_back (T);
		int list_length();
		int list_length_helper(LLnode<T>*);
		T retrieve_front ();
		T retrieve_back ();
		void display_list();
		void destroy_list_helper(LLnode<T>* , LLnode<T>*);
		void destroy_list ();
		T search_list ();
		bool delete_node_helper(int, LLnode<T>*, LLnode<T>*, LLnode<T>*);
		bool delete_node (int);
		void searchID(string);
};
template <class T> LL<T>:: LL(){
	headPtr = nullptr;
}

template <class T> void LL<T> :: push_front (T data){
	LLnode<T>* newData = new LLnode<T>();
	newData -> theData = data;
	LLnode<T>* temp = headPtr;
	headPtr = newData;
	newData -> fwdPtr = temp;

}
template <class T> void  LL<T> :: push_back_helper(T data, LLnode<T>* ptr){
	LLnode<T>* temp = ptr;
	if (temp -> fwdPtr == nullptr){
		LLnode<T>* newData = new LLnode<T>();
		newData -> theData = data;
		newData -> fwdPtr = nullptr;
		temp -> fwdPtr = newData;
	} else {
		push_back_helper(data, ptr -> fwdPtr);
	}
}

template <class T> void LL<T> :: push_back (T data){
	push_back_helper(data, headPtr);
}

template <class T> int LL<T> :: list_length_helper(LLnode<T>* ptr){
	if (ptr == nullptr){
		return 0;
	} else {
		return 1 + list_length_helper(ptr -> fwdPtr);
	}
}

template <class T> int LL<T> :: list_length () {
	return list_length_helper(headPtr);
}

template <class T> T LL<T> :: retrieve_front(){
	if (headPtr == nullptr){
		throw runtime_error("the linked list is empty\n");
	} else {
		return headPtr -> theData;
	}
}

template <class T> T LL<T> :: retrieve_back(){
	if (headPtr == nullptr){
			throw runtime_error("the linked list is empty\n");
	} else {
		LLnode<T>* temp = headPtr;
		while (temp -> fwdPtr != nullptr){
			temp = temp -> fwdPtr;
		}
		return temp -> theData;
	}
}

template <class T> void LL<T> :: display_list(){
	if (headPtr == nullptr){
		cout << "	there is no chain from this entry" << endl;
	} else {
		LLnode<T>* temp = headPtr;
		while(temp != nullptr){
			cout << "	chain - employee " <<  temp  -> theData.ID
					<< " name " << temp -> theData.name;

			temp = temp -> fwdPtr;
		}
		cout << endl;
	}
}

template <class T> void LL<T> :: destroy_list_helper(LLnode<T>* ptr, LLnode<T>* next){
	delete ptr;
	ptr = next;
	if (ptr == nullptr){
		headPtr = nullptr;
	} else {
		destroy_list_helper(ptr, ptr -> fwdPtr);
	}

}

template <class T> void LL<T> :: destroy_list(){
	if (list_length() == 0){
		return;
	} else {
		destroy_list_helper(headPtr, headPtr -> fwdPtr);
	}
}


/**
template <class T> T LL<T> :: search_list(string target){
	Student notFound;
	notFound.key = 0;
	notFound.data = "";
	LLnode<T>* temp = headPtr;
	while(temp != nullptr){
		if (temp -> theData.key == target){
			return temp -> theData;
		} else {
			temp = temp -> fwdPtr;
		}
	}
	return notFound;

}
*/


template <class T> bool LL<T> :: delete_node_helper(int target, LLnode<T>* curr, LLnode<T>* prev, LLnode<T>* next){
	if (headPtr -> theData.key == target){
		next = headPtr -> fwdPtr;
		delete curr;
		headPtr = next;
		return true;
	} else if (curr == nullptr){
		return false;
	} else if (curr -> theData.key == target){
		delete curr;
		prev -> fwdPtr = next;
		return true;
	} else {
		prev = curr;
		curr = next;
		if (next == nullptr){
			next = nullptr;
		} else {
			next = next -> fwdPtr;
		}
		return delete_node_helper(target, curr, prev, next);
	}

}

template <class T> bool LL<T> :: delete_node(int target){
	int i = list_length();
	if (i == 0){
		return false;
	} else {
		return delete_node_helper(target, headPtr, nullptr, headPtr -> fwdPtr);
	}
}

template <class T> void LL<T> :: searchID(string ID){
	LLnode<T>* temp = headPtr;
	while(temp != nullptr){
		if (temp -> theData.ID == ID){
			cout << "employee " << ID << " name " << temp -> theData.name;
			return;
		} else {
			temp = temp -> fwdPtr;
		}
	}
	cout << "employee not found" << endl;

}



#endif /* LINKEDLIST_HPP_ */
