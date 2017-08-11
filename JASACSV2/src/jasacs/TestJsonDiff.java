/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasacs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Livinvg
 */
public class TestJsonDiff {

    JSONObject jso = new JSONObject();

    JSONObject version;//student version
    JSONObject model;//test model

    public TestJsonDiff(JSONObject mod, JSONObject ver) {
        model = mod;
        version = ver;
    }

    public String testClassDescriptions() {
        String result = "";
        JSONArray ma = (JSONArray) model.get("class");
        JSONArray va = (JSONArray) version.get("class");
        JSONObject m = (JSONObject) ma.get(0);
        JSONObject v = (JSONObject) va.get(0);

        if (m.equals(v)) {
            result += "All "+m.get("type name")+" description seems to be defined perfectly";
            System.out.println("Checking: " + result);
        } else {
             if (m.get("type name").toString().equalsIgnoreCase(v.get("type name").toString())) {
                result += "The class is rightly defined as a(n) " + m.get("type name") + "<br/>";
            } else {
                result += "The class is wrongly defined as a(n) " + v.get("type name") + " instead of being a(n) " + m.get("type name") + "<br/>";
            }
            if (m.get("package name").toString().equalsIgnoreCase(v.get("package name").toString())) {
                result += "The "+m.get("type name")+" is in the right Package<br/>";
            } else {
                result += "The "+v.get("type name")+" is not in the right Package<br/>";
            }
            if (m.get("modifier").toString().equalsIgnoreCase(v.get("modifier").toString())) {
                result += "The right modifier is used for the "+m.get("type name")+" definition<br/>";
            } else {
                result += "The wrong modifier is used for the "+v.get("type name")+" definition<br/>";
            }
            // testing the super class          
            if (m.get("type name").toString().equalsIgnoreCase("class")
                    || m.get("type name").toString().equalsIgnoreCase("enum")) {

                if(v.get("type name").toString().equalsIgnoreCase("interface")){
                    result += "The is an interface, it should have been declared as a class<br/>";
                }else{
                    if(m.get("type name").toString().equals("enum") && v.get("type name").toString().equals("enum")){
                     //  result += "The is enum class extend the right superclass the Test Model<br/>";
                     // note if model == enum and stuent version is a class or vice versa
                    }else{
                    
                        if (m.get("superclass").toString().equalsIgnoreCase(v.get("superclass").toString())) {
                            result += "The "+m.get("type name")+" extends the right superclass<br/>";
                        } else {
                            result += "This "+v.get("type name")+" does not extend the right superclass the Test Model<br/>";
                        }
                
                    }
              }
                
                
                
            } else {
                if(!v.get("type name").toString().equalsIgnoreCase("interface")){
                    result += "The "+v.get("type name")+" is meant to be an interface<br/>";
                }else{
                // here is an array so do work here
//            if(m.get("Implemented Interface").toString().equalsIgnoreCase(v.get("Implemented Interface").toString())){
//            result +="The class extends the right superclass";           
//            }else {
//            result +="The class extends a different superclass from that of the Test Model";           
//            }   
                JSONArray iMA = (JSONArray) m.get("Implemented Interface");
                JSONArray iVA = (JSONArray) v.get("Implemented Interface");
                
                
               if (iMA.isEmpty() & iMA.equals(iVA)) {
                    result += "Perfect, no interface implenmentation is required";
                    System.out.println("Checking: " + result);
                } 
                 else if (iMA.equals(iVA)) {
                    result = "All implemented interfaces seems to be defined perfectly<br/>";
                    System.out.println("Checking: " + result);
                } else {
                    int found = 0;
                    int notFound = 0;
                    String[] mar = new String[iMA.size()];
                    String[] var = new String[iVA.size()];
                    for (int i = 0; i < mar.length; i++) {
                        mar[i] = iMA.get(i).toString();
                    }
                    for (int i = 0; i < var.length; i++) {
                        var[i] = iVA.get(i).toString();
                    }

                    for (String s : var) {
                        boolean p = false;
                        for (String nx : mar) {
                            if (s.equalsIgnoreCase(nx)) {
                                p = true;
                                break;
                            }
                        }
                        if (p) {
                            found = found + 1;
                        } else {
                            notFound = notFound + 1;
                        }

                    }

                    System.out.println("found: " + found);
                    System.out.println("not found: " + notFound);
                    if (found == mar.length & notFound == 0) {
                        result += "All interfaces have been rightly implemented<br/>";
                    } else if (found == mar.length & notFound > 0) {
                        result += "All interfaces have been rightly defined,however, however, additional interfaces were implemented<br/>";
                    } else if ((found < mar.length & found > 0) & notFound == 0) {
                        result += "Not all interfaces were implemented<br/>";
                    } else if ((found < mar.length & found > 0) & notFound > 0) {
                        result += "Not all interfaces were implemented, Some alien interfaces were implemented<br/>";
                    } else {

                        result += "Some alien interfaces were implemented<br/>";
                    }

                }
            }
            }

        }
        System.out.println("result: " + result);
        return result;
    }

