/*
 * BST.h
 *
 *  Created on: Nov 5, 2022
 *      Author: phoebe.wang
 */

#ifndef BST_H_
#define BST_H_
#include <iostream>
using namespace std;

template <class T> class BST {
	private:
		template <class> struct Tnode{
			T nodeValue;
			Tnode <T> * Lchild;
			Tnode <T> * Rchild;
		};

		Tnode <T> * tRoot;
		int heightInternal(Tnode<T> *);
		void udeleteInternal (Tnode<T> * &);
		void inOrderTraversal (Tnode<T> *);
		void preOrderTraversal (Tnode<T> *);
		void postOrderTraversal (Tnode<T> *);
		void printRohitLevel (Tnode<T>*, int, int, int, string&, const int);

	public:
		BST();
		int height();
		bool search (T &);
		void insert (T &);
		void udelete (T &);
		void printRohit ();
		void inOrderTraversal ();
		void preOrderTraversal ();
		void postOrderTraversal ();
};


#endif /* BST_H_ */
