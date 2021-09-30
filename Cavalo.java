package Entities;

public class Cavalo {
    int posX, posY;

    public Cavalo(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "POS X: " + posX + "\t" + "POS Y: " + posY;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + posX;
        result = prime * result + posY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cavalo other = (Cavalo) obj;
        if (posX != other.posX)
            return false;
        if (posY != other.posY)
            return false;
        return true;
    }
}
