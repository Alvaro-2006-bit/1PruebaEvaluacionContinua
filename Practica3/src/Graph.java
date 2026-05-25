import java.util.*;

public class Graph<V> {

    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    // Añadir vértice
    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) return false;
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    // Añadir arista
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);

        if (adjacencyList.get(v1).contains(v2)) return false;

        adjacencyList.get(v1).add(v2);
        return true;
    }

    // Obtener adyacentes
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice no existe");
        }
        return adjacencyList.get(v);
    }

    // Comprobar si existe vértice
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    // Mostrar grafo
    @Override
    public String toString() {
        return adjacencyList.toString();
    }

    // 🔥 MÉTODO IMPORTANTE
    public List<V> onePath(V v1, V v2) {

        Stack<V> abierta = new Stack<>();
        Map<V, V> traza = new HashMap<>();

        // Inicialización
        abierta.push(v1);
        traza.put(v1, null);

        boolean encontrado = false;
        V actual = null;

        // Búsqueda en profundidad (DFS)
        while (!abierta.isEmpty() && !encontrado) {
            actual = abierta.pop();

            if (actual.equals(v2)) {
                encontrado = true;
            } else {
                for (V vecino : adjacencyList.getOrDefault(actual, new HashSet<>())) {
                    if (!traza.containsKey(vecino)) {
                        abierta.push(vecino);
                        traza.put(vecino, actual);
                    }
                }
            }
        }

        // Si no encuentra
        if (!encontrado) return null;

        // Reconstruir camino
        List<V> camino = new ArrayList<>();
        V paso = v2;

        while (paso != null) {
            camino.add(0, paso);
            paso = traza.get(paso);
        }

        return camino;
    }
}
