package deltas;

import java.util.ArrayList;
import java.util.List;

public class CollectionSplitter {
    public static List<?> splitChunk(Chunk chunk, List<Integer> relativeSplitPoints) {
        int currentPosInChunk = 0;
        int currentOffset = 0;
        int lastSplit = -1;
        List<Chunk> result = new ArrayList<>();
        for (int splitPoint : relativeSplitPoints) {
            int subChunkSize = splitPoint - lastSplit - 1;
            if (subChunkSize > 0) {
                int subChunkPos = chunk.getPosition() + currentOffset;
                List<?> tokenSublist = chunk.getLines().subList(currentPosInChunk, currentPosInChunk + subChunkSize);
                result.add(new Chunk(subChunkPos, tokenSublist));
                currentPosInChunk += subChunkSize;
                currentOffset += subChunkSize;
            }
            lastSplit = splitPoint;
            currentOffset++;
        }
        if (currentPosInChunk < chunk.getLines().size()) {
            int tailChunkPos = chunk.getPosition() + currentOffset;
            List<?> tailSublist = chunk.getLines().subList(currentPosInChunk, chunk.getLines().size());
            result.add(new Chunk(tailChunkPos, tailSublist));
        }
        return result;
    }
}
