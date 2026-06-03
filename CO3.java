import java.util.*;

public class CFG_DCE {

    /** FIND DEAD BLOCKS USING ITERATIVE DFS */
    static Set<String> findDeadBlocks(Map<String, List<String>> cfg, String entry) {

        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();

        stack.push(entry);

        while (!stack.isEmpty()) {

            String node = stack.pop();

            if (visited.contains(node)) continue;

            visited.add(node);

            List<String> neighbors = cfg.getOrDefault(node, new ArrayList<>());

            // process in reverse alphabetical order for correct DFS
            Collections.sort(neighbors, Collections.reverseOrder());

            for (String nxt : neighbors) {
                if (!visited.contains(nxt)) {
                    stack.push(nxt);
                }
            }
        }

        Set<String> dead = new HashSet<>(cfg.keySet());
        dead.removeAll(visited);

        return dead;
    }

    /** POST ORDER USING DFS */
    static List<String> computePostOrder(Map<String, List<String>> cfg, String entry) {

        Set<String> visited = new HashSet<>();
        List<String> order = new ArrayList<>();

        dfs(cfg, entry, visited, order);

        return order;
    }

    static void dfs(Map<String, List<String>> cfg,
                    String node,
                    Set<String> visited,
                    List<String> order) {

        if (visited.contains(node)) return;

        visited.add(node);

        List<String> neighbors = cfg.getOrDefault(node, new ArrayList<>());

        Collections.sort(neighbors);

        for (String nxt : neighbors) {
            dfs(cfg, nxt, visited, order);
        }

        // POST ORDER ADD
        order.add(node);
    }

    // TEST CASE
    public static void main(String[] args) {

        Map<String, List<String>> cfg = new HashMap<>();

        cfg.put("B0", Arrays.asList("B1", "B2"));
        cfg.put("B1", Arrays.asList("B3", "B4"));
        cfg.put("B2", Arrays.asList("B5"));
        cfg.put("B3", Arrays.asList("B6"));
        cfg.put("B4", Arrays.asList("B6"));
        cfg.put("B5", Arrays.asList("B6"));
        cfg.put("B6", Arrays.asList("B8"));
        cfg.put("B7", Arrays.asList("B8"));
        cfg.put("B8", new ArrayList<>());

        String entry = "B0";

        System.out.println("Dead Blocks: " + findDeadBlocks(cfg, entry));
        System.out.println("Post Order: " + computePostOrder(cfg, entry));
    }
}
