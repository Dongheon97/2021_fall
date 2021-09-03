/*
컴파일러 개론 1주차 과제
201702052 이동헌
os : linux mint | jdk : openjdk-16 | compiler : eclipse compiler
*/

import java.io.*;

public class hf2c{
    public static void main(String[] args){
        BufferedReader buf = null;
        String old_line;

        File output = new File("./test.c");
        FileOutputStream fos = null;
        try{
            File hf = new File("./test.hf");
            buf = new BufferedReader(new FileReader(hf));
            fos = new FileOutputStream(output);

            String msg = "#include <stdio.h>\n#include <stdlib.h>\n\nint main(){\n";
            fos.write(string2byte(msg, false));
            while((old_line=buf.readLine()) != null){
                //System.out.println(line);
                String new_line = "";
                String content = "";

                // Remove '(', ')'
                String prep_line = old_line.substring(1, old_line.length()-1);

                // command + rest of line
                String[] token = prep_line.split(" ");
                for(int i=1; i<token.length; i++){
                    content += " " + token[i];
                }

                // Action Classify
                switch(token[0]) {
                    case "echo":
                        new_line += "printf(\"" + rm_substring(content);
                        //new_line += "printf(\"" + content;
                        break;
                    case "list_dir":
                        new_line += "system(\"ls -al";
                        break;
                    case "del":
                        new_line += "system(\"rm " + rm_substring(content);
                        break;
                    case "show":
                        new_line += "system(\"cat " + rm_substring(content);
                        break;

                    case "mov":
                        // prep new line
                        new_line += "system(\"";
                        // command(token_[1]) + rest of line(token_[2]
                        String content_ = "";
                        String[] token_ = content.split(" ");

                        for(int i=2; i<token_.length-1; i++){
                            content_ += " " + token_[i];
                        }
                        // Writing Destination
                        String last_token = token_[token_.length-1];

                        switch(token_[1]){
                            // Because of 'white space'
                            case "echo":
                                new_line += "echo " + rm_substring(content_) + " > " + rm_substring(last_token);
                                break;
                            case "list_dir":
                                new_line += "ls -al" + " > " + rm_substring(last_token);
                                break;
                            case "del":
                                new_line += "rm " + rm_substring(content_) + " > " + rm_substring(last_token);
                                break;
                            case "show":
                                new_line += "cat " + rm_substring(content_) + " > " + rm_substring(last_token);
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;

                }
                fos.write(string2byte(new_line, true));
            }

        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(buf != null) {
                    buf.close();
                    fos.write(string2byte("return 0;\n}", false));
                    fos.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */
            BufferedReader bReader = null;
            try {

                String s;
                File file = new File("test.c");
                bReader = new BufferedReader(new FileReader(file));

                // 더이상 읽어들일게 없을 때까지 읽어들이게 합니다.
                while((s = bReader.readLine()) != null) {
                    System.out.println(s);
                }
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bReader != null) bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
/* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */
        }
    }

    private static String rm_substring(String inputLine){
        // remove '"' '"'
        String new_line;
        if(inputLine.charAt(0) == '"'){
            new_line = inputLine.substring(1, inputLine.length()-1);
            return new_line;
        }
        else if(inputLine.charAt(1) == '"'){
            new_line = inputLine.substring(2, inputLine.length()-1);
            return new_line;
        }
        else{
            //System.out.println(inputLine);
            return inputLine;
        }
    }
    private static byte[] string2byte(String inputLine, boolean opt){
        // true : able new line | false : disable new line
        if (opt){
            inputLine += "\");\n";        }
        byte[] processed = inputLine.getBytes();
        return processed;
    }
}