    public String testFields() {
        String result = "";
        JSONArray m = (JSONArray) model.get("fields");
        JSONArray v = (JSONArray) version.get("fields");
        

        if (m.isEmpty() & m.equals(v)) {
            result += "Perfect, no Field definition is required";
            System.out.println("Checking: " + result);
        } 
        else if (m.equals(v)) {
            result = "All Fields seems to be defined perfectly";
            System.out.println("Checking: " + result);
        } 
        else {
            int found = 0;
            int notFound = 0;  
//            int pFound = 0;
//            int nopFound = 0;
            JSONObject[] mar = new JSONObject[m.size()];
            JSONObject[] var = new JSONObject[v.size()];
            for (int i = 0; i < mar.length; i++) {
                mar[i] = (JSONObject) m.get(i);
            }
            for (int i = 0; i < var.length; i++) {
                var[i] = (JSONObject) v.get(i);
            }
            
            for(JSONObject js : var){
                boolean p = false;
                for(JSONObject ms : mar){
                    if(js.equals(ms)){
                        p = true;
                        break;
                    }
                }
                if (p) {
                    found = found + 1;
                } else {
                    if(js.get("name").equals("studentID")){
                        System.out.println("Student id found");                   
                    }else{
                    notFound = notFound + 1;
                    
                }
                }
            }

            System.out.println("found: " + found);
            System.out.println("not found: " + notFound);
            
            if (mar.length == 0 & notFound == 0) {
                result += "Perfect, no Field definition is required";
            }
            else if (found == mar.length & notFound == 0) {
                result += "All Fields have been rightly defined";
            }
            else if (found == mar.length & notFound > 0) {
                result += "All Fields have been rightly defined,however, additional Fields defined";
            } else if ((found < mar.length & found > 0) & notFound == 0) {
                result += "Not all Fields are defined";
            } else if ((found < mar.length & found > 0) & notFound > 0) {
                result += "Not all Fields are defined, Some alien Fields are defined";
            } else {
                result += "Only alien Fields are defined";
            }

        }

        System.out.println("result: " + result);
        return result;
    }
    

    public String testConstructors() {
        String result = "";
        JSONArray m = (JSONArray) model.get("constructors");
        JSONArray v = (JSONArray) version.get("constructors");
        

        if (m.isEmpty() & m.equals(v)) {
            result += "Perfect, no Constructor definition is required";
            System.out.println("Checking: " + result);
        } 
        else if (m.equals(v)) {
            result = "All Constructors seems to be defined perfectly";
            System.out.println("Checking: " + result);
        } else {
            int found = 0;
            int notFound = 0;  
//            int pFound = 0;
//            int nopFound = 0;
            JSONObject[] mar = new JSONObject[m.size()];
            JSONObject[] var = new JSONObject[v.size()];
            for (int i = 0; i < mar.length; i++) {
                mar[i] = (JSONObject) m.get(i);
            }
            for (int i = 0; i < var.length; i++) {
                var[i] = (JSONObject) v.get(i);
            }
            
            for(JSONObject js : var){
                boolean p = false;
                for(JSONObject ms : mar){
                    if(js.equals(ms)){
                        p = true;
                        break;
                    }
                }
                if (p) {
                    found = found + 1;
                } else {
                    notFound = notFound + 1;
                }
            }


            System.out.println("found: " + found);
            System.out.println("not found: " + notFound);
            
            if (mar.length == 0 & notFound == 0) {
                result += "Perfect, no constructor definition is required";
            }
            else if (found == mar.length & notFound == 0) {
                result += "All Constructors have been rightly defined";
            }
            else if (found == mar.length & notFound > 0) {
                result += "All Constructors have been rightly defined,however, additional Constructors defined";
            } else if ((found < mar.length & found > 0) & notFound == 0) {
                result += "Not all Constructors are defined";
            } else if ((found < mar.length & found > 0) & notFound > 0) {
                result += "Not all Constructors are defined, Some alien Constructors are defined";
            } else {
                result += "Only alien Constructors are defined";
            }

        }

        System.out.println("result: " + result);
        return result;
    }

