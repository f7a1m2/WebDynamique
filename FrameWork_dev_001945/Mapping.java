package etu1945.framework;

public class Mapping {
    
    String className;
    String Method;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public Mapping(String className, String method) {
        setClassName(className);
        setMethod(method);
    }

    public Mapping() {
    
    }
}
