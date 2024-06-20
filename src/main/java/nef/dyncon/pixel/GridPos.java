package nef.dyncon.pixel;

public class GridPos {
    public int x, y;

    public GridPos(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public long asLong() {
        return asLong(this.x, this.y);
    }

    public static long asLong(int x, int y) {
        return (long) x << 32 | y;
    }

    public GridPos setFromLong(long key) {
        this.x = (int) (key >> 32);
        this.y = (int) key;
        return this;
    }
}
