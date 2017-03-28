package reregex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {

	static Pattern comments = Pattern.compile("(?<!\\\\)((\\\\\\\\)*)(\\(\\?#(.*?)\\)|#(.*))");
	static String stdin;
	static String path;
	
	public static void main(String[] args) throws Exception { // Cover all for making programmers sad.
		String target;
		if(args.length==0){
			System.err.println("No input code detected. Nothing to run.");
			return;
		}
		
		target = args[0];
		path = target.replaceAll("(.*)/.*?$", "$1");
		
		HashMap<String, String> regexes = new HashMap<String,String>(13);
		
		stdin = read_stdin();
		String[] code = parse_code(read_code(target));
		if(code.length%2!=1){
			System.err.println("No default code detected, nothing to run.");
			return;
		}
		for(int i=0; i<code.length-1;i+=2){
			regexes.put(code[i], code[i+1].replaceAll("\\\\/", "/"));
		}
		
		String toexecute = code[code.length-1].replaceAll("\\\\/", "/");
		String old="";
		while(!toexecute.equals(old)){
			old = toexecute;
			for(String e : regexes.keySet()){
				toexecute = toexecute.replaceAll(e, regexes.get(e));
			}
			if(args.length>1){
				System.err.println(toexecute);
			}
		}
		System.out.println(toexecute);
	}
	
	private static String read_stdin() throws IOException {
		byte[] buffer = new byte[256];
		StringBuffer sb = new StringBuffer();
		
		int L;
		while((L=System.in.read(buffer))>0){
			sb.append(new String(buffer).substring(0,L));
		}
		
		return new String(sb);
	}

	private static String[] parse_code(String read_code) throws IOException {
		Matcher m = comments.matcher(read_code);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			String grp = "";
			if(m.group(4)==null){
				grp = m.group(5);
			}else{
				grp = m.group(4);
			}
			if(grp.matches("^input$")){
				m.appendReplacement(sb, m.group(1)+"(?#"+grp+")");
			}else if(grp.matches("^import .*")){
				String target = grp.replaceAll("^import ", "");
				String[] totry = {target, "lib/"+target, target+".rr", "lib/"+target+".rr", path+"/"+target, path+"/lib/"+target, path+"/"+target+".rr", path+"/lib/"+target+".rr"};
				boolean found = false;
				for (int i=0; i<totry.length; i++){
					File f = new File(totry[i]);
					if(f.exists()){
						FileReader fr = new FileReader(f);
						BufferedReader br = new BufferedReader(fr);
						String out = "";
						String line = null;
						while((line = br.readLine())!=null){
							out += line + "\n";
						}
						sb.insert(0, String.join("/",parse_code(out))+"/");
						found = true;
						br.close();
						break;
					}
				}
				if (!found){
					System.err.println("Could not find library \""+target+"\"");
				}
				m.appendReplacement(sb, "");
			}else{
				m.appendReplacement(sb, m.group(1));
			}
		}
		m.appendTail(sb);
		String tmp = new String(sb);
		tmp = tmp.replaceAll("\r?\n", "");
		Stack<String> strs = new Stack<String>();
		char[] chars = tmp.toCharArray();
		boolean ignext = false;
		int strt = 0;
		for(int i=0; i<chars.length; i++){
			if(ignext){
				ignext = false;
				continue;
			}
			if(chars[i]=='\\'){
				ignext = true;
			}
			if(chars[i]=='/'){
				strs.push(new String(Arrays.copyOfRange(chars, strt, i)));
				strt = i+1;
			}
		}
		if(strt!=chars.length){
			strs.push(new String(Arrays.copyOfRange(chars, strt, chars.length)));
		}
		String[] strings = new String[strs.size()];
		strings = strs.toArray(strings);
		
		for (int i=0; i<strings.length; i++){
			strings[i] = strings[i].replaceAll("\\(\\?#input\\)", (stdin).replaceAll("([^a-zA-Z0-9])", "\\\\$1"))
							       .replaceAll("\\\\n", "\n");
		}
		return strings;
	}

	public static String read_code(String target) throws Exception{
		File f = new File(target);
		FileReader fr = new FileReader(f);
		char[] buffer = new char[256];
		StringBuffer sb = new StringBuffer();
		
		int L;
		while((L=fr.read(buffer))>0){
			sb.append(buffer,0,L);
		}
		fr.close();
		
		
		if(sb.length()>0 && sb.charAt(0)==("/").charAt(0)){
			sb=new StringBuffer(sb.subSequence(1, sb.length()));
		}
		
		return new String(sb);
	}

}
