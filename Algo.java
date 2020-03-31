import java.util.*;
public class Algo {
    public static void main(String[] args) {
        Random r=new Random();
        //int arr[]=new int[7];
        /*for(int i=0;i<7;i++){
            arr[i]=r.nextInt(11);
            //System.out.println(arr[i]);
        }*/
        int[] arr={8, 4, 2, 1};
        Sorting.MergeSort m=new Sorting.MergeSort();
        int[] aux=new int[arr.length];
        m.sort(arr,aux,0,arr.length-1);
       for(int i=0;i<arr.length;i++){
        System.out.println(arr[i]);
        }
       /* Sorting.QuickSort q=new Sorting.QuickSort();
        System.out.println(q.select(arr,0,arr.length-1,6));//QuickSelect
        q.sort(arr,0,arr.length-1);
        for(int i=0;i<7;i++){
            System.out.println(arr[i]);
        }*/
       int a[]={2,3,6,1,4,9,7,15};
       Sorting s=new Sorting(a);
       //s.heapSort(a); //using heapSort
        //Graph X=new Digraph(6);
        WeightedGraph X=new WeightedGraph(6);
        //X.printG(X);
        /*X.addEdge(0, 1, 4);
        X.addEdge(0, 2, 3);
        X.addEdge(1, 2, 1);
        X.addEdge(1, 3, 2);
        X.addEdge(2, 3, 4);
        X.addEdge(3, 4, 2);
        X.addEdge(4, 5, 6);
        KruskalMST MST=new KruskalMST(X);
        for(Edge e:MST.edges()){
            System.out.println(e.toString());
        }*/
        //DepthFS d=new DepthFS(X,2);//Check the answer. Done it's correct
        //BreadthFS b=new BreadthFS(X,2);




        }
    }

//for tree data structure
//An advanced implementation would also have a key for each Node
class Node{
    int data;
    Node left,right;
    public Node (int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Sorting{
    //This won't work
    /*void swap(int i,int j){
        int temp=i;
        i=j;
        j=temp;
    }*/
    int a[];
    public Sorting(int a[]){
        this.a=a;
    }

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
    }


    public void insertionSort(){
        int ptr;
        for(int i=0;i<a.length;i++){
            ptr=i;
            while(ptr>0){
                if(a[ptr]<a[ptr-1]){
                    int temp=a[ptr];
                    a[ptr]=a[ptr-1];
                    a[ptr-1]=temp;
                }
                ptr--;
            }
        }

    }
    //A better implementation of HeapSort is by heapifying the heap, which you can implement later on
   /* public void heapSort(int[] a){
        BHeap b=new BHeap(a);
        for(int i=0;i<a.length;i++) {
            int k = b.delMax();
            System.out.println(k);
        }
    }*/


    static class MergeSort{
        static int count=0;
        public void merge(int[] arr,int[] aux, int low ,int mid, int high ){
            //aux=arr; //this is not copying but mere referencing
            for(int i=low;i<=high;i++){
            aux[i]=arr[i];
        }

            int i=low;int j=mid+1;  //i and j are are on aux  // k is on a
            for(int k=low;k<=high;k++){
                if(i>mid){
                    arr[k]=aux[j];
                    j++;
                }
                else if(j>high){
                    arr[k]=aux[i];
                    i++;
                }
                else if(aux[i]>aux[j]){
                    arr[k]=aux[j];
                    j++;
                }
                else{
                    arr[k]=aux[i];
                    i++;
                    count = count + mid-i ;
                }
            }


    }
        public void sort(int[] a,int[] aux, int low ,int high){
            if(high<=low){
                return;
            }
            int mid=low+(high-low)/2;
            sort(a,aux,low,mid);
            sort(a,aux,mid+1,high);
            merge(a,aux,low,mid,high);
            System.out.println("count="+count);
        }
    }
    static class QuickSort{
        //Taking pivot as the last element //end index
        public int partition(int[] a,int start,int end){
            int pivot=a[end];
            int pIndex=start;
            for(int i=start;i<end;i++){
                if(a[i]<=pivot){
                    //swap a[i] and a[pIndex]
                    int temp=a[i];
                    a[i]=a[pIndex];
                    a[pIndex]=temp;
                    pIndex++;
                }

            }
            int temp=a[end];
            a[end]=a[pIndex];
            a[pIndex]=temp;
            return pIndex;
        }

        public void sort(int[] a,int start, int end){
            if(start<end){
                int pIndex=partition(a, start, end);
                sort(a,start,pIndex-1);
                sort(a,pIndex+1,end);
            }
        }
        public int select(int[] a,int start,int end,int k){
            int pIndex=partition(a,start,end);
            if(pIndex==k){
                return a[k];
            }
            else if(pIndex<k){
                return select(a,pIndex+1,end,k);
            }
            else{
                return select(a,start,pIndex-1,k);
            }
        }
    }
}

class Queue{
    int[] arr=new int[100];
    int front;
    int back;
    int size;

