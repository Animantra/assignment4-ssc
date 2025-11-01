<h1>Assignment 4

<h3>Data Summary

- 3 type of datasets: small(6-10 nodes), meduim(10-20 nodes), large(20-50 nodes) 

- Directed graphs

- edge weighted model


<h3>Results

![dataset](/plot.png)

<h3>Analysis

- SCC(Kosaraju): Time complexity O(V + E), same time complexity with Tarjan's algorithm. Kosaraju uses 2 traversal DFS. 
Bottleneck: Large SCCs increase DFS calls

- Topo Sorting(Kahn): Uses in-degree counting and queue. Fails if the graph contains cycles

- DAG Paths: O(V + E), using topo order, relies on DAG.

<h3>Conclusion
Kosaraju: uses for finding SCC in directed graphs. 
Kahn Sort: uses for build DAGs
DAG SP/LP: uses for find shortest and longest paths in DAG, based on source 
