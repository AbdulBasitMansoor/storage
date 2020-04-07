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

 class node{
        int state;
        int action;
        int cost;
        node parent;

    public node(int state, int action, int cost, node parent) {
        this.state = state;
        this.action = action;
        this.cost = cost;
        this.parent = parent;
    }
        
        
    }


public class ArtificialIntellegece {

    /**
     * @param args the command line arguments
     */
    
    public static String[] possibleStates;
    public static String[] possibleActions;
    public static String[] problem;
    public int noOfActions;
    public int noOfTestCases;
    public int noOfStates;
    public static int[][] matrix;
    public static String[][] splittedCases;
    public Queue<node> frontier = new LinkedList();
    public HashSet<Integer> exploredSet = new HashSet<Integer>();
    
   
    public void readHeader(){
        
        Scanner Myinput = new Scanner(System.in); 
        System.out.println("Input the number of states : ");
        noOfStates = Myinput.nextInt(); 
        System.out.println("Input the number of actions: ");
        noOfActions = Myinput.nextInt();
        System.out.println("Input the number of test cases : ");
        noOfTestCases = Myinput.nextInt();
        
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
     
     
     public void arraySplit(){
         splittedCases = new String[noOfTestCases][2];
         for(int i=0;i<noOfTestCases;i++)
         {
             String[] s= possibleStates[i].split("\t");
             splittedCases[i][0]=s[0];
             splittedCases[i][1]=s[1];
         }
      
     }
     
     public int determineState(String s){
     
         for(int i=0;i<noOfStates;i++)
         {
             if(s==possibleStates[i])
                 return i;
         }
         return -1;
     }
             
     public boolean isGoal(int state,int goal){
         if(state==goal)
             return true;
         return false;   
     }
     
     
     
     public void algorithm(String [] problem)   {
         
         String[] toPrint = new String[noOfStates];
         for(int i=0;i<noOfTestCases;i++)
         {
             node Node=new node(determineState(splittedCases[i][0]),-1,0,null);
             frontier.add(Node);
             int goal = determineState(splittedCases[i][1]);
             boolean noPlan = false;
             if(Node.state == -1 || goal == -1)
                 noPlan=true;
             boolean alreadyGoalState = false;
             if(Node.state==determineState(splittedCases[i][1]))
             {
                 alreadyGoalState=true;
                 toPrint[i]="Start and Goal states are same, So goal achieved.";
             }
             node goalFound = null;
             while(!frontier.isEmpty() && !noPlan && !alreadyGoalState)
             {
                 node newNode = frontier.remove();
                 for(int k=0;k<noOfStates;k++)
                 {
                     int transition = matrix[newNode.state][k];
                     node temp = new node(transition,k,newNode.cost+1,newNode);
                     if(transition!=newNode.state ){
                         if(isGoal(transition,goal)){
                             goalFound=temp;
                             break;
                         }
                         else
                         {
                             if(!exploredSet.contains(temp) && !frontier.contains(temp))
                                 frontier.add(temp);
                         }
                     }
                         
                     
                 }
             }
             if(!alreadyGoalState)
             {
                 if(goalFound!=null)
                 {
                     while(goalFound!=null)
                     {
                         if(goalFound.action != -1)
                         {
                             toPrint[i]=possibleActions[goalFound.action] + "->" + toPrint[i];
                             
                         }
                         else
                             goalFound = goalFound.parent;
                     }
                 }
                 else
                     toPrint[i]=null;
             }
             for(int m =0;m<noOfTestCases;m++)
                 System.out.println(toPrint[m]);
         }       
            
     }
     
     public static void main(String[] args) {
        
       ArtificialIntellegece graphSearch = new  ArtificialIntellegece();
       graphSearch.readHeader();
       graphSearch.possibleStates(graphSearch.noOfStates);
       graphSearch.possibleActions(graphSearch.noOfActions);
       graphSearch.transitionMatrix(graphSearch.noOfStates,graphSearch.noOfActions);
       graphSearch.readTestCases(graphSearch.noOfTestCases);
       graphSearch.arraySplit();
       graphSearch.algorithm(graphSearch.problem);   
    }
}