    public Queue(){
       front=-1;
       back=-1;
       size=0;
    }
    public boolean isEmpty(){
        if(front==-1 && back==-1){
            return true;
        }
        else{
            return false;
        }

    }

    public void enqueue(int e){
        if(front==-1 && back==-1){
            front=0;
            back=0;
        }
        else {
            back++;
        }
        arr[back] = e;
        size++;
    }
    public int dequeue(){
        if(isEmpty()){
            System.out.println("Array Empty");
            return Integer.MIN_VALUE;
        }
        if(front==back){   //In this way,
            int a=arr[front];
            front=-1; back=-1;
            size--;
            return a;
        }
        else{
            int d = arr[front];
            front++;
            size--;
            return d;
        }

    }
}

class BHeap{
    int[][] heap=new int[1000][4];// index starts from 1
    int N; //no. of elements(1,2,3,4....)
    public BHeap(int[][] a){
        //N=a.length;
        /*for(int i=1;i<=a.length;i++){
            for(int j=1;j<=3;j++){
                heap[i][j]=a[i-1][j-1];
            }
        }*/

        for(int i=1;i<=a.length;i++){
            this.insert(a[i-1][1]);
        }
        /*for(int i=1;i<=a.length;i++){
            for(int j=1;j<=3;j++){
                System.out.print(heap[i][j]+ " ");
            }
        }*/

    }
    private void swim(int k){
        while(k>1 && (heap[k][2]>=heap[k/2][2])){
            //swap heap[k] and heap[k/2]
            int[] temp=heap[k];
            heap[k]=heap[k/2];
            heap[k/2]=temp;
            k=k/2;
        }
    }
    public void insert(int x){
        N++;
        heap[N][2]=x;
        heap[N][1]=x;
        heap[N][3]=1;
        swim(N);
    }
    private void sink(int k){
        while(2*k<=N){
            int j=2*k; //j is the left child of k
            if(j<N &&(heap[j][2]<heap[j+1][2])){
                j++;
            }
            if(!(heap[j][2]>heap[k][2])){
                break;
            }
            int[] temp=heap[k];
            heap[k]=heap[j];
            heap[j]=temp;
            k=j;
        }
    }
    public int retMax(){
        //int max=heap[1][1];
        heap[1][3]++;
        //System.out.println(heap[1][1]);
        //System.out.println(heap[1][3]);
        float x=(float)heap[1][1]/(float)heap[1][3];
        heap[1][2]=(int)Math.ceil(x);
        //System.out.println(heap[1][2]);
        //System.out.println(heap[1][2]);
        //System.out.println(heap[1][1]);
        //N--;
        //heap[N+1][1]=0; //Can't make an int array null;
        sink(1);
        int max=heap[1][2];
        return max;
    }
}

//This code hasn't been verified
class Tree{

    //pre-Order Display//root,left,right
    void preDisplay(Node root) {
        if (root != null) {
            System.out.print(" " + root.data);
            preDisplay(root.left);
            preDisplay(root.right);
        }
    }
    //Do post and in similarly
    int count(Node root){
        if(root==null){
            return 0;
        }
        return 1+count(root.left)+count(root.right);
    }
    //BST Search to return Node Reference
    public Node searchBST(Node root,int val){
        Node curr=root;
        if(curr!=null){
            if(curr.data==val){
                return curr;
            }
            else if(curr.data>val){
                return searchBST(curr.left,val);
            }
            else{
                return searchBST(curr.right,val);
            }
        }
        return null;
    }
    //smallest value on a BST
    public int smallest(Node root){
        Node curr=root;
        if(curr.left==null){
            return curr.data;
        }
        else{
            return smallest(curr.left);
        }
    }
    public Node insert (Node root, int value) {
        if (root == null) {
            root = new Node(value);
        }
        else {
            if (value < root.data) {
                root.left = insert(root.left, value);
            } else {
                root.right = insert(root.right, value);
            }
        }
        return root;
    }


