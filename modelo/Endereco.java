package modelo;

import com.google.gson.annotations.SerializedName;

public class Endereco {
    @SerializedName("cep")
    private String cep;
    @SerializedName("logradouro")
    private String logradouro;
    @SerializedName("bairro")
    private String bairro;
    @SerializedName("localidade")
    private String localidade;
    @SerializedName("uf")
    private String uf;

    public Endereco(String cep, String logradouro, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return  "cep = " + cep +
                ", logradouro = " + logradouro +
                ", bairro=" + bairro + ", " +
                "localidade=" + localidade +
                ", uf='" + uf;

    }
}
