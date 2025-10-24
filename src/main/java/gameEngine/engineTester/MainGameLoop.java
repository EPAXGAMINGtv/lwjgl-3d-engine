package gameEngine.engineTester;

import gameEngine.renderEngine.Loader;
import gameEngine.renderEngine.RawModel;
import gameEngine.renderEngine.Renderer;
import gameEngine.renderEngine.WindowManager;

/*
    MainGameLoop.class
    just a class for the game were you init stuff and building it of all the fetures
    lol



    BTW this engine is inspired by the series from Thin Matrix's 3d game engine tutorial its just modern version with later some more fetures
    and uses newer version of lwjgl and just have some more fetures lol
    written by EPAXGAMING
 */


public class MainGameLoop {

    public static  void main(String[] args){
        //window creation
        WindowManager.createWindow("testWindow",1920,1080,false,false);

        //Loader creation
        Loader loader = new Loader();
        //Renderer ceation
        Renderer renderer = new Renderer();

        //test vertecies
        float[] vertices = {
                // triangle 1
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f,  0.5f, 0f,
                // triangle 2
                0.5f,  0.5f, 0f,
                -0.5f,  0.5f, 0f,
                -0.5f, -0.5f, 0f
        };


        RawModel model = loader.loadToVAO(vertices);

        //updates if the window not should close
        while (!WindowManager.shouldClose()){
            //preparing renderer
            renderer.prepare();
            //render it
            renderer.render(model);

            //doing renderreing and creating window and stuff going on in you game
            WindowManager.updateWindow();
        }
        //if not updating it contunues here were i can do some shutdown an cleanup method

        //cleaning loader
        loader.cleanUp();
        //closing window
        WindowManager.exitWindow();

    }


}