    public static boolean isBST(Node root,int min, int max){

        if(root==null){
            return true;
        }
        return(root.data>min && root.data<max && isBST(root.left,min,root.data) && isBST(root.right,root.data,max));

    }//or do an in order traversal and keep checking
    public Node getParent(Node root,int value){
        Node parent=root;
        if(parent==null){
            return parent;
        }
        else if((parent.left!=null && parent.left.data==value) || (parent.left!=null && parent.left.data==value) ) {
            return parent;
        }
        else{
            Node found=getParent(root.right,value);
            if(found==null){
                found=getParent(root.left,value);
            }
            return found;
        }
    }

    public static Node deleteNode(Node root,int value){//Incomplete
        if(root==null){
            return root;
        }
        if(root.data>value){
            root.left=deleteNode(root.left,value);

        }
        else if(root.data<value){
            root.right=deleteNode(root.right,value);
        }
        else if(root.data==value){
        }
        else{

        }

        return root;
        }

}
abstract class Graph{
    abstract void addEdge(int v,int w);
    abstract int getV();
    abstract Iterable<Integer> adj(int v);
    //abstract void printG(Graph G);
}
//Adjacency list implementation of UnDirected Graph
class UGraph extends Graph{
    private final int V; //Starts from 0 to V-1
    private List[] adj;
    public UGraph(int V){
        this.V=V;
        adj=new List[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<Integer>();
        }
    }

    public int getV() {
        return V;
    }

    public void addEdge(int v, int w){ //new edge joining v-w
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v){ //Iterator for vertices adjacent to v
        return adj[v];    //Iterates over the linked list present at the node v
    }

    public void printG(UGraph G) {
        for (int i = 0; i < G.V; i++) {
            System.out.println("Adjacent vertices of node "+i+" is "+G.adj(i));
        }
    }
}

class Digraph extends Graph{
    private final int V; //Starts from 0 to V-1
    private List[] adj;
    public Digraph(int V){
        this.V=V;
        adj=new List[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<Integer>();
        }
    }

    public int getV() {
        return V;
    }

    public void addEdge(int v, int w){ //new edge joining v-w
        adj[v].add(w);
        //adj[w].add(v); //For undirected graphs
    }
    public Iterable<Integer> adj(int v) { //Iterator for vertices adjacent to v
        return adj[v];
    }//Iterates over the linked list present at the node v

