package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;
 
public class CountWord {
	 

	public static  TreeSet<WordBean> countLetter(File file,int frequency) throws Exception{
		BufferedReader br=new BufferedReader(new FileReader(file));
		Map<String,Integer> map =new HashMap<String, Integer>();//集合
		TreeSet<WordBean> set =new TreeSet<WordBean>();//有序集合
		try{
			String line=null;
			//遍历文本将字符-次数添加到集合map中去
			while((line=br.readLine())!=null){
				StringTokenizer stoken =new StringTokenizer(line, ",.! 	");
				while(stoken.hasMoreElements()){
					int count;
					String letter=stoken.nextToken();
					if(!map.containsKey(letter)){
						count=1;
					}else{
						count=map.get(letter)+1;
					}
					map.put(letter, count);
				}
			}
		}finally{
			br.close();
		}
		
		for(Map.Entry<String, Integer> entry:map.entrySet()){
				String key =entry.getKey();
				Integer count=map.get(key);
				set.add(new WordBean(key, count));
				
		}
		return set;
	
	}
	
}
class WordBean implements Comparable<WordBean>{
	String key;
	Integer count;
	public WordBean(String key, Integer count) {
		super();
		this.key = key;
		this.count = count;
	}
	
	public String getKey() {
		return key;
	}
 
	public void setKey(String key) {
		this.key = key;
	}
 
	public Integer getCount() {
		return count;
	}
 
	public void setCount(Integer count) {
		this.count = count;
	}
 
	//WordBean按照count排序
	@Override
	public int compareTo(WordBean o) {
		int temp=this.count-o.count;
		return temp==0?this.key.compareTo(o.key):-temp; //逆序负号
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordBean other = (WordBean) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
}

