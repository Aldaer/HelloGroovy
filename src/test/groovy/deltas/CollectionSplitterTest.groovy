package deltas

import org.junit.Test

import static deltas.CollectionSplitter.splitChunk

class CollectionSplitterTest {
    def chunk = new Chunk(3, ['A', 'B', 'C', 'D', 'E', 'F'])

    @Test
    void testCollectionSplit1() {
        def insertedTokens1 = [0, 1, 4]
        def result = splitChunk(chunk, insertedTokens1)

        def expected1 = new Chunk(5, ['A', 'B'])
        def expected2 = new Chunk(8, ['C', 'D', 'E', 'F'])

        assert result == [expected1, expected2]
    }

    @Test
    void testCollectionSplit2() {
        def insertedTokens1 = [5]
        def result = splitChunk(chunk, insertedTokens1)

        def expected1 = new Chunk(3, ['A', 'B', 'C', 'D', 'E'])
        def expected2 = new Chunk(9, ['F'])

        assert result == [expected1, expected2]
    }

    @Test
    void testCollectionSplit3() {   // SHOULD NOT HAPPEN
        def insertedTokens1 = [6]
        def result = splitChunk(chunk, insertedTokens1)

        def expected1 = new Chunk(3, ['A', 'B', 'C', 'D', 'E', 'F'])

        assert result == [expected1]
    }

    @Test
    void testCollectionSplit4() {
        def insertedTokens1 = [0]
        def result = splitChunk(chunk, insertedTokens1)

        def expected1 = new Chunk(4, ['A', 'B', 'C', 'D', 'E', 'F'])

        assert result == [expected1]
    }
}
