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

    private static final String API_KEY = System.getenv("ALPHA_VANTAGE_KEY");
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
    public void getDadosIndicadores(String indicador) {
        try {
            String codigoSerie = switch (indicador) {
                case "SELIC" -> "11";
                case "IPCA"  -> "433";
                case "CDI"   -> "12";
                default -> null;
            };

            if (codigoSerie == null) {
                System.out.println("Indicador inválido!");
                return;
            }

            String uri = "https://api.bcb.gov.br/dados/serie/bcdata.sgs." + codigoSerie + "/dados/ultimos/1?formato=json";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode json = mapper.readTree(response.body());
            JsonNode ultimo = json.get(0);

            String data = ultimo.get("data").asText();
            String valor = ultimo.get("valor").asText();

            System.out.println("Indicador: " + indicador);
            System.out.println("Data: " + data);
            System.out.println("Valor: " + valor + "%");

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar indicador: " + e.getMessage());
        }
    }
    public void getCotacaoCripto(String moeda) {
        try {
            String uri = "https://api.coingecko.com/api/v3/simple/price?ids=" + moeda + "&vs_currencies=brl";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode json = mapper.readTree(response.body());
            JsonNode preco = json.get(moeda);

            if (preco == null) {
                System.out.println("Criptomoeda não encontrada: " + moeda);
                return;
            }

            String valor = preco.get("brl").asText();
            System.out.println("Cotação de " + moeda + ": R$ " + valor);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar cotação: " + e.getMessage());
        }
    }
}