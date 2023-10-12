package dev.codewithfriends;

import java.io.*;
import java.net.URLClassLoader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static java.util.Map.entry;


public class App {

    public final static String LETTERS = "A";
    public final static String BASE_URL = "https://www.names.org/baby-names-by-letter";

    public static Map<String,String> HEADERS_MAP;

    public static Document getNamesPage(String name) {
        try {
            String url = String.format("%s/n/%s/about", BASE_URL, name);
            Document doc = Jsoup.connect(url).headers(HEADERS_MAP).get();
            return doc;
        } catch (IOException ioe) {
            return null;
        }
    }

    public static Map<String,String> getNamesFromElements(Elements tableRows) {
        Map<String, String> meaningsByName = new HashMap<>();
        //List<String> result = new LinkedList<>();

        for (Element rows : tableRows) {
            Elements cells = rows.children();
            if (cells.size() == 3) {
                Element nameLink = cells.get(0);
                String nameURL = nameLink.attr("href");
                String nameKey = nameLink.text();
                Element language = cells.get(1);
                String meaning = cells.get(2).text();
                meaningsByName.put(nameKey, meaning);
                System.out.printf("Name %s means %s\n", nameKey, meaning);
            }
        }
        return meaningsByName;
    }

    public static Map<String,String> getNamesByLetterPage(char letter, int pageNum) {
        Map<String, String> meaningsByName = new HashMap<>();
        //List<String> result = new LinkedList<>();

        // https://stackoverflow.com/a/6802502/2925303
        // https://scrapeops.io/web-scraping-playbook/403-forbidden-error-web-scraping/

        try {
            String url = String.format("%s/%c/%d", BASE_URL, letter, pageNum);
            Document doc = Jsoup.connect(url).headers(HEADERS_MAP).get();
            Elements tableRows = doc.select("table.advanced-search-results-table tbody tr");

            return getNamesFromElements(tableRows);
        } catch (IOException ioe) {
            System.err.println(ioe.toString());
        }
        return meaningsByName;
    }

    /**
     * Return a map of names to meanings beginning with the given letter
     * @param letter
     * @return
     */
    public static Map<String,String> getNamesByLetter(char letter) {
        Map<String,String> result = new HashMap<>();

        // https://stackoverflow.com/a/6802502/2925303
        // https://scrapeops.io/web-scraping-playbook/403-forbidden-error-web-scraping/

        try {
            String url = String.format("%s/%c/", BASE_URL, letter);
            Document doc = Jsoup.connect(url).headers(HEADERS_MAP).get();
            Elements pagination = doc.select("div.container div.row div.col-xs-12 p");
            assert(1 == pagination.size());
            String paginateString = Objects.requireNonNull(pagination.first()).text();

            Pattern p = Pattern.compile("Showing (\\d+) to (\\d+) of (\\d+,\\d+)");
            Matcher m = p.matcher(paginateString);

            if (!m.find()) {
                return result;
            }
            int pageStart = Integer.parseInt(m.group(1));
            int pageEnd = Integer.parseInt(m.group(2));

            int total = Integer.parseInt(String.join("", m.group(3).split(",")));
            System.out.printf("%d to %d of %d\n", pageStart, pageEnd, total);
            int pageCount = total / pageEnd;

            // We return results from page 1 without re-scraping it here
            Elements tableRows = doc.select("table.advanced-search-results-table tbody tr");
            Map<String,String> page1Meanings = getNamesFromElements(tableRows);
            result.putAll(page1Meanings);
            for (int i = 2; i <= pageCount; i += 1) {
                Map<String,String> pageMeanings = getNamesByLetterPage(letter, i);
                System.out.printf("Found %d names from page %d\n", pageMeanings.size(), i);
                result.putAll(pageMeanings);
            }

            System.out.printf("Letter %c %s%n", letter, paginateString);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return result;
    }

    public static void loadHeadersMap() {
        HEADERS_MAP = new HashMap<>();

        InputStream is = App.class.getClassLoader().getResourceAsStream("headers.txt");

        BufferedInputStream bis = new BufferedInputStream(is);
        String fileContents;
        try {
            byte[] allBytes = bis.readAllBytes();
            fileContents = new String(allBytes);
        } catch(IOException ioe) {
            fileContents = "";
        }
        Stream<String> lines = fileContents.lines();
        lines.forEach((l) -> {
            String[] tokens = l.split(": ");
            HEADERS_MAP.put(tokens[0], tokens[1]);
        });
    }

    public static void retrieveNames() {
        loadHeadersMap();

        Map<String,String> result = new HashMap<>();
        for (char letter : LETTERS.toLowerCase().toCharArray()) {
            result.putAll(getNamesByLetter(letter));
        }
        System.out.printf("All names %s", result.size());
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("name-meanings.oos");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(result);
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public static void loadNamesAndSearch(String searchName) {
        try {
            FileInputStream fis = new FileInputStream("name-meanings.oos");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String,String> nameMeanings = (Map<String, String>) ois.readObject();
            String meaning = nameMeanings.get(searchName);
            if ((meaning != null) && (meaning.isEmpty())) {
                System.out.printf("Name %s not found\n", meaning);
            } else {
                System.out.printf("Name %s means %s\n", searchName, meaning);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
        }

    }
    public static void main( String[] args ) {
        System.out.println(args[1]);

        if (args[0].equals("load")) {
            loadNamesAndSearch(args[1]);
        } else if (args[0].equals("retrieve")) {
            retrieveNames();
        }
    }
}
