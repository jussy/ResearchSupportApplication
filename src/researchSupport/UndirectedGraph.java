package researchSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author jussy
 * 
 * @param <V>
 */
public class UndirectedGraph<V extends Vertex<?, ?>> {

	private Map<String, V> vertices;

	/**
	 * 
	 */
	public UndirectedGraph() {
		this.vertices = new HashMap<String, V>();
	}

	/**
	 * 
	 * @param key
	 * @param v
	 * @return
	 */
	public boolean addVertex(String key, V v) {
		this.vertices.put(key, v);
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, V> getAllVertices() {
		return this.vertices;
	}

	/**
	 * 
	 * @param referee
	 * @return
	 */
	public boolean containsVertex(String name) {
		for (String s : this.vertices.keySet()) {
			if (s.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public V getVertex(String key) {
		return this.vertices.get(key);
	}
}
