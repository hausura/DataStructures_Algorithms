/**
 * The implementation of the Trie data structure
 */
public class Trie {
    public static class TrieNode {
        public char val;
        public boolean isEndOfWord;
        public int childNum = 0;
        public TrieNode[] children = new TrieNode[26];

        public TrieNode() {
        }

        public TrieNode(char c) {
            val = c;
        }
    }

    private static TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) { // If there is no child node, create one
                ws.children[c - 'a'] = new TrieNode(c);
                ws.childNum++;
            }
            ws = ws.children[c - 'a'];
        }
        ws.isEndOfWord = true;
    }

    // Searches for a word in the trie. Returns -1 if the word is found.
    public int search(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) {
                return i;
            }
            ws = ws.children[c - 'a'];
        }
        if (ws.isEndOfWord) {
            return -1;
        } else {
            return word.length();
        }

    }

    // Checks whether a given word can be deleted from the trie.
    public int canDelete(String key) {
        TrieNode currentNode = root;
        int deepestNode = -1; // initialize with -1
        int i = 0;
        while (i < key.length()) {
            int index = key.charAt(i) - 'a';
            if (currentNode.children[index] == null) {
                return -1;
            }
            currentNode = currentNode.children[index];
            if (currentNode.childNum > 1 || currentNode.isEndOfWord) {
                deepestNode = i;
                break;
            }
            i++;
        }
        if (i == key.length() - 1) {
            if (currentNode.isEndOfWord && currentNode.childNum > 0) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return deepestNode + 1;
        }
    }

    // Returns the longest common prefix of all the words in the trie.
    public String longestCommonPrefix() {
        return longestCommonPrefix(root);
    }

    public String longestCommonPrefix(TrieNode node) {
        StringBuilder prefix = new StringBuilder();
        while (node.childNum == 1 && !node.isEndOfWord) {
            int index = 0;
            while (node.children[index] == null) {
                index++;
            }
            node = node.children[index];
            prefix.append((char) ('a' + index));
        }
        return prefix.toString();
    }

    // Deletes a word from the trie.
    public void delete(String word) {
        TrieNode ws = root;
        TrieNode deletedNode = root;
        char lastDeletedChar = word.charAt(0);
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.childNum != 1 || ws.isEndOfWord) {
                deletedNode = ws;
                lastDeletedChar = c;
            }
            ws = ws.children[c - 'a'];
        }
        if (ws.isEndOfWord && ws.childNum != 0) {
            ws.isEndOfWord = false;
        } else {
            deletedNode.childNum--;
            deletedNode.children[lastDeletedChar - 'a'] = null;
        }
    }

    // Clears the trie.
    public void clear() {
        root = new TrieNode(' ');
    }
}

