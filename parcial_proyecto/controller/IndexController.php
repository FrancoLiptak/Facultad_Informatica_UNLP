<?php

include_once "./view/TwigView.php";
include_once "SessionController.php";

class IndexController{

    private static $instance;
    
    public static function getInstance() {
        
        if (!isset(self::$instance)) {
            self::$instance = new self();
        }

        return self::$instance;
    }

    public function show( $mensaje = NULL ) {

        $controladorSession = SessionController::getInstance();
        echo TwigView::getTwig()->render('index.html.twig', array('usuario' => $controladorSession->obtenerUsuario(), 'mensaje' => $mensaje, 'logueado' => $controladorSession->isLogged()));

    }

}