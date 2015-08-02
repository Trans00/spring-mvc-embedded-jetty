package mari

import groovy.transform.CompileStatic
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.ContextHandler
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.springframework.core.io.ClassPathResource
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Denis on 31.07.2015.
 */
@CompileStatic
public class Main {
    private static final int DEFAULT_PORT = 8080
    private static final String CONTEXT_PATH = "/"
    private static final String CONFIG_LOCATION = "mari.config"
    private static final String MAPPING_URL = "/*"
    public static  final AtomicInteger currentPage = new AtomicInteger(1)


    public static void main( String[] args ) throws Exception
    {
        Server server = new Server( DEFAULT_PORT );

        ContextHandler context = new ContextHandler();
        context.setContextPath( "/" );
        context.setHandler( getServletContextHandler(getContext()) );

        // Can be accessed using http://localhost:8080/hello

        server.setHandler( getServletContextHandler(getContext()) );

        // Start the server
        server.start();
        server.join();
    }

    private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase(new ClassPathResource("WEB-INF").getURI().toString());
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }
}