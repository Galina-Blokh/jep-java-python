import jep.*;
import jep.MainInterpreter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class JepForPython38 {

    public static void main(String[] args) {
        String pythonFolder = System.getenv("CLASSIFICATIONS_SDK_PYTHON");
        System.out.println(pythonFolder);
        String jepPath = pythonFolder + "/jep/libjep.jnilib";
        if (!Files.exists(Path.of(jepPath))){
            jepPath = pythonFolder + "/jep/libjep.so";
        }
        //create the interpreter for python executing
        MainInterpreter.setJepLibraryPath(jepPath);
        jep.JepConfig jepConf = new JepConfig();
        jepConf.addIncludePaths("/Users/galinablokh/Documents/POCNerClassificationSDK/src/main/java");
        jepConf.addIncludePaths(pythonFolder);
        SharedInterpreter.setConfig(jepConf);
        SharedInterpreter subInterp = new SharedInterpreter();
        // run each function from the .py doc I
        subInterp.eval("import jep_path as p");
        subInterp.eval("res_spacy = p.run_spacy_nlp('Apple is looking at buying U.K. startup for $1 billion')");
        ArrayList result = (ArrayList) subInterp.getValue("res_spacy");
        for (Object item:result) {
            System.out.println(item.toString());
        }
    }
}
/*
/Users/galinablokh/.pyenv/versions/3.8.6/envs/jepy/lib/python3.8/site-packages
[Apple, Apple, PROPN, NNP, nsubj, Xxxxx, true, false]
[is, be, AUX, VBZ, aux, xx, true, true]
[looking, look, VERB, VBG, ROOT, xxxx, true, false]
[at, at, ADP, IN, prep, xx, true, true]
[buying, buy, VERB, VBG, pcomp, xxxx, true, false]
[U.K., U.K., PROPN, NNP, dobj, X.X., false, false]
[startup, startup, VERB, VBD, dep, xxxx, true, false]
[for, for, ADP, IN, prep, xxx, true, true]
[$, $, SYM, $, quantmod, $, false, false]
[1, 1, NUM, CD, compound, d, false, false]
[billion, billion, NUM, CD, pobj, xxxx, true, false]
*\
 */
