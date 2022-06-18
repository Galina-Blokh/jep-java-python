import jep.Interpreter;
import jep.JepConfig;
import jep.MainInterpreter;

public class CallDocScript {
    public static void main(String[] args) {
        
        // set path for jep executing python3.9
        MainInterpreter.setJepLibraryPath("/Users/galinablokh/.pyenv/versions/3.9.10/envs/jepy/lib/python3.8/site-packages/jep/libjep.jnilib");
        
        // set path for python docs with python script to run
        jep.JepConfig jepConf = new JepConfig();
        jepConf.addIncludePaths(System.getProperty("user.dir")+"/src/main/java/");
        
        //create the interpreter for python executing
        Interpreter subInterp = jepConf.createSubInterpreter();

        //import  .py doc with to run
        subInterp.eval("import jep_path as p");

        // run each function from the .py doc I
        subInterp.eval("res_spacy = p.run_spacy_nlp('Apple is looking at buying U.K. startup for $1 billion')");
        System.out.println(subInterp.getValue("res_spacy"));
        
        //II
        subInterp.eval("res_tf = p.run_tf_simple_check()");
        System.out.println( subInterp.getValue("type(res_tf)"));
        
        //III
        subInterp.eval("res_c = p.get_c_path('.idea','*.xml')");
        System.out.println(subInterp.getValue("res_c"));

    }
    }

