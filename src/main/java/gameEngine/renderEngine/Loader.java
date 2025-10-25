package gameEngine.renderEngine;


import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
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
    public RawModel loadToVAO(float[] positions,int[]indices){
        int vaoID = createVAO();
        storeDataInIntBuffer(indices);
        storeDataInAttribList(0,positions);
        unbindVAO();
        return new RawModel(vaoID, indices.length);
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
        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboID);
        //creating float buffer
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        //puts in gl
        GL20.glBufferData(GL15.GL_ARRAY_BUFFER,buffer, GL15.GL_STATIC_DRAW);
        //makes it in atrrib pointer
        GL20.glVertexAttribPointer(attribNum,3, GL11.GL_FLOAT,false,0,0);
        //unbind  buffer
        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
    }

    /*
       just a method to unbind some vao shit
     */
    private void unbindVAO(){
        //unbinds the list with seting it to zero
        GL30.glBindVertexArray(0);
    }
    /*
        method to bind indices buffers and add them to vbos
     */

    private void bindIndicesBuffer(int[]indices){
        //creating vbo
        int vboID =GL15.glGenBuffers();
        //adding vbo to lost
        vbos.add(vboID);
        //gen buffer to user the vbo
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,vboID);
        //convert nindices in int buffer
        IntBuffer buffer= storeDataInIntBuffer(indices);
        //storing
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
    }

    /*
        method for storing data into an int buffer which will be used for renderering and
        stuff later
     */

    private IntBuffer storeDataInIntBuffer(int[]data){
        //creating int buffer wihtbuffer utils
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        //ading data into the buffer
        buffer.put(data);
        //flipping for reading out of it
        buffer.flip();
        //returning the buffer
        return  buffer;
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
