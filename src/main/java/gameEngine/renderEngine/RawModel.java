package gameEngine.renderEngine;

public class RawModel {
    /*
        RawModel.class
        just a class for an raw model without texture and so on
        theres the Textured model what you should use lol
        writen by EPAXGAMING
     */

    //making some values needed
    private int vaoID;
    private int vertexCount;

    //constuctor for this stuff
    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    //some getters

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
