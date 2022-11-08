package spoon.visitors;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class CtClassVisitor extends AbstractProcessor<CtClass>{
    private List<CtClass> classes = new ArrayList<CtClass>();
    public void process(CtClass element) {
        classes.add(element);
    }
    public List<CtClass> getClasses() {
        return classes;
    }
    public void setClasses(List<CtClass> classes) {
        this.classes = classes;
    }
}