    public String testMethods() {
        String result = "";
        JSONArray m = (JSONArray) model.get("methods");
        JSONArray v = (JSONArray) version.get("methods");
        

        if (m.isEmpty() & m.equals(v)) {
            result += "Perfect, no Method definition is required";
            System.out.println("Checking: " + result);
        } 
        else if (m.equals(v)) {
            result = "All Methods seems to be defined perfectly";
            System.out.println("Checking: " + result);
        } else {
            int found = 0;
            int notFound = 0;  
//            int pFound = 0;
//            int nopFound = 0;
            JSONObject[] mar = new JSONObject[m.size()];
            JSONObject[] var = new JSONObject[v.size()];
            for (int i = 0; i < mar.length; i++) {
                mar[i] = (JSONObject) m.get(i);
            }
            for (int i = 0; i < var.length; i++) {
                var[i] = (JSONObject) v.get(i);
            }
            
            for(JSONObject js : var){
                boolean p = false;
                for(JSONObject ms : mar){
                    if(js.equals(ms)){
                        p = true;
                        break;
                    }
                }
                if (p) {
                    found = found + 1;
                } else {
                    notFound = notFound + 1;
                }
            }


            System.out.println("found: " + found);
            System.out.println("not found: " + notFound);
            
            if (mar.length == 0 & notFound == 0) {
                result += "Perfect, no Methods definition is required";
            }
            else if (found == mar.length & notFound == 0) {
                result += "All Methods have been rightly defined";
            }
            else if (found == mar.length & notFound > 0) {
                result += "All Methods have been rightly defined,however, additional Methods defined";
            } else if ((found < mar.length & found > 0) & notFound == 0) {
                result += "Not all Methods are defined";
            } else if ((found < mar.length & found > 0) & notFound > 0) {
                result += "Not all Methods are defined, Some alien Methods are defined";
            } else {
                result += "Only alien Methods are defined";
            }

        }

        System.out.println("result: " + result);
        return result;
    }

    public static void main(String[] args) {
        JSONObject jsob = new JSONObject();
        JSONObject jsoc = new JSONObject();

        JSONArray jacob = new JSONArray();
        jacob.add("");

        jsob.put("constructors", jacob);

        JSONArray jamb = new JSONArray();
        jamb.add("public abstract void mastapam.RecordSwitcherInterface.gotoCurrent()");
        jamb.add("public abstract void mastapam.RecordSwitcherInterface.gotoPrevious()");
        jamb.add("public abstract void mastapam.RecordSwitcherInterface.gotoNext()");
        jamb.add("public abstract void mastapam.RecordSwitcherInterface.gotoAll()");
        jamb.add("public abstract void mastapam.RecordSwitcherInterface.gotoStart()");
        jamb.add("public abstract void mastapam.RecordSwitcherInterface.gotoFinish()");
        jsob.put("methods", jamb);

        JSONArray jafb = new JSONArray();
        jafb.add("public static final java.lang.String mastapam.RecordSwitcherInterface.studentID");

        jsob.put("fields", jafb);

        jsob.put("Assessment", "RecordSwitcherInterface");

        jsob.put("ID", "Test Model");

        JSONObject jcb = new JSONObject();

        JSONArray jacb = new JSONArray();
        jcb.put("package name", "package mastapam");
        jcb.put("class name", "mastapam.RecordSwitcherInterface");
        jcb.put("superclass", "jacob");
        jcb.put("modifier", "abstract");
        jcb.put("type name", "interface");
        jacb.add(jcb);

        jsob.put("class", jacb);

        JSONArray jacoc = new JSONArray();
        jacoc.add("");

        jsoc.put("constructors", jacoc);

        JSONArray jamc = new JSONArray();
        jamc.add("public abstract void mastapam.RecordSwitcherInterface.gotoCurrent()");
        jamc.add("public abstract void mastapam.RecordSwitcherInterface.gotoPrevious()");
        jamc.add("public abstract void mastapam.RecordSwitcherInterface.gotoNext()");
        jamc.add("public abstract void mastapam.RecordSwitcherInterface.gotoAll()");
        jamc.add("public abstract void mastapam.RecordSwitcherInterface.gotoStart()");
        jamc.add("public abstract void mastapam.RecordSwitcherInterface.gotoFinish()");
        jsoc.put("methods", jamc);

        JSONArray jafc = new JSONArray();
        jafc.add("public static final java.lang.String mastapam.RecordSwitcherInterface.studentID");

        jsoc.put("fields", jafc);

        jsoc.put("Assessment", "RecordSwitcherInterface");

        jsoc.put("ID", "Test Model");

        JSONObject jcc = new JSONObject();

        JSONArray jacc = new JSONArray();
        jcc.put("package name", "package mastapam");
        jcc.put("class name", "mastapam.RecordSwitcherInterface");
        jcc.put("superclass", "jacob");
        jcc.put("modifier", "abstract");
        jcc.put("type name", "interface");
        jacc.add(jcc);

        jsoc.put("class", jacc);

        TestJsonDiff tj = new TestJsonDiff(jsob, jsoc);
        tj.testFields();
    }
}