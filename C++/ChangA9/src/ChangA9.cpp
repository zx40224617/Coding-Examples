//============================================================================
// Name        : ChangA9.cpp
// Author      : Yun Chung Chang
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <fstream>
using namespace std;
#include <list>
#include <vector>
#include <queue>



struct Vertex{
	int vertexValue;
	list <int> neighbor;
};


void dft(vector <Vertex>);
void dftInternal (vector <Vertex>, Vertex, bool[]);
void bft (vector <Vertex> name);



int main() {


	vector <Vertex> graph;
	ifstream inFile;
	int input;
	int AdjMatrix[11][11] = {0};

	inFile.open("A9data688g3 F2022.txt");
	if (inFile.is_open()){

		while(!inFile.eof()){
			Vertex v;
			inFile >> input;
			v.vertexValue = input;
			inFile >> input;
			while (input != -1){
				v.neighbor.push_back(input);
				AdjMatrix[v.vertexValue][input] = 1;
				inFile >> input;
			}

			if (v.vertexValue != -1){
				graph.push_back(v);
			}

		}
		inFile.close();
	} else {
		cout << "error" << endl;
	}

	cout << "Populated adjacency list: " << endl << endl;

	for (int i = 0; i < 11; i++ ){
		int vertexValue = graph.at(i).vertexValue;

		cout << "vertex number " << i << " value " << vertexValue << " neighbors -> ";
		for (list<int>::iterator it = graph.at(i).neighbor.begin(); it != graph.at(i).neighbor.end(); it++){
			cout << *it << " ";
		}
		cout << endl;
	}

	cout << endl << endl;

	cout << "Populated adjacency matrix:" << endl << endl;

	for (int i = 0; i < 11; i++){
		for (int j = 0; j < 11; j++){
			cout << AdjMatrix[i][j] << " ";
		}
		cout << endl;
	}

	cout << endl << endl;

	cout << "Depth-first traversal: " << endl;
	dft(graph);

	cout << endl << endl;


	cout << "Breadth-first traversal: " << endl;
	bft(graph);

}



void dft(vector <Vertex> graph){
	bool visited[11];
	for (int  i = 0; i < graph.size(); i++){
		visited[i] = false;
	}

	int j = 0;
	while(j < graph.size()){
		if (visited[j] == false){
			dftInternal(graph, graph.at(j), visited);
		}
		j++;
	}

}

void dftInternal (vector <Vertex> graph, Vertex v, bool visited[]){
	cout << v.vertexValue << " ";
	visited[v.vertexValue] = true;
	vector <int> neighbors;
	for (list<int>::iterator it = v.neighbor.begin(); it != v.neighbor.end(); it++){
		neighbors.push_back(*it);
	}

	if (neighbors.size() != 0) {
		for (int i = 0;i < neighbors.size(); i++){
			if (visited[neighbors.at(i)] == false){
				dftInternal(graph, graph.at(neighbors.at(i)), visited);
			}
		}
	}
}


void bft (vector <Vertex> graph){
	bool visited[11];
	queue<Vertex> verticies;
	for (int  i = 0; i < 11; i++){
		visited[i] = false;
	}
	verticies.push(graph.at(0));
	visited[0] = true;
	int j = 1;
	while(j < graph.size()){
		if (visited[j] == false){
			verticies.push(graph.at(j));
			visited[j] = true;
			while(verticies.size() != 0){
				Vertex v = verticies.front();
				verticies.pop();
				cout << v.vertexValue << " ";
				vector <int> neighbors;

				for (list<int>::iterator it = v.neighbor.begin(); it != v.neighbor.end(); it++){
					neighbors.push_back(*it);
				}

				if (neighbors.size() != 0) {
					for (int i = 0;i < neighbors.size(); i++){
						if (visited[neighbors.at(i)] == false){
							verticies.push(graph.at(neighbors.at(i)));
							visited[neighbors.at(i)] = true;
						}
					}
				}

			}
		}
		j++;
	}


}




