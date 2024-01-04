package model.gameobjects;

public abstract class GameObject {
    protected int x;
    protected int y;

    public boolean reaches(GameObject other) {
        return (other.getX() == this.x && other.getY() == this.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
