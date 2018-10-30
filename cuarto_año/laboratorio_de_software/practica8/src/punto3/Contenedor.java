package punto3;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Contenedor {

    public static void proccess(Object o){
        Class myClass = o.getClass();
        Servidor servidor = (Servidor) myClass.getAnnotation(Servidor.class);
        List<Method> methodList = getMethodsToInvoke(myClass);
        FileWriter fileWriter = null;
        try{
           fileWriter = new FileWriter(servidor.archivo());
           startServer(servidor.direccion(), servidor.puerto(), methodList, o, fileWriter);
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try {
                fileWriter.close();
            }catch (Exception e2){
                System.out.println(e2.getMessage());
            }
        }
    }

    public static List<Method> getMethodsToInvoke(Class myClass){
        List<Method> methodList = null;
        for(Method method : myClass.getMethods()){
            if(method.isAnnotationPresent(Invocar.class)) methodList.add(method);
        }
        return methodList;
    }

    private static void startServer(String direccion, int puerto, List<Method> methodList, Object object, FileWriter fileWriter) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(direccion,puerto), 0);
        server.createContext("/", new MyHandler(methodList, object, fileWriter));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public static void main(String[] args) {
        ClaseCualquiera cc = new ClaseCualquiera();
        try {
            proccess(cc);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    static class MyHandler implements HttpHandler {
        List<Method> methodList;
        Object object;
        FileWriter fileWriter;

        @Override
        public void handle(HttpExchange t){
            try {
                sendHttpResponse(t);
                invokeMethods();
                updateLog(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public MyHandler(List<Method> methodList, Object object, FileWriter fileWriter) {
            this.methodList = methodList;
            this.object = object;
            this.fileWriter = fileWriter;
        }

        private void updateLog(HttpExchange t) throws IOException {
            // TODO hacer lo de escribir en un archivo
            Headers headers = t.getRequestHeaders();
            Object host = headers.get("Host");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            fileWriter.write("DATE: " + dateFormat.format(date) + ", HOST: " + host + "\n");
        }

        private void invokeMethods() throws Exception{
            for (Method method : methodList) {
                method.invoke(object);
            }
        }

        private void sendHttpResponse(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }
}
