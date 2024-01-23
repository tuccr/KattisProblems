// due january 4th


import java.util.*;

public class dyslectionary {
    /* 
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        try{
            String temp = in.nextLine();
            
            while(true) {
                if(temp.equals("")) {
                    Collections.sort(words, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            int a = 1;
                            while(s1.charAt(s1.length() - a) == s2.charAt(s2.length() - a)) {
                                a++;
                            }
                            return s1.charAt(s1.length() - a) - s2.charAt(s2.length() - a);
                        }
                    });
                    

                    int n = findLongestWord(words);
                    for(String s : words) {
                        System.out.println(addSpaces(n - s.length(), s));
                    }

                    words.clear();

                    temp = in.nextLine();
                    continue;
                }
                words.add(temp);
                temp = in.nextLine();
            }
        }
        catch(Exception e) {
            in.close();
        }
    }*/

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> tempList = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        try {
            while(true) {
                words.add(in.nextLine());
            }
        }
        catch(NoSuchElementException e) {
            words.add("");
        }
        finally {
            in.close();
            try {
                for(String s : words) {
                    if(s.equals("")) {
                        Collections.sort(tempList, new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                int a = 1;
                                while(s1.charAt(s1.length() - a) == s2.charAt(s2.length() - a)) {
                                    a++;
                                }
                                return s1.charAt(s1.length() - a) - s2.charAt(s2.length() - a);
                            }
                        });

                        int n = findLongestWord(tempList);
                        for(String str : tempList) {
                            System.out.println(addSpaces(n - str.length(), str));
                        }

                        tempList.clear();
                    }
                    else {
                        tempList.add(s);
                    }
                }
            }
            catch(Exception e) {
                tempList.clear();
                words.clear();
            }
        }
        
    }

    public static int findLongestWord(ArrayList<String> words) {
        int n = 0;
        for(String s: words) {
            if(s.length() > n) {
                n = s.length();
            }
        }
        return n;
    }

    public static String addSpaces(int numSpaces, String str) {
        StringBuilder str1 = new StringBuilder();
        for(int i = 0; i < numSpaces; i++) {
            str1.append(" ");
        }
        return str1.append(str).toString();
    }

    
}


//Collections.sort(revWords, new Comparator<String>() {
//    @Override
//    public int compare(String s1, String s2) {
//        if(s1.charAt(0) == s2.charAt(0)) {
//            return s1.charAt(1) - s2.charAt(2);
//        }
//        return s1.compareTo(s2);
//    }
//});


/*
 * if blank link: sort list, print list, empty list
 *  collections sort, Comparator<>, lambda
 * sort list: compare each letter from right to left and keep track of index (outof bounds)
 *  reverse both strings, compare both strings
 * print list: right justified
 *  find longest length
 * pad the remaining space in front of string, longest - length()
 * 
 * else keep adding words to list
 * if list is not emppty
 * 
 * 
 * 
 * some guy used try-catch for finding end of file, then reverse string and sort alphabetically
 */