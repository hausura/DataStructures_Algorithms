/**
 * This is a Java class for a GUI implementation of a Trie data structure visualization.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TrieGUI extends JFrame {
    public static MyPanel panel = new MyPanel();
    Timer timer;
    Trie trie = new Trie();
    FunctionBar functionBar = new FunctionBar();

    public TrieGUI() {
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Trie Visualization");
        setVisible(true);
        setLayout(null);

        ImageIcon image = new ImageIcon("components/images/logo_64_eyes.png");
        setIconImage(image.getImage());
        getContentPane().setBackground(new Color(203, 237, 213));
        add(functionBar);

        // create a JScrollPane and add the panel to it
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(200, 0, 1080, 700);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setViewPosition(new Point(500, 0));
        add(scrollPane);
    }

    public void start() {
        // insert operation
        functionBar.operationButton.insert.addMouseListener(new MouseAdapter() {        // Mouse click event
            @Override
            public void mousePressed(MouseEvent e) {
                String word = functionBar.textField.getText().toLowerCase();
                // Check if word is empty
                if (word.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Check if word contains only letters
                boolean isAllLetters = true;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    if (ch > 'z' || ch < 'a') {
                        isAllLetters = false;
                        break;
                    }
                }

                if (isAllLetters) {
                    trie.insert(word);
                    ActionListener action = new ActionListener() {
                        int x = 870;
                        int i = 0;
                        boolean isEndOfWord = false;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            x = x + (word.charAt(i) - 'm') * 15;
                            if (i < word.length() - 1) {
                                isEndOfWord = false;
                            } else {
                                isEndOfWord = true;
                                timer.stop();
                            }
                            Node node = new Node(x, 100 + 80 * i, word.charAt(i), isEndOfWord, false);
                            MyPanel.vectorNode.add(node);
                            panel.repaint();
                            i++;
                        }
                    };
                    timer = new Timer(800, action);
                    timer.start();
                } else {
                    JOptionPane.showMessageDialog(null, "Input must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                functionBar.textField.setText("");
            }
        });

        // search operation
        functionBar.operationButton.search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String word = functionBar.textField.getText().toLowerCase();
                // Check if word is empty
                if (word.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean isAllLetters = true;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    if (ch > 'z' || ch < 'a') {
                        isAllLetters = false;
                        break;
                    }
                }

                if (isAllLetters) {
                    int count = trie.search(word);
                    if (count == -1) {
                        //Graphics g = new Graphics()
                        ActionListener action = new ActionListener() {
                            int x = 870;
                            int i = 0;
                            boolean isEndOfWord = false;

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                x = x + (word.charAt(i) - 'm') * 15;
                                if (i < word.length() - 1) {
                                    isEndOfWord = false;
                                } else {
                                    isEndOfWord = true;
                                    JOptionPane.showMessageDialog(null, "Word existed", "Result", JOptionPane.PLAIN_MESSAGE);
                                    timer.stop();
                                }
                                if (MyPanel.vectorNode.contains(new Node(x, 100 + 80 * i, word.charAt(i), true, false))) {
                                    MyPanel.vectorNode.add(new Node(x, 100 + 80 * i, word.charAt(i), true, false));
                                } else {
                                    Node node = new Node(x, 100 + 80 * i, word.charAt(i), isEndOfWord, false);
                                    MyPanel.vectorNode.add(node);
                                }
                                panel.repaint();
                                i++;
                            }
                        };
                        timer = new Timer(800, action);
                        timer.start();
                    } else if (count == 0) {
                        JOptionPane.showMessageDialog(null, "Oops, word not existed", "Result", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        ActionListener action = new ActionListener() {
                            int x = 870;
                            int i = 0;
                            boolean isEndOfWord = false;

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                x = x + (word.charAt(i) - 'm') * 15;
                                if (i < word.length() - 1) {
                                    isEndOfWord = false;
                                }
                                if (i == count - 1) {
                                    timer.stop();
                                    JOptionPane.showMessageDialog(null, "Oops, word not existed", "Result", JOptionPane.PLAIN_MESSAGE);
                                }
                                if (MyPanel.vectorNode.contains(new Node(x, 100 + 80 * i, word.charAt(i), true, false))) {
                                    MyPanel.vectorNode.add(new Node(x, 100 + 80 * i, word.charAt(i), true, false));
                                } else {
                                    Node node = new Node(x, 100 + 80 * i, word.charAt(i), isEndOfWord, false);
                                    MyPanel.vectorNode.add(node);
                                }
                                panel.repaint();
                                i++;
                            }
                        };
                        timer = new Timer(800, action);
                        timer.start();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Input must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                functionBar.textField.setText("");
            }
        });

        // delete operation
        functionBar.operationButton.delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String word = functionBar.textField.getText().toLowerCase();
                // Check if word is empty
                if (word.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean isAllLetters = true;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    if (ch > 'z' || ch < 'a') {
                        isAllLetters = false;
                        break;
                    }
                }
                if (isAllLetters) {
                    int isContain = trie.search(word);
                    if (isContain == 0) {
                        JOptionPane.showMessageDialog(null, "Cannot delete word.", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else if (isContain == -1) {
                        int canDel = trie.canDelete(word);
                        trie.delete(word);
                        ActionListener action;
                        if (canDel == -1) {
                            action = new ActionListener() {
                                int x = 870;
                                int i = 0;
                                boolean isEndOfWord = false;
                                boolean isDel = false;

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    x = x + (word.charAt(i) - 'm') * 15;
                                    if (i == word.length() - 1) {
                                        isEndOfWord = false;
                                        isDel = true;
                                        timer.stop();
                                    }
                                    Node node = new Node(x, 100 + 80 * i, word.charAt(i), isEndOfWord, isDel);
                                    MyPanel.vectorNode.add(node);
                                    panel.repaint();
                                    i++;
                                }
                            };
                        } else {
                            //Graphics g = new Graphics()
                            action = new ActionListener() {
                                int x = 870;
                                int i = 0;
                                final boolean isEndOfWord = false;

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    x = x + (word.charAt(i) - 'm') * 15;
                                    Node node;
                                    if (i < canDel) {
                                        node = new Node(x, 100 + 80 * i, word.charAt(i), isEndOfWord, false);
                                    } else if (i == word.length() - 1) {
                                        MyPanel.deleteWord(x, 100 + 80 * i);
                                        node = new Node(x, 100 + 80 * i, '1', isEndOfWord, true);
                                        timer.stop();
                                    } else {
                                        MyPanel.deleteWord(x, 100 + 80 * i);
                                        node = new Node(x, 100 + 80 * i, '1', isEndOfWord, true);
                                    }
                                    MyPanel.vectorNode.add(node);
                                    panel.repaint();
                                    i++;
                                }
                            };
                        }
                        timer = new Timer(800, action);
                        timer.start();
                    } else {
                        ActionListener action = new ActionListener() {
                            int x = 870;
                            int i = 0;
                            boolean isEndOfWord = false;

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                x = x + (word.charAt(i) - 'm') * 15;
                                if (i < word.length() - 1) {
                                    isEndOfWord = false;
                                }
                                if (i == isContain - 1) {
                                    timer.stop();
                                    JOptionPane.showMessageDialog(null, "Cannot delete word.", "Warning", JOptionPane.WARNING_MESSAGE);
                                }
                                Node node = new Node(x, 100 + 80 * i, word.charAt(i), isEndOfWord, false);
                                MyPanel.vectorNode.add(node);
                                panel.repaint();
                                i++;
                            }
                        };
                        timer = new Timer(800, action);
                        timer.start();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Input must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                functionBar.textField.setText("");
            }
        });

        // find the longest common prefix operation
        functionBar.operationButton.LCP.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String word = trie.longestCommonPrefix();
                if (!word.equals("")) {
                    ActionListener action = new ActionListener() {
                        int x = 870;
                        int i = 0;
                        boolean isEndOfWord = false;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            x = x + (word.charAt(i) - 'm') * 15;
                            if (i < word.length() - 1) {
                                isEndOfWord = false;
                            } else {
                                timer.stop();
                                JOptionPane.showMessageDialog(null, "The longest common prefix is:\n[" + word + "]", "Result", JOptionPane.PLAIN_MESSAGE);
                            }
                            if (MyPanel.vectorNode.contains(new Node(x, 100 + 80 * i, word.charAt(i), true, false))) {
                                MyPanel.vectorNode.add(new Node(x, 100 + 80 * i, word.charAt(i), true, false));
                            } else {
                                Node node = new Node(x, 100 + 80 * i, word.charAt(i), isEndOfWord, false);
                                MyPanel.vectorNode.add(node);
                            }
                            panel.repaint();
                            i++;
                        }
                    };
                    timer = new Timer(800, action);
                    timer.start();
                } else {
                    JOptionPane.showMessageDialog(null, "Oops, there is no common prefix", "Result", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // clear operation
        functionBar.operationButton.clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                trie.clear();
                MyPanel.vectorNode.clear();
                panel.repaint();
            }
        });
    }

    public static void main(String[] args) {
        TrieGUI trieGUI = new TrieGUI();
        trieGUI.start();
    }
}
