import jep.Interpreter;
import jep.MainInterpreter;
import jep.SharedInterpreter;
import java.text.MessageFormat;

public class FirstSetUpCall {
    public static void main(String[] args) {
        //set the Jep lib path
        MainInterpreter.setJepLibraryPath("/Users/galinablokh/.pyenv/versions/miniforge3/envs/" +
                "env_tf/lib/python3.9/site-packages/jep/libjep.jnilib");
        //create an interpreter obj
        Interpreter interp = new SharedInterpreter();

        //try spacy run
        long startTimeSpacyLoad = System.currentTimeMillis();
        interp.eval("import spacy");
        interp.eval("nlp = spacy.load(\"en_core_web_sm\")");
        getRuntimeinms(startTimeSpacyLoad,"startSpacyLoad");
        //use tokenizer
        long startTimeSpacy = System.currentTimeMillis();
        interp.eval("doc = nlp('Apple is looking at buying U.K. startup for $1 billion')");
        System.out.println(interp.getValue("type(doc)"));
        Object res_spacy = interp.getValue("[(token.text, token.lemma_, token.pos_, token.tag_, " +
                "token.dep_, token.shape_, token.is_alpha, token.is_stop) for token in doc]");
        System.out.println("res_spacy:\n"+res_spacy);
        getRuntimeinms(startTimeSpacy,"SpacyTokenizer");

        //try tensorflow run
        long startTimeTensorflow = System.currentTimeMillis();
        interp.eval("import tensorflow as tf");
        interp.eval("res = tf.version.VERSION");
        String res = (String) interp.getValue("res");
        System.out.println("Tensorflow version is:\n"+res);
        getRuntimeinms(startTimeTensorflow,"TimeTensorflowLoad");

    }
    public static void getRuntimeinms(long startTime, String processName){
        //measuring elapsed time using System.nanoTime
        long elapsedTime = System.currentTimeMillis() - startTime;
        String strResult = MessageFormat.format("Total execution time in millis for{1}: {0}\n", elapsedTime,processName);
        System.out.println(strResult);

    }
}
