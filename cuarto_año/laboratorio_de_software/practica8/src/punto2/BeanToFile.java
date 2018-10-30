package punto2;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BeanToFile {

    public static void beanToFile(Object o){
        try{
            Class objectClass = o.getClass();
            FileWriter fileWriter = null;
            for(Annotation a : objectClass.getAnnotations()){
                if(a.annotationType().equals(Archivo.class)){
                    Archivo archivo = (Archivo) a;
                    fileWriter = createFile(archivo.nombre(), objectClass.getName());
                }
            }
            if(fileWriter == null){
                fileWriter = createFile(objectClass.getName(), objectClass.getName());
            }
            for(Field field: objectClass.getDeclaredFields()){
                for(Annotation a : field.getDeclaredAnnotations()){
                    if(a.annotationType().equals(AlmacenarAtributo.class)){
                        field.setAccessible(true);
                        writeFile(fileWriter, "<nombreAtributo>" + field.getName() + "</nombreAtributo>");
                        writeFile(fileWriter, "<nombreValor>" + field.get(o) + "</nombreValor>");
                    }
                }
            }
            fileWriter.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static FileWriter createFile(String nameFile, String className) throws IOException{
        FileWriter f = new FileWriter(nameFile, true);
        writeFile(f, "<nombreClase>"+ className + "</nombreClase>");
        return f;
    }

    public static void writeFile(FileWriter f, Object object) throws IOException{
        f.write(object.toString());
        f.write("\n");
    }

    public static void main(String[] args){
        BeanToFile.beanToFile(new Mapeado());
    }
}
