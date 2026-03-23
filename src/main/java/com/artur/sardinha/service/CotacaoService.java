package com.artur.sardinha.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CotacaoService {

    private static final String API_KEY = "U9ULXFRQ9X9NMRL2";
    private static final String URL = "https://www.alphavantage.co/query";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public BigDecimal getCotacao(String simbolo) {
        try {
            String uri = URL + "?function=GLOBAL_QUOTE&symbol=" + simbolo + "&apikey=" + API_KEY;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode json = mapper.readTree(response.body());
            JsonNode globalQuote = json.get("Global Quote");

            if (globalQuote == null || globalQuote.get("05. price") == null) {
                System.out.println("Símbolo não encontrado ou limite da API atingido.");
                return BigDecimal.ZERO;
            }
            String preco = globalQuote.get("05. price").asText();

            return new BigDecimal(preco);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar cotação: " + e.getMessage());
            return BigDecimal.ZERO;
        }
    }
}