package dijkstra
class DijkstrasShortestPathAlgoritm {  
    def graph, start, destination      
    // PriorityQueue performs much better than List - O(log(n)) for poll.      
    def unsettledNodes = new PriorityQueue<String>(100, new Comparator<String>() {  
       public int compare(String node1, String node2) {  
            shortestDistance(node1).compareTo(shortestDistance(node2))  
        }  
    });  
    def shortestDistances = [:]  
    def predecessors = [:]  
    def settledNodes = [] as Set  
  
    DijkstrasShortestPathAlgoritm(graph, start, destination) {  
        this.graph = graph  
        this.start = start  
        this.destination = destination  
  
        unsettledNodes.add(start)  
        shortestDistances[(start)] = 0  
    }  
  
    int shortestDistance(node) {  
        shortestDistances.containsKey(node) ? shortestDistances[node] : Integer.MAX_VALUE  
    }  
  
    def extractMin() {  
        unsettledNodes.poll()  
    }  
  
    def unsettledNeighbours(node) {  
        graph.findAll { edge ->  
            edge.node1 == node && !settledNodes.contains(edge.node2)  
        }  
    }  
  
    def relaxNeighbours(node) {  
        unsettledNeighbours(node).each { edge ->  
            if (shortestDistance(edge.node2) > shortestDistance(edge.node1) + edge.distance) {  
                shortestDistances[edge.node2] = shortestDistance(edge.node1) + edge.distance  
                predecessors[edge.node2] = edge.node1  
                if (!unsettledNodes.contains(edge.node2)) {  
                    unsettledNodes.add(edge.node2)  
                }  
            }  
        }  
    }  
  
    def calculateShortestPath() {  
        while (!unsettledNodes.isEmpty()) {  
            String node = extractMin()  
            if (node == destination) {  
                break  
            }  
            settledNodes += node  
            relaxNeighbours(node)  
        }  
        shortestDistances[destination]  
    }  
  
    private def getShortestPath(node, path) {  
        node == start ? [node]+path : getShortestPath(predecessors[node], [node]+path)  
    }  
      
    def getShortestPathWay() {  
        getShortestPath(destination, [])   
    }  
}  