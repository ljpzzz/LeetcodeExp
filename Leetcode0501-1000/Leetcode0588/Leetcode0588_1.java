package Leetcode0588;

import java.util.*;

class Leetcode0588_1 {

    DictTree2 root;
    public Leetcode0588_1() {
        root = new DictTree2("/");
    }

    public List<String> ls(String path) {
        DictTree2 cur = root;
        String[] pArr = path.substring(1).split("/");
        if(path.length() > 1) {
            for (String w : pArr) cur = cur.children.get(w);
        }
        List<String> ans = new ArrayList<>();
        if(cur.isFile) {
            ans.add(cur.str);
            return ans;
        }
        for(String child : cur.children.keySet()) ans.add(cur.children.get(child).str);
        Collections.sort(ans);
        return ans;
    }

    public void mkdir(String path) {
        String[] pArr = path.substring(1).split("/");
        root.add(pArr, false, "");
    }

    public void addContentToFile(String filePath, String content) {
        String[] pArr = filePath.substring(1).split("/");
        root.add(pArr, true, content);
    }

    public String readContentFromFile(String filePath) {
        DictTree2 cur = root;
        String[] pArr = filePath.substring(1).split("/");
        for(String w : pArr) cur = cur.children.get(w);
        return cur.content;
    }
    class DictTree2{
        public Map<String, DictTree2> children;
        public boolean isLeaf;
        public boolean isFile;
        public String str;
        public String content;
        public DictTree2(String tmp){
            isLeaf = false;
            isFile = false;
            children = new HashMap<>();
            str = tmp;
            content = "";
        }
        public void add(String[] word, boolean isFile, String content) {
            DictTree2 cur = this;
            for(int i = 0; i < word.length; i++){
                if(cur.children.get(word[i]) == null){
                    DictTree2 node = new DictTree2(word[i]);
                    cur.children.put(word[i], node);
                }
                cur = cur.children.get(word[i]);
            }
            cur.isLeaf = true;
            if(isFile){
                cur.isFile = true;
                cur.content += content;
            }
        }
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
