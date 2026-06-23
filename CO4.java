import java.util.*;

public class BidirectionalDijkstra {
    static class Edge{int to,w;Edge(int t,int w){to=t;this.w=w;}}
    static void addEdge(List<List<Edge>> a,List<List<Edge>> r,int u,int v,int w){a.get(u).add(new Edge(v,w));r.get(v).add(new Edge(u,w));}
    static int bidirDijkstra(int n,List<List<Edge>> a,List<List<Edge>> r,int s,int t){
        int[] fd=new int[n], bd=new int[n];
        Arrays.fill(fd,Integer.MAX_VALUE);Arrays.fill(bd,Integer.MAX_VALUE);
        fd[s]=0;bd[t]=0;
        PriorityQueue<int[]> f=new PriorityQueue<>((x,y)->x[0]-y[0]);
        PriorityQueue<int[]> b=new PriorityQueue<>((x,y)->x[0]-y[0]);
        f.offer(new int[]{0,s});b.offer(new int[]{0,t});
        Set<Integer> sf=new HashSet<>(), sb=new HashSet<>();
        int mu=Integer.MAX_VALUE;
        while(!f.isEmpty()&&!b.isEmpty()){
            if(f.peek()[0]+b.peek()[0]>=mu) return mu;
            boolean fwd=f.peek()[0]<=b.peek()[0];
            PriorityQueue<int[]> pq=fwd?f:b;
            int[] d=fwd?fd:bd, od=fwd?bd:fd;
            Set<Integer> sset=fwd?sf:sb, oset=fwd?sb:sf;
            List<List<Edge>> g=fwd?a:r;
            int u=pq.poll()[1];
            if(sset.contains(u)) continue;
            sset.add(u);
            for(Edge e:g.get(u)){
                int nd=d[u]+e.w;
                if(nd<d[e.to]){
                    d[e.to]=nd;
                    pq.offer(new int[]{nd,e.to});
                    if(oset.contains(e.to)) mu=Math.min(mu,d[e.to]+od[e.to]);
                }
            }
        }
        return mu==Integer.MAX_VALUE?-1:mu;
    }
    public static void main(String[] z){
        int n=8;
        List<List<Edge>> a=new ArrayList<>(), r=new ArrayList<>();
        for(int i=0;i<n;i++){a.add(new ArrayList<>());r.add(new ArrayList<>());}
        addEdge(a,r,0,1,10);addEdge(a,r,0,2,16);addEdge(a,r,0,4,14);
        addEdge(a,r,1,3,12);addEdge(a,r,1,5,15);addEdge(a,r,2,3,9);
        addEdge(a,r,3,7,11);addEdge(a,r,5,7,13);addEdge(a,r,5,6,8);
        addEdge(a,r,4,6,18);addEdge(a,r,6,7,20);
        System.out.println("Shortest Distance from SEC to GIT = "+bidirDijkstra(n,a,r,0,7)+" minutes");
    }
}
