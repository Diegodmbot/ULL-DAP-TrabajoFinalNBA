package modelo.datafinder;

import org.json.JSONObject;

// Clase que se encarga de ejecutar la estrategia, es el objeto con el que interactua el cliente
public class StrategyContext {
    private DataFetcherStrategy strategy;

    // El cliente puede cambiar el tipo de datafetcher que se va a usar en tiempo de ejecucion
    public void setStrategy(DataFetcherStrategy strategy) {
        this.strategy = strategy;
    }

    // El cliente no tiene que saber como se ejecuta la estrategia, solo que se ejecuta
    public JSONObject executeRequest() {
        return strategy.executeRequest();
    }
}
