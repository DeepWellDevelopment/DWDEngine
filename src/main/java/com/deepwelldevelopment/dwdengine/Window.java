package com.deepwelldevelopment.dwdengine;

import com.deepwelldevelopment.dwdengine.gui.Canvas;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GLCapabilities;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Represents a GLFW window. Initializes the GLFW instance and creates the OpenGL context
 *
 * @author CJ Schaefer
 * @since 1.0
 */
public class Window {

    /**
     * The window id. US\sed in GLFW to differentiate multiple windows from each other
     */
    private long id;

    /**
     * The number of samples for the window. Used for antialiasing
     */
    private int samples;
    /**
     * The major version of GLFW. Should always be 3
     */
    private int majorVersion;
    /**
     * The minor version of GLFW. Should always be 3
     */
    private int minorVersion;
    /**
     * Whether or not forward compatibility is enable in the OpenGL context. For most situations this should be true
     */
    private boolean forwardCompat;
    /**
     * The window title
     */
    private String title;
    /**
     * The width of the window
     */
    private int width;
    /**
     * The height of the window
     */
    private int height;
    /**
     * The VertexArrayObject (vao) of the window. Will later be implements in an OO fashion
     */
    private int vao;

    private GLCapabilities capabilities;

    private Canvas rootCanvas;

    public Window(String title, int width, int height) {
        this(4, 3, 3, true, title, width, height);
    }

    public Window(String title) {
        this(4, 3, 3, true, title, 500, 500);
    }

    public Window(int majorVersion, int minorVersion, String title, int width, int height) {
        this(4, majorVersion, minorVersion, true, title, width, height);
    }

    public Window(int samples, int majorVersion, int minorVersion, boolean forwardCompat, String title, int width, int height) {
        this.samples = samples;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.forwardCompat = forwardCompat;
        this.title = title;
        this.width = width;
        this.height = height;

        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW");
        }
        glfwWindowHint(GLFW_SAMPLES, samples);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, majorVersion);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, minorVersion);
        if (forwardCompat)
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        else
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_FALSE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        id = glfwCreateWindow(width, height, title, NULL, NULL);

        if (id == NULL) {
            System.err.println("Failed to open the window. Is OpenGL " + majorVersion + "." + minorVersion + " supported on your system?");
            throw new IllegalStateException("Could not open the window");
        }

        glfwSetMouseButtonCallback(id, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (action == GLFW_PRESS) {
                    resize(Window.this.width + 5, Window.this.height + 5);
                }
            }
        });
        glfwSetWindowSizeCallback(id, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long l, int width, int height) {
                glViewport(0, 0, width, height);
                Window.this.width = width;
                Window.this.height = height;
            }
        });

        glfwMakeContextCurrent(id);
        glfwShowWindow(id);

        capabilities = createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);

        glViewport(0, 0, width, height);
        glClearColor(0.0f, 0.0f, 0.4f, 1.0f);

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        rootCanvas = new Canvas(this, 0, 0, width, height);
    }

    /**
     * Shows the window
     */
    public void show() {
        glfwShowWindow(id);
    }

    /**
     * Hides the window
     */
    public void hide() {
        glfwHideWindow(id);
    }

    /**
     * Hides the cursor
     */
    public void hideCursor() {
        glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
    }

    /**
     * Shows the cursor
     */
    public void showCursor() {
        glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    /**
     * Changes the size of the window
     *
     * @param width  The new width of the window
     * @param height The new veight of the window
     */
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        glfwSetWindowSize(id, width, height);
        glViewport(0, 0, width, height);
    }

    /**
     * Tell GLFW that the window should close. Does not actually close thw window, however since the GLFW
     * render loop is traditionally done using {@link org.lwjgl.glfw.GLFW#glfwWindowShouldClose(long)} this
     * should trigger a window close after the next frame.
     */
    public void close() {
        glfwSetWindowShouldClose(id, true);
    }

    /**
     * Renders the windows components to the OpenGL context. THis render function clears the screen and
     * delegates rendering, as well as swaps the window buffers. Most rendering occurs within the root canvas
     * and its children
     */
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        rootCanvas.render();
        glfwSwapBuffers(id);
    }

    public static void main(String[] args) {
        Window window = new Window("Test", 1024, 768);
        while (!glfwWindowShouldClose(window.id)) {
            window.render();
            System.out.print(window.width + ", " + window.height + "\r");
            glfwSwapBuffers(window.id);
            glfwPollEvents();
        }
        glfwDestroyWindow(window.id);
        glfwTerminate();
        glDeleteVertexArrays(window.vao);
    }

    public long getId() {
        return id;
    }

    public int getSamples() {
        return samples;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public boolean isForwardCompat() {
        return forwardCompat;
    }

    public GLCapabilities getCapabilities() {
        return capabilities;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
