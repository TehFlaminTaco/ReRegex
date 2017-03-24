package reregex;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
public class Main {

	public static void main(String[] args) throws Exception { // Cover all for making programmers sad.
		String target;
		if(args.length==0){
			System.err.println("No input code detected. Nothing to run.");
			return;
		}
		
		target = args[0];
		
		HashMap<String, String> regexes = new HashMap<String,String>(13);
		String[] code = read_code(target).replaceAll("(?<!\\\\)#[^\r\n]*", "")
										 .replaceAll("\r?\n", "")
										 .replaceAll("\\(\\?#.*?\\)", "")
		String toexecute;								 .split("(?<!\\\\)/");
		if(code.length%2!=1){
			toexecute=new Scanner(System.in).useDelimiter("\\Z").next();
		} else {
			toexecute = code[code.length-1];
		}
		for(int i=0; i<code.length-1;i+=2){
			regexes.put(code[i], code[i+1].replaceAll("\\\\/", "/"));
		}
		String old="";
		while(!toexecute.equals(old)){
			old = toexecute;
			for(String e : regexes.keySet()){
				toexecute = toexecute.replaceAll(e, regexes.get(e));
				//System.err.print("\t");
				//System.err.println(toexecute);
			}
			System.err.println(toexecute);
		}
		System.out.println(toexecute);
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
	
	public static String read_code() throws Exception{
		byte[] buffer = new byte[256];
		StringBuffer sb = new StringBuffer();
		
		int L;
		while((L=System.in.read(buffer))>0){
			sb.append(new String(buffer).substring(0,L));
		}
		
		
		if(sb.length()>0 && sb.charAt(0)==("/").charAt(0)){
			sb=new StringBuffer(sb.subSequence(0, sb.length()));
		}
		
		return new String(sb);
	}

}
