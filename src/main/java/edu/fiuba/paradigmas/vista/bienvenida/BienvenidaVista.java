package edu.fiuba.paradigmas.vista.bienvenida;

import edu.fiuba.paradigmas.vista.componentes.bienvenida.BannerDispositivo;
import edu.fiuba.paradigmas.vista.componentes.FondoEstrellas;
import edu.fiuba.paradigmas.vista.componentes.bienvenida.LogoMafia;
import edu.fiuba.paradigmas.vista.componentes.bienvenida.MenuAcciones;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BienvenidaVista extends StackPane {

    private final MenuAcciones menuAcciones;

    public BienvenidaVista() {
        FondoEstrellas fondo = new FondoEstrellas("#060e17", "");

        LogoMafia logo = new LogoMafia();
        this.menuAcciones = new MenuAcciones();
        BannerDispositivo banner = new BannerDispositivo();

        Region espaciadorCentral = new Region();
        espaciadorCentral.setPrefHeight(40);

        Region espaciadorInferior = new Region();
        VBox.setVgrow(espaciadorInferior, Priority.ALWAYS);

        VBox uiPrincipal = new VBox();
        uiPrincipal.setAlignment(Pos.CENTER);
        uiPrincipal.setPadding(new Insets(60, 40, 30, 40));
        uiPrincipal.getChildren().addAll(
                logo,
                espaciadorCentral,
                this.menuAcciones,
                espaciadorInferior,
                banner
        );

        this.getChildren().addAll(fondo, uiPrincipal);
    }

    public void configurarBotonContinuar(Runnable accion) {
        this.menuAcciones.configurarBotonPrincipal(accion);
    }
}