    public void printG(Digraph G) {
        for (int i = 0; i < G.V; i++) {
            System.out.println("Adjacent vertices of node "+i+" is "+G.adj(i));
        }
    }
}

class DepthFS{
    boolean[] visited;
    int[] edgeTo;
    int s;
    public DepthFS(Graph G,int s){   //s -> source node
        visited=new boolean[G.getV()];
        edgeTo=new int[G.getV()];
        this.s=s;
        dfs(G,s);
    }
    public void dfs(Graph G,int v){
        visited[v]=true;
        System.out.print(v+" -> ");
        for(int w:G.adj(v)){
            if(!visited[w]){
                dfs(G,w);
                edgeTo[w]=v;
            }
        }

    }
}

class BreadthFS{
    boolean[] visited;
    int[] edgeTo;
    int s;
    public BreadthFS(Graph G,int s){   //s -> source node
        visited=new boolean[G.getV()];
        edgeTo=new int[G.getV()];
        this.s=s;
        bfs(G,s);
    }
    public void bfs(Graph G,int s){
        Queue q=new Queue();
        q.enqueue(s);
        System.out.print(s+" -> ");
        visited[s]=true;
        while(!q.isEmpty()){
            int v=q.dequeue();
            for(int w:G.adj(v)){
                if(!visited[w]){
                    System.out.print(w+" -> ");
                    q.enqueue(w);
                    visited[w]=true;
                    edgeTo[w]=v;
                }
            }

        }
    }
}

class QuickFindUF{
    int[] id;
    public QuickFindUF(int n){
        id=new int[n];
        for(int i=0;i<n;i++){
            id[i]=i;
        }
    }
    public boolean connected(int p, int q){
        return id[p]==id[q];
    }
    public void union(int p,int q){
        int pid=id[p];
        int qid=id[q];
        for(int i=0;i<id.length;i++){
            if(id[i]==qid){
                id[i]=pid;
            }
        }
    }
}
class Edge implements Comparable<Edge>{
    int x;
    int y;
    int weight;
    public Edge(int x,int y,int w){
        this.x=x;
        this.y=y;
        weight=w;
    }
    public int either(){  //return any node
        return this.x;
    }
    public int other(int v){   //return the node other than v
        if(this.x==v){
            return this.y;
        }
        else {
            return x;
        }
    }
    @Override
    public int compareTo(Edge b){
        if (this.weight < b.weight) return -1;
        else if (this.weight > b.weight) return +1;
        else return 0;
    }
    @Override
    public String toString(){
        System.out.println("Weight: "+this.weight);
        return(this.x+" - "+this.y);
    }
}
class WeightedGraph{  //Using Edge // Undirected Graph
    int V;
    List[] adj;   //adjacency list implementation
    private ArrayList<Edge> edges=new ArrayList<Edge>();
    public WeightedGraph(int V){
        this.V=V;
        adj=new List[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<Edge>();
        }
    }
    public void addEdge(int x, int y,int weight){
        Edge e=new Edge(x,y,weight);
        int v=e.either();
        int w=e.other(v);
        adj[v].add(w);
        adj[w].add(v);
        edges.add(e);
    }
    public Iterable<Edge> adj(int v){  //For using enhanced for-loop on linked list
        return adj[v];
    }
    public Iterable<Edge> edges(){  //For using enhanced for-loop on linked list
        return edges;
    }
}
class KruskalMST{ //Need to understand this properly
    private List<Edge> mst=new ArrayList<Edge>();
    public Iterable<Edge> edges(){
        return mst;
    }
    public KruskalMST(WeightedGraph G){ //Perfect
        PriorityQueue<Edge> pq=new PriorityQueue<Edge>(10);
        for(Edge e:G.edges()) {
            pq.add(e);
        }
            QuickFindUF uf=new QuickFindUF(G.V);
            while(!pq.isEmpty() && mst.size()<G.V-1){
                Edge x=pq.poll();
                System.out.println(x.weight);
                int v=x.either(); int w=x.other(v);
                if(!uf.connected(v,w)) {
                    uf.union(v, w);
                    mst.add(x);
                }
            }

    }

}
class DiEdge {
    int x;
    int y;
    int weight;
    public DiEdge(int x,int y,int w) {
        this.x = x;
        this.y = y;
        weight = w;
    }
    public int from(){
        return x;
    }
    public int to(){
        return y;
    }
}
class WeightedDigraph{  //Using Edge // Undirected Graph
    int V;
    List[] adj;   //adjacency list implementation
    private ArrayList<DiEdge> edges=new ArrayList<DiEdge>();
    public WeightedDigraph(int V){
        this.V=V;
        adj=new List[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<Edge>();
        }
    }
    public void addEdge(int x, int y,int weight){
        DiEdge e=new DiEdge(x,y,weight);
        int v=e.from();
        int w=e.to();
        adj[v].add(w);
        edges.add(e);
    }
    public Iterable<DiEdge> adj(int v){  //For using enhanced for-loop on linked list
        return adj[v];
    }
    public Iterable<DiEdge> edges(){  //For using enhanced for-loop on linked list
        return edges;
    }

}

class DijkstraSP{
    DiEdge[] edgeTo;
    int[] distTo;
    PriorityQueue pq;
    public DijkstraSP(WeightedDigraph G,int s){
        edgeTo=new DiEdge[G.V];
        distTo=new int[G.V];
        pq=new PriorityQueue(G.V); //Use own implementation of priority queue
        for(int i=0;i<G.V;i++){
            distTo[i]=Integer.MAX_VALUE;//Not Working Well rn
        }
        distTo[s]=0;
        pq.add(s);
        while(!pq.isEmpty()){
            int v=(int)pq.poll();
            for(DiEdge e: G.adj(v)){
                relaxEdge(e);
            }
        }
    }
    public void relaxEdge(DiEdge e){
        int v=e.from(); int w=e.to();
        if(distTo[w]>distTo[v]+e.weight){
            distTo[w]=distTo[v]+e.weight;
            edgeTo[w]=e;
            if(pq.contains(w)){ //Update priority queue
                pq.remove(w);
            }
            pq.add(w);
        }
    }

}










