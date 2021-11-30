package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.funstuff.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUtils {
    public static String fetch(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");

        // probably overkill with only API calls since JSON is only one line.
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String out;
        while ((out = br.readLine()) != null) {
            sb.append(out);
        }
        br.close();
        return sb.toString();
    }

    public static String fetchJokes() throws ExecutionException, InterruptedException {
        List<String> urls = Arrays.asList(
                "https://icanhazdadjoke.com",
                "https://api.chucknorris.io/jokes/random",
                "https://v2.jokeapi.dev/joke/Any?type=single&format=json",
                "https://my-bao-server.herokuapp.com/api/breadpuns",
                "https://api.yomomma.info/",
                "https://geek-jokes.sameerkumar.website/api?format=json",
                "https://cat-fact.herokuapp.com/facts/random",
                "https://friends-quotes-api.herokuapp.com/quotes/random"
        );
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<>();
        for (String url : urls) {
            futures.add(executor.submit(() -> HttpUtils.fetch(url)));
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // not the most elegant, but I think it's needed since they're all of different DTO type.
        DadJokeDTO dad = gson.fromJson(futures.get(0).get(), DadJokeDTO.class);
        ChuckJokeDTO chuck = gson.fromJson(futures.get(1).get(), ChuckJokeDTO.class);
        SingleLineJokeDTO singleLineJoke = gson.fromJson(futures.get(2).get(), SingleLineJokeDTO.class);
        BreadJokeDTO bread = new BreadJokeDTO(futures.get(3).get());
        YoMamaJokeDTO yoMama = gson.fromJson(futures.get(4).get(), YoMamaJokeDTO.class);
        GeekJokeDTO geek = gson.fromJson(futures.get(5).get(), GeekJokeDTO.class);
        CatFactDTO cat = gson.fromJson(futures.get(6).get(), CatFactDTO.class);
        FriendsQuoteDTO friends = gson.fromJson(futures.get(7).get(), FriendsQuoteDTO.class);

        CombinedFunStuffDTO combined = new CombinedFunStuffDTO(dad, chuck, singleLineJoke, bread, yoMama, geek, cat, friends);

        executor.shutdown();
        // could also return a DTO, and then it would be parsed to JSON later.
        return gson.toJson(combined);
    }

    public static List<String> runParallel(List<String> urls) throws IOException, ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<>();
        for (String url : urls) {
            futures.add(executor.submit(() -> HttpUtils.fetch(url)));
        }
        List<String> res = new ArrayList<>();
        for (Future<String> future : futures) {
            res.add(future.get());
        }
        // I'm not sure if this is necessary.
        executor.shutdown();
        return res;
    }

    public static List<String> runSequential(List<String> urls) throws IOException {
        List<String> res = new ArrayList<>();
        for (String url : urls) {
            res.add(fetch(url));
        }
        return res;
    }
}
