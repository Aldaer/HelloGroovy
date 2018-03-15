package deltas

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Chunk {
    final int position
    final List<?> lines

    Chunk(int position, List<?> lines) {
        this.position = position
        this.lines = lines
    }
}
