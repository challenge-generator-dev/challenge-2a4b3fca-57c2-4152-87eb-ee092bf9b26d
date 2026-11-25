package model;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

// Actor de automatización del dominio de pruebas. Extiende el actor de Screenplay
// y recibe su capacidad de navegación desde un WebDriver ya creado por el proyecto.
public class Actor extends net.serenitybdd.screenplay.Actor {

    private WebDriver browser;

    public Actor(String name) {
        super(Objects.requireNonNull(name, "El nombre del actor no puede ser nulo"));
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    public static ActorBuilder builder() {
        return new ActorBuilder();
    }

    // Asocia el navegador al actor y le concede la habilidad de navegar la web.
    public Actor withBrowser(WebDriver driver) {
        this.browser = Objects.requireNonNull(driver, "El WebDriver no puede ser nulo");
        this.whoCan(BrowseTheWeb.with(driver));
        return this;
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public void initializeBrowser() {
        if (browser == null) {
            throw new IllegalStateException(
                    "WebDriver no inicializado para el actor " + getName() + ". Llama a withBrowser(...) primero.");
        }
    }

    public void closeBrowser() {
        if (browser != null) {
            browser.quit();
            browser = null;
        }
    }

    public static class ActorBuilder {
        private String name;

        public ActorBuilder withName(String name) {
            this.name = Objects.requireNonNull(name, "El nombre del actor no puede ser nulo");
            return this;
        }

        public Actor build() {
            if (name == null) {
                throw new IllegalStateException("Se requiere un nombre antes de construir el actor");
            }
            return new Actor(name);
        }
    }
}