package gameEngine.engineTester;

import gameEngine.renderEngine.WindowManager;

public class MainGameLoop {

    public static  void main(String[] args){
        //window creation
        WindowManager.createWindow("testWindow",1920,1080,true,false);
        //updates if the window not should close
        while (!WindowManager.shouldClose()){
            WindowManager.updateWindow();
        }
        //if not updating it contunues here were i can do some shutdown an cleanup method
        //for now only window close method
        WindowManager.exitWindow();

    }


}
