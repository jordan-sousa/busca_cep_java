package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelo.Endereco;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultarCep {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String busca = "";

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite seu Cep!");
            busca = leitura.nextLine();
            if (busca.equalsIgnoreCase("sair") || busca.length() > 8){
                System.out.println("Cep invalido!");
                break;
            }

            String enderecoApi = "https://viacep.com.br/ws/" + busca + "/json/";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(enderecoApi))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);

                Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                Endereco novoEndereco = gson.fromJson(json, Endereco.class);
                System.out.println(novoEndereco);

                try(FileWriter writer = new FileWriter("endereco.json", true)){
                    gson.toJson(novoEndereco, writer);
                    writer.append("\n");
                }


            } catch (IOException e) {
                System.out.println("Erro na comunicação com a API! " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Requisição interronpida " + e.getMessage());
            }
        }

        System.out.println("Programa finalizou corretamente!");
    }
}
