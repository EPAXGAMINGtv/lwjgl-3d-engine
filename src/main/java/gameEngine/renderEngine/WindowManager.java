package gameEngine.renderEngine;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class WindowManager {

    private static long window; // GLFW window handle
    private static boolean fullscreen;
    private static boolean vsync;

    /**
     * creates window with opengl context
     */
    public static void createWindow(String title, int width, int height, boolean isFullscreen, boolean useVsync) {
        fullscreen = isFullscreen;
        vsync = useVsync;

        // GLFW init
        if (!glfwInit()) {
            throw new IllegalStateException("ERROR: Can't init GLFW");
        }

        // setting up GLFW-Window options
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // window firstly invisible
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // window can be scalled maybe i will deactivate it
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        long monitor = isFullscreen ? glfwGetPrimaryMonitor() : NULL;

        // Window creation
        window = glfwCreateWindow(width, height, title, monitor, NULL);
        if (window == NULL) {
            throw new RuntimeException("ERROR: Can't create glfw window!");
        }

        // if noot fullscreen makes it in the middle of the screen
        if (!isFullscreen) {
            try (MemoryStack stack = stackPush()) {
                IntBuffer pWidth = stack.mallocInt(1);
                IntBuffer pHeight = stack.mallocInt(1);

                glfwGetWindowSize(window, pWidth, pHeight);
                GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

                glfwSetWindowPos(
                        window,
                        (vidmode.width() - pWidth.get(0)) / 2,
                        (vidmode.height() - pHeight.get(0)) / 2
                );
            }
        }

        // OpenGL-Context activation
        glfwMakeContextCurrent(window);
        // activate vsync if enabled
        glfwSwapInterval(useVsync ? 1 : 0);
        // makes window visible
        glfwShowWindow(window);
        // opengl init
        GL.createCapabilities();
        //prints out the opengl version
        System.out.println("OpenGL-Version: " + glGetString(GL_VERSION));
    }

    /**
     * updates the window and redraw it with the GPU
     */
    public static void updateWindow() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    /**
     * returns he window state
     */
    public static boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    /**
     *exiting game and give all the resources back
     */
    public static void exitWindow() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    /**
     * Acess for window id
     */
    public static long getWindow() {
        return window;
    }
}
