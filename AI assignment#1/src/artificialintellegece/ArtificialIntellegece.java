/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificialintellegece;

/**
 *
 * @author Abdul Basit
 */
import java.util.*;


public class ArtificialIntellegece {

    /**
     * @param args the command line arguments
     */
    
    public static String[] possibleStates;
    public static String[] possibleActions;
    public static String[] problem;
    public static int[] header;
    public static int[][] matrix;
    public static String[] splittedCase;
    public static Queue<node> frontier = new LinkedList();
    public static HashSet<Integer> exploredSet = new HashSet<Integer>();
    
    public static class node{
        int state;
        int action;
        int cost;
        node parent;
        
        public boolean goalTest(int k){
         
         if(this.state==k)
         {
             return true;
         }
         return false;
     }
    }
    
    public static void main(String[] args) {
        
        readHeader();
        
        possibleStates(header[0]);
        possibleActions(header[1]);
        transitionMatrix(header[0],header[1]);
        readTestCases(header[2]);
       
        display(splittedCase);
        
    }
    
    public static void readHeader(){
        
        Scanner Myinput = new Scanner(System.in); 
        System.out.println("Input the number of states : ");
        header[0] = Myinput.nextInt(); 
        System.out.println("Input the number of actions: ");
        header[1] = Myinput.nextInt();
        System.out.println("Input the number of test cases : ");
        header[2] = Myinput.nextInt();
        
}
    
    public static void possibleStates(int M)   {
        
        Scanner Myinput = new Scanner(System.in);       
        for(int i=0;i<M;i++)
        {
            possibleStates[i] = Myinput.nextLine();
        }   
    }
    
    public static void possibleActions(int N)   {
        
        Scanner Myinput = new Scanner(System.in);
        for(int i=0;i<N;i++)
        {
            possibleActions[i] = Myinput.nextLine();
        }   
    }
    
     public static void transitionMatrix(int M,int N)   {
        
         Scanner myInput = new Scanner(System.in);
        for(int i=0;i<M;i++)
        {
           for(int j=0;i<N;i++)
           {
               matrix[i][j]= myInput.nextInt();
           }   
        }   
        
    }
    
     public static void readTestCases(int T)   {
        
         Scanner Myinput = new Scanner(System.in);
         for(int i=0;i < T;i++)
            {
                problem[i] = Myinput.nextLine();
            }  
     }   
     
     
     public static void arraySplit(){
        // splittedCases = testCases.split("/t ");
     }
     
     
     
     
     public static node algorithm(String [] problem)   {
         
         node Node=new node();
         for(int i=0;i<header[0];i++)
         {
             if(problem[0]==possibleStates[i])
             {
                 Node.state=i;
                 Node.cost=0;
                 Node.action= -1;
                 Node.parent=null;
             }
         }
         int goalState=0;
         for(int i=0;i<header[0];i++)
         {
             if(problem[1] == possibleStates[i])
             {
                 goalState=i;
             }
         }
         if(Node.goalTest(goalState))
         {
             return Node;
         }
         else
         {
             frontier.add(Node);
         }
         node child = new node();
      
         do{
             exploredSet.add(frontier.peek().action);
             frontier.poll();
             for(int i=0;i<header[1];i++)
             {
                 child.state= matrix[Node.state][i];
                 child.cost=Node.cost+1;
                 child.action=i;
                 child.parent= Node;
                 if(child.goalTest(i))
                 {
                     return child;
                 }
                 else
                 {
                     frontier.add(child);
                 }
             }
             Node = frontier.peek();
             
           }while(frontier != null);
         return Node;
     }
     public static void display(String [] problem)
     {
         node result = algorithm(problem);
         if(result.action==-1)
         {
             System.out.println("Agent is on its position already.");
         }
         else
         {
             if(result.action==0)
                 System.out.println("clean->");
             if(result.action==1)
                 System.out.println("MoveToRoom1->");
             if(result.action==2)
                 System.out.println("MoveToRoom2->");
         }
     }
}
