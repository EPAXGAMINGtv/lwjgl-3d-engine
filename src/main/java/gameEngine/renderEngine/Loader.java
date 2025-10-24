package gameEngine.renderEngine;


import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    /*
        Loader.class
        this class is made to storing loding vaos and stuff
        just a loader for some things

        written by EPAXGAMING
     */

    private List<Integer> vaos = new ArrayList<Integer>();
    private  List<Integer> vbos = new ArrayList<Integer>();


    /*
        just a method to load things to the vao
     */
    public RawModel loadToVAO(float[] positions){
        int vaoID = createVAO();
        storeDataInAttribList(0,positions);
        unbindVAO();
        return new RawModel(vaoID, positions.length/3);
    }

    /*
        this method is just for cleaning up the memory and stuff

     */

    public void cleanUp(){
        //clearing vaos
        for (int vao:vaos){
            GL30.glDeleteVertexArrays(vao);
        }

        //clearing vbos
        for (int vbo:vbos){
            GL15.glDeleteBuffers(vbo);
        }
    }

    /*
        method to create vao and returns the id as an int
     */
    private int createVAO(){
        //creating an vao with GL30 method and returning it
        int vaoID = GL30.glGenVertexArrays();
        //adds it to a list to track some things
        vaos.add(vaoID);
        //binds thje vaoID
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    /*
        method to store some data in the vao
     */
    private void storeDataInAttribList(int attribNum,float[]data){
        //creates empty vbo
        int vboID = GL15.glGenBuffers();
        //store in vbo list to track some things
        vbos.add(vboID);
        //binds the vbo
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboID);
        //creating float buffer
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        //puts in gl
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER,buffer, GL15.GL_STATIC_DRAW);
        //makes it in atrrib pointer
        GL20.glVertexAttribPointer(attribNum,3, GL11.GL_FLOAT,false,0,0);
        //unbind  buffer
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
    }

    /*
       just a method to unbind some vao shit
     */
    private void unbindVAO(){
        //unbinds the list with seting it to zero
        GL30.glBindVertexArray(0);
    }

    /*
        help method to convert things into a float buffer
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data){
        //creating floatbuffer
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        //puts data in
        buffer.put(data);
        //flip buffer
        buffer.flip();
        return buffer;
    }

}
