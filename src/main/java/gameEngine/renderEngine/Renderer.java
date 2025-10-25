package gameEngine.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {
    /*
        Renderer.class
        just not completetet yet later it will split to some renderers and one master renderre
        writen by EPAXGAMING
     */

    /*
        this method is to preparing before rendering
     */

    public void prepare(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(1,0,0,1);
    }


    /*
    this method is to catullay render a Raw Model
     */
    public void render(RawModel model){
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES,model.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

}
