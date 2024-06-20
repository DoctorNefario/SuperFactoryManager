package nef.dyncon;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongAVLTreeSet;
import it.unimi.dsi.fastutil.longs.LongIterator;

import java.util.ArrayList;
import java.util.List;

public class NodeGrouper {
    final Long2IntOpenHashMap nodes = new Long2IntOpenHashMap();
    final List<LongAVLTreeSet> groups = new ArrayList<>();
    private final IntArrayList emptyGroups = new IntArrayList();
    private final IntArrayList matchingGroupNums = new IntArrayList();

    int getGroup(long node) {
        return nodes.getOrDefault(node, -1);
    }

    void addNode(long node) {
        if (nodes.containsKey(node)) {
            return;
        }
        var neighbors = getNeighbors(node);


        int largestGroupNum = -1;
        int largestGroupSize = 0;
        matchingGroupNums.clear();
        for (var neighbor : neighbors) {
            if (nodes.containsKey(neighbor)) {
                var groupNum = nodes.get(neighbor);

                if (!matchingGroupNums.contains(groupNum)) {
                    matchingGroupNums.push(groupNum);

                    int size = groups.get(groupNum).size();

                    if (largestGroupSize < size) {
                        largestGroupNum = groupNum;
                        largestGroupSize = size;
                    }
                }
            }
        }

        if (!matchingGroupNums.isEmpty()) {
            if (largestGroupNum == -1) {
                largestGroupNum = matchingGroupNums.getInt(matchingGroupNums.size() - 1);
            }
            matchingGroupNums.rem(largestGroupNum);

            LongAVLTreeSet largestGroup = groups.get(largestGroupNum);

            nodes.put(node, largestGroupNum);
            largestGroup.add(node);

            // iterate over remaining groups
            // TODO: faster iteration?
            for (int groupNum : matchingGroupNums) {
                LongAVLTreeSet group = groups.get(groupNum);

                for (LongIterator it = group.longIterator(); it.hasNext(); ) {
                    var n = it.nextLong();
                    nodes.put(n, largestGroupNum);
                }

                largestGroup.addAll(group);
                group.clear();

                emptyGroups.add(groupNum);
            }

            return;
        }

        // --- no matching groups ---

        int groupNum;
        if (!emptyGroups.isEmpty()) {
            groupNum = emptyGroups.popInt();
            groups.get(groupNum).add(node);
        } else {
            groupNum = groups.size();
            var lar = new LongAVLTreeSet();
            lar.add(node);
            groups.add(lar);
        }
        nodes.put(node, groupNum);
    }

    void delNode(long node) {
        int groupNum = getGroup(node);
        LongAVLTreeSet group = groups.get(groupNum);

        group.remove(node);
        if (group.isEmpty()) {
            emptyGroups.add(groupNum);
        }
        nodes.remove(node);

        // TODO: group disconnection logic
    }

    protected long[] getNeighbors(long node) {
        return new long[]{};
    }
}
