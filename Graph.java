

import graphproj.GraphADT;
import graphproj.LinkedQueueClass;
import graphproj.UnorderedLinkedList;

import java.io.*;
import java.util.*;

public class Graph implements GraphADT
{
    protected int maxSize;          //maximum number of vertices
    protected int gSize;            //current number of vertices
    protected UnorderedLinkedList[] graph; //array of references
                                     //to create adjacency lists

       //Default constructor
       //Postcondition: The graph size is set to 0, that is,
       //               gSize = 0; maxSize = 100
    public Graph()
    {
        maxSize = 100;
        gSize = 0;
        graph = new UnorderedLinkedList[maxSize];

        for (int i = 0; i < maxSize; i++)
            graph[i] = new UnorderedLinkedList<Integer>();
    }

       //Constructor
       //Postcondition: The graph size is set to 0, that is,
       //               gSize = 0; maxSize = size
    public Graph(int size)
    {
        maxSize = size;
        gSize = 0;
        graph  = new UnorderedLinkedList[maxSize];

        for (int i = 0; i < maxSize; i++)
            graph[i] = new UnorderedLinkedList<Integer>();
    }

       //Method to determine if the graph is empty.
       //Postcondition: Returns true if the graph is empty;
       //               otherwise, returns false.
    public boolean isEmpty()
    {
        return (gSize == 0);
    }

       //Method to create the graph.
       //Postcondition: The graph is created using the
       //               adjacency list representation.
    public void createGraph()
    {
        Scanner console = new Scanner(System.in);

        String fileName;

        if (gSize != 0)
            clearGraph();

        Scanner infile = null;

        try
        {
            System.out.print("Enter input file name: ");
            fileName = console.nextLine();
            System.out.println();

            infile = new Scanner(new FileReader(fileName));
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.toString());
            System.exit(0);
        }

        gSize = infile.nextInt(); //get the number of vertices

        for (int index = 0; index < gSize; index++)
        {
            int vertex = infile.nextInt();
            int adjacentVertex = infile.nextInt();

            while (adjacentVertex != -999)
            {
                graph[vertex].insertLast(adjacentVertex);
                adjacentVertex = infile.nextInt();
            } //end while
        } // end for
    }//end createGraph


       //Method to clear the graph.
       //Postcondition: Each entry of the array graph is set
       //               to null and gSize = 0;
    public void clearGraph()
    {
        for (int index = 0; index < gSize; index++)
            graph[index] = null;

        gSize = 0;
    }

       //Method to print the graph.
       //Postcondition: For each vertex, the vertex and the
       //               vertices adjacent to the vertex are
       //               output.
    public void printGraph()
    {
        for (int index = 0; index < gSize; index++)
        {
            System.out.print(index + " ");
            graph[index].print();
            System.out.println();
        }

        System.out.println();
    }

       //Method to perform a depth first traversal at a
       //given vertex.
       //This method is used by the method, depthFirstTraversal
       //and the method dftAtVertex.
       //Postcondition: A depth first search starting at the
       //                vertex v is performed.
    private void dft(int v, boolean[] visited)
    {
        visited[v] = true;

        System.out.print(" " + v + " ");  //visit the vertex

        UnorderedLinkedList<Integer>.LinkedListIterator<Integer> graphIt
                                = graph[v].iterator();

        while (graphIt.hasNext())
        {
            int w = graphIt.next();

            if (!visited[w])
               dft(w, visited);
        } //end while
    } //end dft


       //Method to perform a depth first traversal of the
       //entire graph.
       //Postcondition: The vertices of the graph in the depth
       //               first traversal order are output.
    public void depthFirstTraversal()
    {
        boolean[] visited;  //array to keep track of the
                            //visited vertices
        visited = new boolean[gSize];

        for (int index = 0; index < gSize; index++)
            visited[index] = false;

        for (int index = 0; index < gSize; index++) //for each
                                                    //vertex that
            if (!visited[index])          //has not been visited
                dft(index, visited);      //do a depth first
                                          //traversal
    } //end depthFirstTraversal

       //Method to perform a depth first traversal at a
       //given vertex.
       //Postcondition: The vertices of the graph in the depth
       //               first traversal order are output.
    public void dftAtVertex(int vertex)
    {
        boolean[] visited;
        visited = new boolean[gSize];

        for (int index = 0; index < gSize; index++)
            visited[index] = false;

        dft(vertex,visited);
    } //end dftAtVertex

       //Method to perform a breadth first traversal of the
       //graph.
       //Postcondition: The vertices of the graph in the breadth
       //               first traversal order are output.
    public void breadthFirstTraversal()
    {
        LinkedQueueClass<Integer> queue = new LinkedQueueClass<Integer>();

        boolean[] visited;
        visited = new boolean[gSize];

        for (int ind = 0; ind < gSize; ind++)
            visited[ind] = false;   //initialize the array
                                    //visited to false

        for (int index = 0; index < gSize; index++)
            if (!visited[index])
            {
               queue.addQueue(index);
               visited[index] = true;
               System.out.print(" " + index + " ");

               while (!queue.isEmptyQueue())
               {
                   int u = queue.front();

                   queue.deleteQueue();

                   UnorderedLinkedList<Integer>.
                        LinkedListIterator<Integer> graphIt
                                      = graph[u].iterator();

                    while (graphIt.hasNext())
                    {
                        int w1 = graphIt.next();

                        if (!visited[w1])
                        {
                            queue.addQueue(w1);
                            visited[w1] = true;
                            System.out.print(" " + w1 + " ");
                        }
                    }
                } //end while
            } //end if
    } //end breadthFirstTraversal
    
public int getSize() {
  	return gSize;
		
	}
}
